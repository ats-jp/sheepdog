package jp.ats.sheepdog.common;

import jp.ats.sheepdog.User;
import jp.ats.sheepdog.Utilities;
import jp.ats.sheepdog.dataobjects.t_threadDTO;
import jp.ats.webkit.util.Sanitizer;

public class RawThread {

	private final t_threadDTO dto;

	public RawThread(t_threadDTO dto) {
		this.dto = dto;
	}

	public int getID() {
		return dto.getid();
	}

	public String getTitle() {
		return Sanitizer.sanitize(dto.getlast_title());
	}

	public User getOwner() {
		return new User(dto.gett_userByt_thread_owner_fkey());
	}

	public Group getGroup() {
		return new Group(dto.gett_groupByt_thread_group_id_fkey());
	}

	public Integer getGroupID() {
		return dto.getgroup_id();
	}

	public ApplyingLevel getApplyingLevel() {
		return ApplyingLevel.values()[dto.getapplying_level()];
	}

	public String getCreateTime() {
		return Utilities.convertTimestamp(dto.getcreate_time());
	}

	public String getUpdateTime() {
		return Utilities.convertTimestamp(dto.getupdate_time());
	}

	public String getCalendar() {
		return dto.getcalendar();
	}
}
