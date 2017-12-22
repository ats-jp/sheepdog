package jp.ats.sheepdog.infinical;

import jp.ats.webkit.infinical.ConcreteMemberHeader;

public class SheepdogMemberHeader extends ConcreteMemberHeader {

	private final int userID;

	public SheepdogMemberHeader(
		int userID,
		String memberString,
		String description) {
		super(memberString, description);
		this.userID = userID;
	}

	public int getUserID() {
		return userID;
	}
}
