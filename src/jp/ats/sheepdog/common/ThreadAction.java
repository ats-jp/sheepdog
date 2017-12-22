package jp.ats.sheepdog.common;

import static jp.ats.substrate.U.isAvailable;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import jp.ats.liverwort.ormapping.DataAccessHelper.RowLockOption;
import jp.ats.liverwort.selector.AnchorOptimizer;
import jp.ats.liverwort.sql.Binder;
import jp.ats.liverwort.sql.Condition;
import jp.ats.liverwort.sql.ConditionFactory;
import jp.ats.liverwort.sql.ConditionFactory.ComparisonOperator;
import jp.ats.liverwort.sql.ConditionFactory.NullComparisonOperator;
import jp.ats.liverwort.sql.OrderByClause;
import jp.ats.liverwort.sql.Relationship;
import jp.ats.liverwort.sql.binder.IntBinder;
import jp.ats.liverwort.sql.binder.StringBinder;
import jp.ats.sheepdog.ApplicationException;
import jp.ats.sheepdog.NewIDInserter;
import jp.ats.sheepdog.Pager;
import jp.ats.sheepdog.PermissionChecker;
import jp.ats.sheepdog.Role;
import jp.ats.sheepdog.SearchResult;
import jp.ats.sheepdog.Sheepdog;
import jp.ats.sheepdog.UserManager;
import jp.ats.sheepdog.Utilities;
import jp.ats.sheepdog.WrapIterator;
import jp.ats.sheepdog.dataobjects.t_content;
import jp.ats.sheepdog.dataobjects.t_contentDAO;
import jp.ats.sheepdog.dataobjects.t_contentDAO.t_contentIterator;
import jp.ats.sheepdog.dataobjects.t_contentDTO;
import jp.ats.sheepdog.dataobjects.t_groupDTO;
import jp.ats.sheepdog.dataobjects.t_member;
import jp.ats.sheepdog.dataobjects.t_thread;
import jp.ats.sheepdog.dataobjects.t_threadDAO;
import jp.ats.sheepdog.dataobjects.t_threadDAO.t_threadIterator;
import jp.ats.sheepdog.dataobjects.t_threadDTO;
import jp.ats.sheepdog.dataobjects.v_thread;
import jp.ats.sheepdog.dataobjects.v_threadDAO;
import jp.ats.sheepdog.dataobjects.v_threadDAO.v_threadIterator;
import jp.ats.sheepdog.dataobjects.v_threadDTO;
import jp.ats.sheepdog.form.ThreadForm;
import jp.ats.substrate.U;
import jp.ats.substrate.revision.OldRevisionException;
import jp.ats.substrate.revision.Precondition;
import jp.ats.substrate.revision.SimpleRevisionRepository;
import jp.ats.substrate.util.CollectionMap;
import jp.ats.webkit.jspod.JspodManager;
import jp.ats.webkit.jspod.JspodRevisionManager;

public class ThreadAction {

	private static final JspodRevisionManager<Integer> manager = new JspodRevisionManager<Integer>(
		new SimpleRevisionRepository<Integer>());

	private static final AnchorOptimizer optimizer = AnchorOptimizer.getInstance();

	private static final AnchorOptimizer rawEventOptimzer = AnchorOptimizer.getInstance();

	private static final AnchorOptimizer updateOptimizer = AnchorOptimizer.getInstance();

	private static final AnchorOptimizer contentsOptimizer = AnchorOptimizer.getInstance();

	private static final AnchorOptimizer contentOptimizer = AnchorOptimizer.getInstance();

	public static Thread prepareForEdit(ThreadForm form, String id) {
		manager.start(new Integer(id));

		Thread thread = getThread(id);

		form.setTitle(thread.getTitle());
		form.setBody(thread.getBody());
		form.setApplyingLevel(String.valueOf(thread.getApplyingLevel()
			.ordinal()));
		form.setDate(thread.getCalendar());

		Integer groupID = thread.getGroupID();
		if (groupID != null) form.setGroupID(groupID.toString());

		return thread;
	}

	public static void store(HttpServletRequest request) throws IOException {
		if (!JspodManager.isValidToken()) throw new ApplicationException(
			"この操作は既に完了しています。");

		ThreadForm form = new ThreadForm(request);

		int contentID;
		if (Utilities.isUpdate(request.getParameter("mode"))) {
			contentID = update(form);
		} else {
			contentID = insert(form);
		}

		AttachedFileAction.insertContentAttachedFiles(request, contentID);

		JspodManager.removeToken();
	}

	public static void delete(String id) {
		if (!JspodManager.isValidToken()) throw new ApplicationException(
			"この操作は既に完了しています。");

		manager.start(new Integer(id));

		final t_threadDTO dto = new t_threadDAO().select(
			RowLockOption.FOR_UPDATE_WAIT,
			new Binder[] { new IntBinder(manager.getCurrentTarget()) });

		PermissionChecker.checkForStore(dto.getowner());

		dto.setdelete_flag(1);
		dto.setupdate_time(new Timestamp(new Date().getTime()));

		try {
			manager.finish(new Precondition() {

				@Override
				public boolean execute() {
					return dto.update();
				}
			});
		} catch (OldRevisionException e) {
			throw new ApplicationException("この"
				+ Sheepdog.EVENT_NAME
				+ "はすでに他で更新されています。");
		}

		JspodManager.removeToken();
	}

	public static interface GroupConditionComplementer {

		String getApplyingLevelColumnName();

		String getGroupIDColumnName();

		String getOwnerColumnName();

		Relationship getThreadGroupRelationship();
	}

	public static Condition createApplyingLevelCondition(
		GroupConditionComplementer complementer,
		int groupID) {
		if (!UserManager.isUserInRole(Role.USER)) return ConditionFactory.createCondition();

		Condition condition = ConditionFactory.createCondition(
			complementer.getApplyingLevelColumnName(),
			new IntBinder(ApplyingLevel.グループ外全てに公開.ordinal()));

		condition.or(UserManager.createCondition(complementer.getOwnerColumnName()));

		Condition memberOnlyCondition = ConditionFactory.createCondition(
			complementer.getGroupIDColumnName(),
			new IntBinder(groupID));
		memberOnlyCondition.and(ConditionFactory.createCondition(
			complementer.getApplyingLevelColumnName(),
			new IntBinder(ApplyingLevel.グループメンバーのみ公開.ordinal())));
		memberOnlyCondition.and(ConditionFactory.createSubQueryCondition(
			complementer.getThreadGroupRelationship(),
			Relationship.getInstance(t_member.RESOURCE_LOCATOR).find(
				t_member.t_group_BY_t_member_group_id_fkey),
			UserManager.createCondition(t_member.user_id)));

		condition.or(memberOnlyCondition);

		return condition;
	}

	public static boolean isEventType(HttpServletRequest request) {
		if (U.isAvailable(request.getParameter("date"))) {
			return true;
		}

		return Boolean.parseBoolean(request.getParameter("event"));
	}

	public static boolean isDateAvailable(HttpServletRequest request) {
		return U.isAvailable(request.getParameter("date"));
	}

	public static String createPageTitle(HttpServletRequest request) {
		String title = (isEventType(request)
			? Sheepdog.EVENT_NAME
			: Sheepdog.TOPIC_NAME) + "一覧";
		String calendar = request.getParameter("date");
		if (U.isAvailable(calendar)) {
			title = title + " (" + Utilities.convertMMdd(calendar) + ")";
		}

		return title;
	}

	public static SearchResult<Thread> searchTopics(
		int groupID,
		String pageString) {
		Condition condition = createApplyingLevelCondition(
			new GroupConditionComplementer() {

				@Override
				public String getApplyingLevelColumnName() {
					return v_thread.applying_level;
				}

				@Override
				public String getGroupIDColumnName() {
					return v_thread.group_id;
				}

				@Override
				public String getOwnerColumnName() {
					return v_thread.owner;
				}

				@Override
				public Relationship getThreadGroupRelationship() {
					return Relationship.getInstance(v_thread.RESOURCE_LOCATOR)
						.find(v_thread.t_group_BY_v_thread_group_id_fkey);
				}
			}, groupID);

		condition.and(ConditionFactory.createCondition(
			v_thread.group_id,
			new IntBinder(groupID)));

		condition.and(ConditionFactory.createCondition(
			v_thread.event_flag,
			new IntBinder(0)));

		OrderByClause order = new OrderByClause();
		order.desc(v_thread.update_time);

		int page = isAvailable(pageString) ? Integer.parseInt(pageString) : 1;

		v_threadDAO dao = new v_threadDAO();

		Pager pager = new Pager(
			page,
			Sheepdog.DEFAULT_PAGE_COUNT,
			dao.count(condition));

		return new SearchResult<Thread>(new ThreadIterator(dao.select(
			optimizer,
			condition,
			order,
			pager,
			RowLockOption.NONE)), pager);
	}

	public static SearchResult<Thread> search(HttpServletRequest request) {
		String groupIDString = request.getParameter("groupID");

		Condition condition = ConditionFactory.createCondition();
		if (U.isAvailable(groupIDString)) {
			int groupID = Integer.parseInt(groupIDString);

			condition.and(createApplyingLevelCondition(
				new GroupConditionComplementer() {

					@Override
					public String getApplyingLevelColumnName() {
						return v_thread.applying_level;
					}

					@Override
					public String getGroupIDColumnName() {
						return v_thread.group_id;
					}

					@Override
					public String getOwnerColumnName() {
						return v_thread.owner;
					}

					@Override
					public Relationship getThreadGroupRelationship() {
						return Relationship.getInstance(
							v_thread.RESOURCE_LOCATOR).find(
							v_thread.t_group_BY_v_thread_group_id_fkey);
					}
				},
				groupID));

			Condition groupCondition = ConditionFactory.createCondition(
				v_thread.group_id,
				new IntBinder(groupID));
			groupCondition.or(ConditionFactory.createNullCondition(
				NullComparisonOperator.IS_NULL,
				v_thread.group_id));

			condition.and(groupCondition);
		}

		String calendar = request.getParameter("date");

		if (U.isAvailable(calendar)) {
			condition.and(ConditionFactory.createCondition(
				v_thread.calendar,
				new StringBinder(calendar)));
		}

		condition.and(ConditionFactory.createCondition(
			v_thread.event_flag,
			new IntBinder(isEventType(request) ? 1 : 0)));

		String userIDString = request.getParameter("userID");
		int userID;
		if (!U.isAvailable(userIDString)) {
			userID = UserManager.getUserID();
		} else {
			userID = Integer.parseInt(userIDString);
		}

		condition.and(ConditionFactory.createCondition(
			v_thread.owner,
			new IntBinder(userID)));

		OrderByClause order = new OrderByClause();
		order.desc(v_thread.update_time);

		String pageString = request.getParameter("page");
		int page = isAvailable(pageString) ? Integer.parseInt(pageString) : 1;

		v_threadDAO dao = new v_threadDAO();

		Pager pager = new Pager(
			page,
			Sheepdog.DEFAULT_PAGE_COUNT,
			dao.count(condition));

		return new SearchResult<Thread>(new ThreadIterator(dao.select(
			optimizer,
			condition,
			order,
			pager,
			RowLockOption.NONE)), pager);
	}

	public static Content searchContent(String contentID) {
		return new Content(new t_contentDAO().select(
			contentOptimizer,
			new IntBinder(Integer.parseInt(contentID))));
	}

	public static SearchResult<Content> searchContent(HttpServletRequest request) {
		Condition condition = ConditionFactory.createCondition(
			t_content.thread_id,
			new IntBinder(Integer.parseInt(request.getParameter("id"))));

		OrderByClause order = new OrderByClause();
		order.desc(t_content.id);

		String pageString = request.getParameter("page");
		int page = isAvailable(pageString) ? Integer.parseInt(pageString) : 1;

		t_contentDAO dao = new t_contentDAO();

		Pager pager = new Pager(
			page,
			Sheepdog.DEFAULT_PAGE_COUNT,
			dao.count(condition));

		return new SearchResult<Content>(new ContentIterator(dao.select(
			contentsOptimizer,
			condition,
			order,
			pager,
			RowLockOption.NONE)), pager);
	}

	public static CollectionMap<EventKey, t_threadDTO> selectEvents(
		Condition condition,
		Date fromDate,
		Date toDate) {
		condition.and(ConditionFactory.createCondition(
			t_thread.delete_flag,
			new IntBinder(0)));

		condition.and(ConditionFactory.createComparisonCondition(
			ComparisonOperator.GE,
			t_thread.calendar,
			new StringBinder(U.formatDate("yyyyMMdd", fromDate))));
		condition.and(ConditionFactory.createComparisonCondition(
			ComparisonOperator.LE,
			t_thread.calendar,
			new StringBinder(U.formatDate("yyyyMMdd", toDate))));

		OrderByClause order = new OrderByClause();
		order.desc(t_thread.update_time);

		t_threadIterator iterator = new t_threadDAO().select(condition, order);

		CollectionMap<EventKey, t_threadDTO> result = CollectionMap.newInstance();
		for (t_threadDTO dto : iterator) {
			result.put(new EventKey(dto.getowner(), dto.getcalendar()), dto);
		}

		return result;
	}

	public static Thread getThread(String id) {
		return new Thread(new v_threadDAO().select(optimizer, new IntBinder(
			Integer.parseInt(id))));
	}

	public static t_threadDTO getThreadDTO(int id) {
		return new t_threadDAO().select(rawEventOptimzer, new IntBinder(id));
	}

	public static class EventKey {

		private final int userID;

		private final String date;

		public EventKey(int userID, String date) {
			this.userID = userID;
			this.date = date;
		}

		@Override
		public boolean equals(Object object) {
			EventKey another = (EventKey) object;
			return userID == another.userID && date.equals(another.date);
		}

		@Override
		public int hashCode() {
			return U.sumHashCodes(new Object[] { userID, date });
		}
	}

	private static int insert(ThreadForm form) {
		t_threadDTO dto = new t_threadDTO();

		setValues(form, dto);

		String date = form.getDate();
		if (U.isAvailable(date)) {
			dto.setcalendar(date);
			dto.setevent_flag(1);
		} else {
			dto.setevent_flag(0);
		}

		int owner = UserManager.getUserID();

		dto.setowner(owner);

		String groupID = form.getGroupID();
		if (U.isAvailable(groupID)) {
			dto.setgroup_id(new Integer(groupID));

			t_groupDTO group = GroupAction.getGroupDTO(Integer.parseInt(groupID));

			//他人のトピックが登録された時点でグループレベルを下げれなくする
			if (group.getowner().intValue() != owner) {
				group.setprivate_flag(0);
				group.update();
			}
		}

		int newID = (int) NewIDInserter.insert(t_thread.RESOURCE_LOCATOR, dto);

		SubscribeAction.subscribeThreadForComment(newID);

		//非公開でなければ通知する
		if (ApplyingLevel.非公開.ordinal() != Integer.parseInt(form.getApplyingLevel())) {
			if (U.isAvailable(groupID)) {
				SubscribeAction.sendForThread(Integer.parseInt(groupID), newID);
			} else {
				SubscribeAction.sendForPublicThread(newID);
			}
		}

		return insertContent(newID, form.getTitle(), form.getBody());
	}

	private static int update(ThreadForm form) {
		final t_threadDTO dto = new t_threadDAO().select(
			RowLockOption.FOR_UPDATE_WAIT,
			new Binder[] { new IntBinder(manager.getCurrentTarget()) });

		PermissionChecker.checkForStore(dto.getowner());

		v_threadDTO thread = new v_threadDAO().select(
			updateOptimizer,
			new IntBinder(dto.getid()));

		ApplyingLevel oldApplyingLevel = ApplyingLevel.values()[dto.getapplying_level()];
		ApplyingLevel newApplyingLevel = ApplyingLevel.values()[Integer.parseInt(form.getApplyingLevel())];

		//プライベート状態ではなく公開レベルを下げたらエラー
		if (dto.getprivate_flag() == 0) {
			if (!oldApplyingLevel.canChangeTo(newApplyingLevel)) throw new IllegalStateException();
		}

		//非公開からそれ以外へ変更された場合は通知する
		if (oldApplyingLevel.equals(ApplyingLevel.非公開)
			&& !newApplyingLevel.equals(ApplyingLevel.非公開)) {
			if (dto.getgroup_id() != null) {
				SubscribeAction.sendForThread(dto.getgroup_id(), dto.getid());
			} else {
				SubscribeAction.sendForPublicThread(dto.getid());
			}
		}

		setValues(form, dto);

		int newContentID = insertContent(
			dto.getid(),
			form.getTitle(),
			form.getBody());

		dto.setupdate_time(new Timestamp(System.currentTimeMillis()));

		AttachedFileAction.changeEvent(
			thread.getcontent_id(),
			newContentID,
			form.getEjectArray());

		try {
			manager.finish(new Precondition() {

				@Override
				public boolean execute() {
					return dto.update();
				}
			});
		} catch (OldRevisionException e) {
			String type = dto.getevent_flag().intValue() == 1
				? Sheepdog.EVENT_NAME
				: Sheepdog.TOPIC_NAME;
			throw new ApplicationException("この" + type + "はすでに他で更新されています。");
		}

		return newContentID;
	}

	private static void setValues(ThreadForm form, t_threadDTO dto) {
		dto.setlast_title(form.getTitle());
		dto.setapplying_level(new Integer(form.getApplyingLevel()));
	}

	private static int insertContent(int eventID, String title, String body) {
		t_contentDTO dto = new t_contentDTO();
		dto.setthread_id(eventID);
		dto.settitle(title);
		dto.setbody(body);
		dto.setowner(UserManager.getUserID());
		return (int) NewIDInserter.insert(t_content.RESOURCE_LOCATOR, dto);
	}

	public static class ThreadIterator
		extends WrapIterator<Thread, v_threadDTO> {

		private ThreadIterator(v_threadIterator iterator) {
			super(iterator);
		}

		@Override
		protected Thread wrap(v_threadDTO src) {
			return new Thread(src);
		}
	}

	public static class ContentIterator
		extends WrapIterator<Content, t_contentDTO> {

		private ContentIterator(t_contentIterator iterator) {
			super(iterator);
		}

		@Override
		protected Content wrap(t_contentDTO src) {
			return new Content(src);
		}
	}
}
