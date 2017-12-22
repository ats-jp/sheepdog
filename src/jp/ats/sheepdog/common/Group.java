package jp.ats.sheepdog.common;

import jp.ats.sheepdog.PermissionChecker;
import jp.ats.sheepdog.User;
import jp.ats.sheepdog.Utilities;
import jp.ats.sheepdog.dataobjects.t_groupDTO;
import jp.ats.substrate.U;
import jp.ats.webkit.util.Sanitizer;

public class Group {

	private final t_groupDTO dto;

	public Group(t_groupDTO dto) {
		this.dto = dto;
	}

	public t_groupDTO getDTO() {
		return dto;
	}

	public int getID() {
		return dto.getid();
	}

	public String getName() {
		return U.care(Sanitizer.sanitize(dto.getname()));
	}

	public String getDescription() {
		return U.care(Sanitizer.sanitize(dto.getdescription()));
	}

	public String getOmitDescription(int length) {
		return Sanitizer.sanitize(Utilities.createOmitString(
			dto.getdescription(),
			length));
	}

	public User getOwner() {
		return new User(dto.gett_userByt_group_owner_fkey());
	}

	public int getOwnerUserID() {
		return dto.getowner();
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

	public boolean canChange() {
		return PermissionChecker.canStore(dto.getowner());
	}
}
