insert into t_notice (
	owner,
	group_id,
	thread_id,
	comment_id)
select
	owner,
	group_for_thread,
	?,
	null
from
	t_subscribe
where
	group_for_thread = ? and
	owner <> ?
