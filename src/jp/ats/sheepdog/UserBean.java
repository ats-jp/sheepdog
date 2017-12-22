package jp.ats.sheepdog;

import static jp.ats.substrate.U.care;

import java.io.Serializable;
import java.util.Date;

import jp.ats.sheepdog.dataobjects.t_userDTO;
import jp.ats.webkit.util.Sanitizer;

public class UserBean implements Serializable {

	private static final long serialVersionUID = -3295421297093320389L;

	private final int id;

	private final String mailAddress;

	private final String name;

	private final Role role;

	private final String department;

	private final String title;

	private final Date expirationDate;

	private final Date createTime;

	private final Date updateTime;

	public UserBean(t_userDTO dto) {
		id = dto.getid();
		mailAddress = dto.getmail_address();
		name = dto.getname();
		role = Role.values()[dto.getrole()];
		department = dto.getdepartment();
		title = dto.gettitle();
		expirationDate = dto.getexpiration_date();
		createTime = dto.getcreate_time();
		updateTime = dto.getupdate_time();
	}

	public int getID() {
		return id;
	}

	public String getMailAddress() {
		return Sanitizer.sanitize(mailAddress);
	}

	public String getName() {
		return Sanitizer.sanitize(name);
	}

	public Role getRole() {
		return role;
	}

	public String getDepartment() {
		return Sanitizer.sanitize(care(department));
	}

	public String getTitle() {
		return Sanitizer.sanitize(care(title));
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public String getExpirationDateString() {
		return Utilities.convertDate(expirationDate);
	}

	public String getCreateTimeString() {
		return Utilities.convertTimestamp(createTime);
	}

	public String getUpdateTimeString() {
		return Utilities.convertTimestamp(updateTime);
	}
}
