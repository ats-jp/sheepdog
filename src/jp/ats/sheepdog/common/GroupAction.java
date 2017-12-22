package jp.ats.sheepdog.common;

import static jp.ats.substrate.U.isAvailable;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import jp.ats.liverwort.ormapping.DataAccessHelper.RowLockOption;
import jp.ats.liverwort.selector.AnchorOptimizer;
import jp.ats.liverwort.sql.Binder;
import jp.ats.liverwort.sql.Condition;
import jp.ats.liverwort.sql.ConditionFactory;
import jp.ats.liverwort.sql.OrderByClause;
import jp.ats.liverwort.sql.Relationship;
import jp.ats.liverwort.sql.binder.IntBinder;
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
import jp.ats.sheepdog.dataobjects.t_group;
import jp.ats.sheepdog.dataobjects.t_groupDAO;
import jp.ats.sheepdog.dataobjects.t_groupDAO.t_groupIterator;
import jp.ats.sheepdog.dataobjects.t_groupDTO;
import jp.ats.sheepdog.dataobjects.t_member;
import jp.ats.sheepdog.form.GroupForm;
import jp.ats.substrate.revision.OldRevisionException;
import jp.ats.substrate.revision.Precondition;
import jp.ats.substrate.revision.SimpleRevisionRepository;
import jp.ats.webkit.jspod.JspodManager;
import jp.ats.webkit.jspod.JspodRevisionManager;

public class GroupAction {

	private static final JspodRevisionManager<Integer> manager = new JspodRevisionManager<Integer>(
		new SimpleRevisionRepository<Integer>());

	private static final AnchorOptimizer optimizer = AnchorOptimizer.getInstance();

	private static final AnchorOptimizer applyingLevelOptimizer = AnchorOptimizer.getInstance();

	public static t_groupDTO prepareForEdit(GroupForm form, String id) {
		manager.start(new Integer(id));

		t_groupDTO dto = new t_groupDAO().select(
			optimizer,
			new Binder[] { new IntBinder(new Integer(id)) });

		if (dto.getdelete_flag() == 1) throw new IllegalStateException();

		form.setName(dto.getname());
		form.setDescription(dto.getdescription());
		form.setApplyingLevel(dto.getapplying_level().toString());
		form.setOwner(dto.gett_userByt_group_owner_fkey().getname());

		return dto;
	}

	public static Group getGroup(String id) {
		return new Group(getGroupDTO(Integer.parseInt(id)));
	}

	public static t_groupDTO getGroupDTO(int id) {
		return new t_groupDAO().select(new IntBinder(id));
	}

	public static SearchResult<Group> searchAll(HttpServletRequest request) {
		return searchInternal(request, createCondition(UserManager.getUserID()));
	}

	public static int getApplyingLevel(String groupID) {
		return new t_groupDAO().select(
			applyingLevelOptimizer,
			new IntBinder(Integer.parseInt(groupID))).getapplying_level();
	}

	private static Condition createCondition(int userID) {
		if (!UserManager.isUserInRole(Role.USER)) return ConditionFactory.createCondition();

		Condition condition = ConditionFactory.createCondition(
			t_group.owner,
			new IntBinder(userID));
		condition.or(ConditionFactory.createCondition(
			t_group.applying_level,
			new IntBinder(ApplyingLevel.グループ外全てに公開.ordinal())));

		Condition memberOnlyCondition = ConditionFactory.createCondition(
			t_group.applying_level,
			new IntBinder(ApplyingLevel.グループメンバーのみ公開.ordinal()));
		memberOnlyCondition.and(ConditionFactory.createSubQueryCondition(
			Relationship.getInstance(t_group.RESOURCE_LOCATOR),
			Relationship.getInstance(t_member.RESOURCE_LOCATOR).find(
				t_member.t_group_BY_t_member_group_id_fkey),
			ConditionFactory.createCondition(t_member.user_id, new IntBinder(
				userID))));
		condition.or(memberOnlyCondition);

		return condition;
	}

	public static SearchResult<Group> searchMyGroups(HttpServletRequest request) {
		return searchInternal(
			request,
			UserManager.createCondition(t_group.owner));
	}

	public static SearchResult<Group> searchMemberGroups(
		HttpServletRequest request) {
		Condition condition = ConditionFactory.createCondition();

		condition = ConditionFactory.createCondition(
			t_group.applying_level,
			new IntBinder(ApplyingLevel.非公開.ordinal()));
		condition.reverse();

		condition.or(UserManager.createCondition(t_group.owner));

		condition.and(ConditionFactory.createSubQueryCondition(
			Relationship.getInstance(t_group.RESOURCE_LOCATOR),
			Relationship.getInstance(t_member.RESOURCE_LOCATOR).find(
				t_member.t_group_BY_t_member_group_id_fkey),
			ConditionFactory.createCondition(t_member.user_id, new IntBinder(
				UserManager.getUserID()))));

		return searchInternal(request, condition);
	}

	public static void store(HttpServletRequest request) {
		if (!JspodManager.isValidToken()) throw new ApplicationException(
			"この操作は既に完了しています。");

		GroupForm form = new GroupForm(request);

		if (Utilities.isUpdate(request.getParameter("mode"))) {
			update(form);
		} else {
			insert(form);
		}

		JspodManager.removeToken();
	}

	public static void delete(String id) {
		if (!JspodManager.isValidToken()) throw new ApplicationException(
			"この操作は既に完了しています。");

		manager.start(new Integer(id));

		final t_groupDTO dto = new t_groupDAO().select(
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
			throw new ApplicationException("このグループはすでに他で更新されています。");
		}

		JspodManager.removeToken();
	}

	private static SearchResult<Group> searchInternal(
		HttpServletRequest request,
		Condition condition) {
		condition.and(ConditionFactory.createCondition(
			t_group.delete_flag,
			new IntBinder(0)));

		String pageString = request.getParameter("page");
		int page = isAvailable(pageString) ? Integer.parseInt(pageString) : 1;

		t_groupDAO dao = new t_groupDAO();

		Pager pager = new Pager(
			page,
			Sheepdog.DEFAULT_PAGE_COUNT,
			dao.count(condition));

		OrderByClause order = new OrderByClause();
		order.asc(t_group.id);

		return new SearchResult<Group>(new GroupIterator(
			new t_groupDAO().select(
				optimizer,
				condition,
				order,
				pager,
				RowLockOption.NONE)), pager);
	}

	private static void insert(GroupForm form) {
		t_groupDTO group = new t_groupDTO();

		setValues(form, group);

		group.setname(form.getName());

		int userID = UserManager.getUserID();

		group.setowner(userID);

		int newGroupID = (int) NewIDInserter.insert(
			t_group.RESOURCE_LOCATOR,
			group);

		SubscribeAction.subscribeGroupForThread(newGroupID);

		MemberAction.addMember(newGroupID, userID);
	}

	private static void update(GroupForm form) {
		final t_groupDTO dto = new t_groupDAO().select(
			RowLockOption.FOR_UPDATE_WAIT,
			new Binder[] { new IntBinder(manager.getCurrentTarget()) });

		PermissionChecker.checkForStore(dto.getowner());

		//プライベート状態ではなく公開レベルを下げたらエラー
		if (dto.getprivate_flag() == 0) {
			ApplyingLevel oldApplyingLevel = ApplyingLevel.values()[dto.getapplying_level()];
			ApplyingLevel newApplyingLevel = ApplyingLevel.values()[Integer.parseInt(form.getApplyingLevel())];
			if (!oldApplyingLevel.canChangeTo(newApplyingLevel)) throw new IllegalStateException();
		}

		setValues(form, dto);

		dto.setupdate_time(new Timestamp(new Date().getTime()));

		try {
			manager.finish(new Precondition() {

				@Override
				public boolean execute() {
					return dto.update();
				}
			});
		} catch (OldRevisionException e) {
			throw new ApplicationException("このグループはすでに他で更新されています。");
		}
	}

	private static void setValues(GroupForm form, t_groupDTO dto) {
		dto.setname(form.getName());
		dto.setdescription(form.getDescription());
		dto.setapplying_level(new Integer(form.getApplyingLevel()));
	}

	public static class GroupIterator extends WrapIterator<Group, t_groupDTO> {

		private GroupIterator(t_groupIterator iterator) {
			super(iterator);
		}

		@Override
		protected Group wrap(t_groupDTO src) {
			return new Group(src);
		}
	}
}
