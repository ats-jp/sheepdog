package jp.ats.sheepdog.common;

import jp.ats.sheepdog.PermissionChecker;
import jp.ats.sheepdog.User;
import jp.ats.sheepdog.Utilities;
import jp.ats.sheepdog.dataobjects.t_commentDTO;
import jp.ats.webkit.util.Sanitizer;

public class Comment {

	private final t_commentDTO dto;

	public Comment(t_commentDTO dto) {
		this.dto = dto;
	}

	public int getID() {
		return dto.getid();
	}

	public String getBody() {
		return Sanitizer.sanitize(dto.getbody());
	}

	public User getOwner() {
		return new User(dto.gett_userByt_comment_owner_fkey());
	}

	public int getNumber() {
		return dto.getv_comment_relationshipByt_comment_id_fkey()
			.getcomment_number();
	}

	public long getAttachCount() {
		return dto.getv_comment_relationshipByt_comment_id_fkey()
			.getattach_count();
	}

	public boolean hasParent() {
		return dto.getv_comment_relationshipByt_comment_id_fkey()
			.getparent_id() != null;
	}

	public int getParentID() {
		return dto.getv_comment_relationshipByt_comment_id_fkey()
			.getparent_id();
	}

	public int getParentNumber() {
		return dto.getv_comment_relationshipByt_comment_id_fkey()
			.getparent_number();
	}

	public String getCreateTime() {
		return Utilities.convertTimestamp(dto.getcreate_time());
	}

	public String getUpdateTime() {
		return Utilities.convertTimestamp(dto.getupdate_time());
	}

	public boolean isDeleted() {
		return dto.getdelete_flag() == 1;
	}

	public boolean canDelete() {
		return PermissionChecker.canStore(dto.getowner());
	}

	public Integer getThreadID() {
		return dto.getv_comment_relationshipByt_comment_id_fkey()
			.gett_threadByv_comment_relationship_thread_id_fkey()
			.getid();
	}

	public String getThreadTitle() {
		return dto.getv_comment_relationshipByt_comment_id_fkey()
			.gett_threadByv_comment_relationship_thread_id_fkey()
			.getlast_title();
	}
}
