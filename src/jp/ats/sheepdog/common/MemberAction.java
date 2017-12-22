package jp.ats.sheepdog.common;

import jp.ats.liverwort.selector.AnchorOptimizer;
import jp.ats.liverwort.sql.Condition;
import jp.ats.liverwort.sql.ConditionFactory;
import jp.ats.liverwort.sql.OrderByClause;
import jp.ats.liverwort.sql.binder.IntBinder;
import jp.ats.sheepdog.Role;
import jp.ats.sheepdog.UserManager;
import jp.ats.sheepdog.dataobjects.t_groupDAO;
import jp.ats.sheepdog.dataobjects.t_groupDTO;
import jp.ats.sheepdog.dataobjects.t_member;
import jp.ats.sheepdog.dataobjects.t_memberDAO;
import jp.ats.sheepdog.dataobjects.t_memberDAO.t_memberIterator;
import jp.ats.sheepdog.dataobjects.t_memberDTO;
import jp.ats.sheepdog.dataobjects.t_userDAO;
import jp.ats.sheepdog.dataobjects.t_userDTO;

public class MemberAction {

	private static final AnchorOptimizer defaultOptimizer = AnchorOptimizer.getInstance();

	public static t_memberIterator search(
		AnchorOptimizer optimizer,
		String groupID) {
		Condition condition = ConditionFactory.createCondition(
			t_member.group_id,
			new IntBinder(Integer.parseInt(groupID)));

		OrderByClause order = new OrderByClause();
		order.asc(t_member.user_id);

		return new t_memberDAO().select(optimizer == null
			? defaultOptimizer
			: optimizer, condition, order);
	}

	static void addMember(int groupID, int userID) {
		check(groupID, userID);

		t_memberDTO dto = new t_memberDTO();
		dto.setuser_id(userID);
		dto.setgroup_id(groupID);
		new t_memberDAO().insert(dto);
	}

	static void removeMember(int groupID, int userID) {
		check(groupID, userID);

		Condition condition = ConditionFactory.createCondition(
			t_member.group_id,
			new IntBinder(groupID));
		condition.and(ConditionFactory.createCondition(
			t_member.user_id,
			new IntBinder(userID)));

		if (new t_memberDAO().delete(condition) != 1) throw new IllegalStateException();
	}

	private static void check(int groupID, int userID) {
		t_groupDTO group = new t_groupDAO().select(new IntBinder(groupID));

		if (!UserManager.isUserInRole(Role.USER)) return;

		if (UserManager.getUserID() != group.getowner()) throw new IllegalStateException();

		t_userDTO user = new t_userDAO().select(new IntBinder(userID));

		//ユーザー権限では、ユーザー権限のものしかメンバーに追加できない
		if (!Role.values()[user.getrole()].equals(Role.USER)) throw new IllegalStateException();
	}

	public static class MemberFinder {

		private final t_memberIterator iterator;

		private boolean hasNext;

		private t_memberDTO next;

		public MemberFinder(t_memberIterator iterator) {
			this.iterator = iterator;
			next();
		}

		public boolean find(int userID) {
			while (hasNext) {
				int memberUserID = next.getuser_id();
				if (userID < memberUserID) return false;
				if (userID == memberUserID) return true;
				next();
			}

			return false;
		}

		public long getCurrentMemberID() {
			return next.getid();
		}

		private void next() {
			hasNext = iterator.hasNext();
			if (hasNext) next = iterator.next();
		}
	}
}
