package jp.ats.sheepdog.common;

import static jp.ats.substrate.U.isAvailable;

import javax.servlet.http.HttpServletRequest;

import jp.ats.liverwort.extension.SQLProxyBuilder;
import jp.ats.liverwort.ormapping.DataAccessHelper.RowLockOption;
import jp.ats.liverwort.selector.AnchorOptimizer;
import jp.ats.liverwort.selector.Optimizer;
import jp.ats.liverwort.sql.Column;
import jp.ats.liverwort.sql.Condition;
import jp.ats.liverwort.sql.ConditionFactory;
import jp.ats.liverwort.sql.ConditionFactory.NullComparisonOperator;
import jp.ats.liverwort.sql.OrderByClause;
import jp.ats.liverwort.sql.Relationship;
import jp.ats.liverwort.sql.binder.IntBinder;
import jp.ats.sheepdog.Pager;
import jp.ats.sheepdog.SearchResult;
import jp.ats.sheepdog.Sheepdog;
import jp.ats.sheepdog.UserManager;
import jp.ats.sheepdog.WrapIterator;
import jp.ats.sheepdog.common.subscribesql.SubscribeSQL;
import jp.ats.sheepdog.dataobjects.t_group;
import jp.ats.sheepdog.dataobjects.t_member;
import jp.ats.sheepdog.dataobjects.t_memberDAO;
import jp.ats.sheepdog.dataobjects.t_memberDAO.t_memberIterator;
import jp.ats.sheepdog.dataobjects.t_memberDTO;
import jp.ats.sheepdog.dataobjects.t_seal;
import jp.ats.sheepdog.dataobjects.t_sealDAO;
import jp.ats.sheepdog.dataobjects.t_subscribe;
import jp.ats.sheepdog.dataobjects.t_subscribeDAO;
import jp.ats.sheepdog.dataobjects.t_subscribeDAO.t_subscribeIterator;
import jp.ats.sheepdog.dataobjects.t_subscribeDTO;
import jp.ats.sheepdog.dataobjects.t_thread;

public class SubscribeAction {

	private static final SubscribeSQL proxy = SQLProxyBuilder.buildProxyObject(SubscribeSQL.class);

	private static final AnchorOptimizer threadOptimizer = AnchorOptimizer.getInstance();

	private static final AnchorOptimizer commentOptimizer = AnchorOptimizer.getInstance();

	private static final AnchorOptimizer memberOptimizer = AnchorOptimizer.getInstance();

	public static SearchResult<Subscribe> searchThread(
		HttpServletRequest request) {
		Condition condition = ConditionFactory.createNullCondition(
			NullComparisonOperator.IS_NOT_NULL,
			t_subscribe.group_for_thread);
		Relationship relation = Relationship.getInstance(t_subscribe.RESOURCE_LOCATOR);
		Column column = relation.find(
			t_subscribe.t_group_BY_t_subscribe_group_for_thread_fkey)
			.getColumn(t_group.delete_flag);
		condition.and(ConditionFactory.createCondition(column, new IntBinder(0)));
		return search(threadOptimizer, condition, request);
	}

	public static SearchResult<Subscribe> searchComment(
		HttpServletRequest request) {
		Condition condition = ConditionFactory.createNullCondition(
			NullComparisonOperator.IS_NOT_NULL,
			t_subscribe.thread_for_comment);
		Relationship relation = Relationship.getInstance(t_subscribe.RESOURCE_LOCATOR);
		Column column = relation.find(
			t_subscribe.t_thread_BY_t_subscribe_thread_for_comment_fkey)
			.getColumn(t_thread.delete_flag);
		condition.and(ConditionFactory.createCondition(column, new IntBinder(0)));
		return search(commentOptimizer, condition, request);
	}

	public static void subscribeGroupForThread(int id) {
		Condition condition = ConditionFactory.createCondition(UserManager.createCondition(t_subscribe.owner));
		condition.and(ConditionFactory.createCondition(
			t_subscribe.group_for_thread,
			new IntBinder(id)));

		t_subscribeDAO dao = new t_subscribeDAO();

		if (dao.count(condition) > 0) return;

		t_subscribeDTO subscribe = new t_subscribeDTO();
		subscribe.setgroup_for_thread(id);
		subscribe.setowner(UserManager.getUserID());
		dao.insert(subscribe);
	}

	public static void subscribeThreadForComment(int id) {
		Condition condition = ConditionFactory.createCondition(UserManager.createCondition(t_subscribe.owner));
		condition.and(ConditionFactory.createCondition(
			t_subscribe.thread_for_comment,
			new IntBinder(id)));

		t_subscribeDAO dao = new t_subscribeDAO();

		if (dao.count(condition) > 0) return;

		t_subscribeDTO subscribe = new t_subscribeDTO();
		subscribe.setthread_for_comment(id);
		subscribe.setowner(UserManager.getUserID());
		dao.insert(subscribe);
	}

	public static boolean hasSubscribeOnGroup(int id) {
		return hasSubscribe(id, t_subscribe.group_for_thread);
	}

	public static boolean hasSubscribeOnThread(int id) {
		return hasSubscribe(id, t_subscribe.thread_for_comment);
	}

	public static void deleteSubscribeOnGroup(int id) {
		deleteSubscribe(id, t_subscribe.group_for_thread);
	}

	public static void deleteSubscribeOnThread(int id) {
		deleteSubscribe(id, t_subscribe.thread_for_comment);
	}

	public static void sendForPublicThread(int threadID) {
		t_memberIterator iterator = new t_memberDAO().select(
			memberOptimizer,
			UserManager.createCondition(t_member.user_id),
			null);

		int owner = UserManager.getUserID();

		for (t_memberDTO dto : iterator) {
			int groupID = dto.getgroup_id();
			proxy.insertNoticeForThread(threadID, groupID, owner);
			proxy.insertSealForThread(threadID, groupID, owner);
		}
	}

	public static void sendForThread(int groupID, int threadID) {
		int owner = UserManager.getUserID();
		proxy.insertNoticeForThread(threadID, groupID, owner);
		proxy.insertSealForThread(threadID, groupID, owner);
	}

	public static void sendForComment(int threadID, int commentID) {
		int owner = UserManager.getUserID();
		proxy.insertNoticeForComment(commentID, threadID, owner);
		proxy.insertSealForComment(
			commentID,
			threadID,
			owner,
			threadID,
			threadID,
			owner);
	}

	public static void threadOpened(int threadID) {
		Condition condition = UserManager.createCondition(t_seal.owner);
		condition.and(ConditionFactory.createCondition(
			t_seal.thread_id,
			new IntBinder(threadID)));

		new t_sealDAO().delete(condition);
	}

	public static void commentOpened(Integer[] commentIDs) {
		Condition condition = UserManager.createCondition(t_seal.owner);

		Condition subCondition = ConditionFactory.createCondition();
		for (int id : commentIDs) {
			subCondition.or(ConditionFactory.createCondition(
				t_seal.comment_id,
				new IntBinder(id)));
		}

		if (!subCondition.isAvailable()) return;

		condition.and(subCondition);

		new t_sealDAO().delete(condition);
	}

	private static SearchResult<Subscribe> search(
		Optimizer optimizer,
		Condition condition,
		HttpServletRequest request) {
		condition.and(UserManager.createCondition(t_subscribe.owner));

		String pageString = request.getParameter("page");
		int page = isAvailable(pageString) ? Integer.parseInt(pageString) : 1;

		t_subscribeDAO dao = new t_subscribeDAO();

		Pager pager = new Pager(
			page,
			Sheepdog.DEFAULT_PAGE_COUNT,
			dao.count(condition));

		OrderByClause order = new OrderByClause();
		order.asc(t_subscribe.id);

		return new SearchResult<Subscribe>(new SubscribeIterator(
			new t_subscribeDAO().select(
				optimizer,
				condition,
				order,
				pager,
				RowLockOption.NONE)), pager);
	}

	private static boolean hasSubscribe(int id, String columnName) {
		Condition condition = ConditionFactory.createCondition(
			columnName,
			new IntBinder(id));
		condition.and(UserManager.createCondition(t_subscribe.owner));

		return new t_subscribeDAO().count(condition) > 0;
	}

	private static void deleteSubscribe(int id, String columnName) {
		Condition condition = ConditionFactory.createCondition(
			columnName,
			new IntBinder(id));
		condition.and(UserManager.createCondition(t_subscribe.owner));

		new t_subscribeDAO().delete(condition);
	}

	public static class SubscribeIterator
		extends WrapIterator<Subscribe, t_subscribeDTO> {

		private SubscribeIterator(t_subscribeIterator iterator) {
			super(iterator);
		}

		@Override
		protected Subscribe wrap(t_subscribeDTO src) {
			return new Subscribe(src);
		}
	}
}
