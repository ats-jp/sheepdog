insert into t_seal (
	owner,
	thread_id,
	comment_id)
select
	owner,
	null,
	?
from
	t_subscribe
where
	thread_for_comment = ? and
	owner <> ?
union all
select
	owner,
	?,
	null
from
	t_subscribe
where
	thread_for_comment = ? and
	owner <> ?
