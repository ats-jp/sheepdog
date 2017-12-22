insert into t_seal (
	owner,
	thread_id,
	comment_id)
select
	owner,
	?,
	null
from
	t_subscribe
where
	group_for_thread = ? and
	owner <> ?
