create table t_user (
	id serial primary key,
	mail_address text unique,
	evacuated_mail_address text,
	name text not null,
	department text,
	title text,
	salt text not null,
	password text not null,
	expiration_date timestamp,
	role integer check (role in (0, 1, 2)),
	delete_flag integer check (delete_flag in (0, 1)) default 0,
	create_time timestamp default current_timestamp,
	update_time timestamp default current_timestamp);

insert into t_user (
	mail_address,
	name,
	salt,
	password,
	role)
values (
	'system@sheepdog',
	'システム管理者',
	'579911fc7d233a3a08fc',
	'6c0e4ac219b939dd0a143f3996d6f63b2bbbf98c8b5e6245180c3b5218a5380a7145552a2d318a7ad3f571844ad492e103915da7efc8b1e09a16490f83fd440c',
	0);

create table t_group (
	id serial primary key,
	name text not null,
	description text,
	owner integer references t_user (id) not null,
	applying_level integer check (applying_level in (0, 1, 2)),
	private_flag integer check (private_flag in (0, 1)) default 1,
	delete_flag integer check (delete_flag in (0, 1)) default 0,
	create_time timestamp default current_timestamp,
	update_time timestamp default current_timestamp);

create table t_member (
	id bigserial primary key,
	user_id integer references t_user (id) not null,
	group_id integer references t_group (id) not null,
	create_time timestamp default current_timestamp);

alter table t_member add constraint t_member_unique_key unique (user_id, group_id);

create table t_thread (
	id serial primary key,
	calendar char(8) check (case when event_flag = 0 then calendar is null else calendar is not null end),
	group_id integer references t_group (id) check (case when event_flag = 0 then group_id is not null end),
	event_flag integer check (event_flag in (0, 1)),
	last_title text not null,
	owner integer references t_user (id) not null,
	applying_level integer check (applying_level in (0, 1, 2)),
	private_flag integer check (private_flag in (0, 1)) default 1,
	delete_flag integer check (delete_flag in (0, 1)) default 0,
	create_time timestamp default current_timestamp,
	update_time timestamp default current_timestamp);

create table t_content (
	id serial primary key,
	thread_id integer references t_thread (id) not null,
	title text not null,
	body text,
	owner integer references t_user (id) not null,
	create_time timestamp default current_timestamp);

create table t_subscribe (
	id bigserial primary key,
	group_for_thread integer references t_group (id),
	thread_for_comment integer references t_thread (id),
	owner integer references t_user (id) not null,
	create_time timestamp default current_timestamp);

create table t_comment (
	id serial primary key,
	thread_id integer references t_thread (id) not null,
	comment_number integer not null,
	parent_id integer references t_comment (id),
	owner integer references t_user (id) not null,
	body text,
	delete_flag integer check (delete_flag in (0, 1)) default 0,
	create_time timestamp default current_timestamp,
	update_time timestamp default current_timestamp);

alter table t_comment add constraint t_comment_number_key unique (thread_id, comment_number);
alter table t_comment add constraint t_comment_id_key unique (thread_id, id);

create table t_attach (
	id serial primary key,
	name text not null,
	size integer not null,
	path text,
	delete_flag integer check (delete_flag in (0, 1)) default 0,
	create_time timestamp default current_timestamp,
	update_time timestamp default current_timestamp);

create table t_content_attach (
	id bigserial,
	attach_id integer references t_attach (id),
	content_id integer references t_content (id),
	create_time timestamp default current_timestamp,
	constraint t_content_attach_pkey primary key (attach_id, content_id));

create table t_comment_attach (
	attach_id integer primary key references t_attach (id),
	comment_id integer references t_comment (id),
	create_time timestamp default current_timestamp);

create table t_notice (
	id bigserial primary key,
	owner integer references t_user (id) not null,
	group_id integer references t_group (id),
	thread_id integer references t_thread (id),
	comment_id integer references t_comment (id),
	create_time timestamp default current_timestamp);

create table t_last_notice (
	owner integer references t_user (id) primary key,
	last_notice bigint not null,
	create_time timestamp default current_timestamp);

create table t_seal (
	id bigserial primary key,
	owner integer references t_user (id) not null,
	thread_id integer references t_thread (id),
	comment_id integer references t_comment (id),
	create_time timestamp default current_timestamp);

create table t_member_order (
	owner integer references t_user (id) not null,
	group_id integer references t_group (id) not null,
	member_order text,
	create_time timestamp default current_timestamp,
	update_time timestamp default current_timestamp,
	constraint t_member_order_pkey primary key (owner, group_id));

create table t_group_history (
	owner integer references t_user (id) not null,
	group_id integer references t_group (id) not null,
	create_time timestamp default current_timestamp,
	update_time timestamp default current_timestamp,
	constraint t_group_history_pkey primary key (owner, group_id));

create view v_thread (
	thread_id,
	content_id,
	calendar,
	title,
	body,
	owner,
	group_id,
	event_flag,
	applying_level,
	private_flag,
	content_count,
	comment_count,
	create_time,
	update_time,
	content_create_time
) as
select
	t_thread.id,
	t_content.id,
	t_thread.calendar,
	t_content.title,
	t_content.body,
	t_thread.owner,
	t_thread.group_id,
	t_thread.event_flag,
	t_thread.applying_level,
	t_thread.private_flag,
	contents.all_count,
	coalesce(comments.all_count, 0),
	t_thread.create_time,
	t_thread.update_time,
	t_content.create_time
from
	t_thread
	join (
		select
			count(*) as all_count,
			max(id) as max_id,
			thread_id
		from
			t_content
		group by
			thread_id) contents
	on
		t_thread.id = contents.thread_id
	join
	t_content
	on
		contents.max_id = t_content.id
	left outer join (
		select
			count(*) as all_count,
			thread_id
		from
			t_comment
		where
			delete_flag = 0
		group by
			thread_id) comments
	on
		t_thread.id = comments.thread_id
where
	t_thread.delete_flag = 0;

create view v_comment_relationship (
	comment_id,
	thread_id,
	comment_number,
	parent_id,
	parent_number,
	attach_count
) as
select
	comment_info.id,
	comment_info.thread_id,
	comment_info.comment_number,
	comment_info.parent_id,
	comment_info.parent_number,
	coalesce(attach_info.attach_count, 0)
from (
	select
		main_comment.id,
		main_comment.thread_id,
		main_comment.comment_number,
		main_comment.parent_id,
		parent_comment.comment_number as parent_number
	from
		t_comment main_comment
		left outer join
			t_comment parent_comment
		on
			main_comment.parent_id = parent_comment.id) comment_info
	left outer join (
		select
			comment_id,
			count(*) as attach_count
		from
			t_comment_attach
		group by
			comment_id) attach_info
	on
		comment_info.id = attach_info.comment_id;

create view v_current_notice (
	owner,
	max_id,
	last_id
) as
select
	current_notice.owner,
	current_notice.max_id,
	coalesce(t_last_notice.last_notice, -1)
from (
	select
		max(id) as max_id,
		owner
	from
		t_notice
	group by
		owner) current_notice
	left outer join
		t_last_notice
	on
		current_notice.owner = t_last_notice.owner;
