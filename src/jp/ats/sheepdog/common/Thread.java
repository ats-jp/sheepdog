package jp.ats.sheepdog.common;

import jp.ats.sheepdog.PermissionChecker;
import jp.ats.sheepdog.User;
import jp.ats.sheepdog.Utilities;
import jp.ats.sheepdog.dataobjects.v_threadDTO;
import jp.ats.webkit.util.Sanitizer;

public class Thread {

	private final v_threadDTO dto;

	public Thread(v_threadDTO dto) {
		this.dto = dto;
	}

	public int getID() {
		return dto.getthread_id();
	}

	public int getContentID() {
		return dto.getcontent_id();
	}

	public String getTitle() {
		return Sanitizer.sanitize(dto.gettitle());
	}

	public String getBody() {
		return Sanitizer.sanitize(dto.getbody());
	}

	public User getOwner() {
		return new User(dto.gett_userByv_thread_owner_fkey());
	}

	public int getOwnerID() {
		return dto.getowner();
	}

	public Group getGroup() {
		return new Group(dto.gett_groupByv_thread_group_id_fkey());
	}

	public Integer getGroupID() {
		return dto.getgroup_id();
	}

	public ApplyingLevel getApplyingLevel() {
		return ApplyingLevel.values()[dto.getapplying_level()];
	}

	public boolean getEventFlag() {
		return dto.getevent_flag() == 1;
	}

	public int getPrivateFlag() {
		return dto.getprivate_flag();
	}

	public String getCreateTime() {
		return Utilities.convertTimestamp(dto.getcreate_time());
	}

	public String getUpdateTime() {
		return Utilities.convertTimestamp(dto.getupdate_time());
	}

	public String getContentCreateTime() {
		return Utilities.convertTimestamp(dto.getcontent_create_time());
	}

	public int getContentCount() {
		return dto.getcontent_count().intValue();
	}

	public int getCommentCount() {
		return dto.getcomment_count().intValue();
	}

	public String getCalendar() {
		return dto.getcalendar();
	}

	public boolean canCange() {
		return PermissionChecker.canStore(dto.getowner());
	}
}
