package jp.ats.sheepdog;

import static jp.ats.substrate.U.care;

import java.util.Date;

import jp.ats.sheepdog.dataobjects.t_userDTO;
import jp.ats.webkit.util.Sanitizer;

public class User {

	private final t_userDTO dto;

	public User(t_userDTO dto) {
		this.dto = dto;
	}

	public int getID() {
		return dto.getid();
	}

	public String getMailAddress() {
		return Sanitizer.sanitize(dto.getmail_address());
	}

	public String getName() {
		return Sanitizer.sanitize(dto.getname());
	}

	public Role getRole() {
		return Role.values()[dto.getrole()];
	}

	public String getDepartment() {
		return Sanitizer.sanitize(care(dto.getdepartment()));
	}

	public String getTitle() {
		return Sanitizer.sanitize(care(dto.gettitle()));
	}

	public String getExpirationDate() {
		Date expirationDate = dto.getexpiration_date();
		if (expirationDate == null) return "–³ŠúŒÀ";
		return Utilities.convertDate(expirationDate);
	}

	public String getCreateTime() {
		return Utilities.convertTimestamp(dto.getcreate_time());
	}

	public String getUpdateTime() {
		return Utilities.convertTimestamp(dto.getupdate_time());
	}
}
