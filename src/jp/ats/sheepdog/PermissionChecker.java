package jp.ats.sheepdog;

import jp.ats.liverwort.selector.AnchorOptimizer;
import jp.ats.liverwort.sql.Condition;
import jp.ats.liverwort.sql.ConditionFactory;
import jp.ats.liverwort.sql.binder.IntBinder;
import jp.ats.sheepdog.common.ApplyingLevel;
import jp.ats.sheepdog.dataobjects.t_groupDAO;
import jp.ats.sheepdog.dataobjects.t_groupDTO;
import jp.ats.sheepdog.dataobjects.t_member;
import jp.ats.sheepdog.dataobjects.t_memberDAO;
import jp.ats.sheepdog.dataobjects.t_threadDAO;
import jp.ats.sheepdog.dataobjects.t_threadDTO;

public class PermissionChecker {

	private static final AnchorOptimizer groupOptimizer = AnchorOptimizer.getInstance();

	private static final AnchorOptimizer threadOptimizer = AnchorOptimizer.getInstance();

	public static boolean canStore(int ownerID) {
		if (!UserManager.isUserInRole(Role.USER)
			|| ownerID == UserManager.getUserID()) return true;
		return false;
	}

	public static void checkForStore(int ownerID) {
		if (!canStore(ownerID)) throw new IllegalStateException();
	}

	public static void checkGroup(int groupID) {
		if (!canAccess(new t_groupDAO().select(groupOptimizer, new IntBinder(
			groupID)))) throwGroupException();
	}

	public static boolean canAccess(t_groupDTO group) {
		if (group.getdelete_flag() == 1) return false;;

		//ユーザー以外はOK
		if (!UserManager.isUserInRole(Role.USER)) return true;

		//非公開かつ自分がオーナーではない場合NG
		if (ApplyingLevel.values()[group.getapplying_level()].equals(ApplyingLevel.非公開)
			&& UserManager.getUserID() != group.getowner()) return false;;

		//公開の場合OK
		if (!ApplyingLevel.values()[group.getapplying_level()].equals(ApplyingLevel.グループメンバーのみ公開)) return true;

		if (!isMember(group)) return false;

		return true;
	}

	public static void checkThread(int threadID) {
		checkThread(new t_threadDAO().select(threadOptimizer, new IntBinder(
			threadID)));
	}

	public static void checkThread(t_threadDTO event) {
		if (event.getdelete_flag() == 1) throwEventException();

		//ユーザー以外はOK
		if (!UserManager.isUserInRole(Role.USER)) return;

		//非公開かつ自分がオーナーではない場合NG
		if (ApplyingLevel.values()[event.getapplying_level()].equals(ApplyingLevel.非公開)
			&& UserManager.getUserID() != event.getowner()) throwEventException();

		//公開の場合OK
		if (!ApplyingLevel.values()[event.getapplying_level()].equals(ApplyingLevel.グループメンバーのみ公開)) return;

		if (!isMember(event.gett_groupByt_thread_group_id_fkey())) throwEventException();
	}

	private static boolean isMember(t_groupDTO group) {
		Condition condition = ConditionFactory.createCondition(
			t_member.group_id,
			new IntBinder(group.getid()));
		condition.and(UserManager.createCondition(t_member.user_id));

		return new t_memberDAO().count(condition) == 1;
	}

	private static void throwGroupException() {
		throw new ApplicationException("現在このグループは使用することができません。");
	}

	private static void throwEventException() {
		throw new ApplicationException("現在この"
			+ Sheepdog.EVENT_NAME
			+ "は使用することができません。");
	}
}
