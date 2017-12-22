package jp.ats.sheepdog.common;

import jp.ats.sheepdog.dataobjects.t_subscribeDTO;

public class Subscribe {

	private final t_subscribeDTO dto;

	public Subscribe(t_subscribeDTO dto) {
		this.dto = dto;
	}

	public Group getGroupForThread() {
		return new Group(dto.gett_groupByt_subscribe_group_for_thread_fkey());
	}

	public RawThread getThreadForComment() {
		return new RawThread(
			dto.gett_threadByt_subscribe_thread_for_comment_fkey());
	}
}
