package jp.ats.sheepdog.common;

import jp.ats.sheepdog.User;
import jp.ats.sheepdog.Utilities;
import jp.ats.sheepdog.dataobjects.t_contentDTO;
import jp.ats.webkit.util.Sanitizer;

public class Content {

	private final t_contentDTO dto;

	public Content(t_contentDTO dto) {
		this.dto = dto;
	}

	public int getID() {
		return dto.getid();
	}

	public String getTitle() {
		return Sanitizer.sanitize(dto.gettitle());
	}

	public String getBody() {
		return Sanitizer.sanitize(dto.getbody());
	}

	public User getOwner() {
		return new User(dto.gett_userByt_content_owner_fkey());
	}

	public String getCreateTime() {
		return Utilities.convertTimestamp(dto.getcreate_time());
	}
}
