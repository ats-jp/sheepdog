insert into t_notice (
	owner,
	group_id,
	thread_id,
	comment_id)
select
	owner,
	null,
	thread_for_comment,
	?
from
	t_subscribe
where
	thread_for_comment = ? and
	owner <> ?
