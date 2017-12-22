package jp.ats.sheepdog.infinical;

import static jp.ats.substrate.U.newLinkedList;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jp.ats.liverwort.ormapping.DataAccessHelper.RowLockOption;
import jp.ats.liverwort.selector.AnchorOptimizer;
import jp.ats.liverwort.sql.Relationship;
import jp.ats.liverwort.sql.binder.IntBinder;
import jp.ats.sheepdog.PermissionChecker;
import jp.ats.sheepdog.UserManager;
import jp.ats.sheepdog.common.MemberAction;
import jp.ats.sheepdog.common.ThreadAction;
import jp.ats.sheepdog.common.ThreadAction.EventKey;
import jp.ats.sheepdog.common.ThreadAction.GroupConditionComplementer;
import jp.ats.sheepdog.dataobjects.t_memberDAO.t_memberIterator;
import jp.ats.sheepdog.dataobjects.t_memberDTO;
import jp.ats.sheepdog.dataobjects.t_member_orderDAO;
import jp.ats.sheepdog.dataobjects.t_member_orderDTO;
import jp.ats.sheepdog.dataobjects.t_thread;
import jp.ats.sheepdog.dataobjects.t_threadDTO;
import jp.ats.sheepdog.dataobjects.t_userDTO;
import jp.ats.substrate.U;
import jp.ats.substrate.util.CollectionMap;
import jp.ats.webkit.infinical.AbstractInfinicalBuilder;
import jp.ats.webkit.infinical.Body;
import jp.ats.webkit.infinical.DateHeader;
import jp.ats.webkit.infinical.MemberHeader;

public class GroupInfinicalBuilder extends AbstractInfinicalBuilder {

	private static final AnchorOptimizer optimizer = AnchorOptimizer.getInstance();

	private static final String memberDelimiter = ",";

	private SheepdogMemberHeader[] headers;

	public GroupInfinicalBuilder(HttpServletRequest request) {
		super(request);
	}

	@Override
	public void moveMember(
		int thisMemberIndex,
		int targetMemberIndex,
		boolean isBefore) {
		String groupID = getRequest().getParameter("id");

		PermissionChecker.checkGroup(Integer.parseInt(groupID));

		t_member_orderDTO dto = new t_member_orderDAO().select(
			RowLockOption.FOR_UPDATE_WAIT,
			new IntBinder(UserManager.getUserID()),
			new IntBinder(Integer.parseInt(groupID)));

		String[] memberOrder = dto.getmember_order().split(memberDelimiter);

		LinkedList<String> list = newLinkedList();
		list.add(null);
		for (String member : memberOrder) {
			list.add(member);
			list.add(null);
		}

		int index = thisMemberIndex * 2 + 1;
		String thisMember = list.get(index);
		list.set(index, null);

		list.set(targetMemberIndex * 2 + 1 + (isBefore ? -1 : 1), thisMember);

		for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
			if (iterator.next() == null) iterator.remove();
		}

		dto.setmember_order(U.join(list, memberDelimiter));
		dto.setupdate_time(new Timestamp(System.currentTimeMillis()));

		dto.update();
	}

	@Override
	protected MemberHeader[] buildMemberHeaders() {
		String groupID = getRequest().getParameter("id");

		PermissionChecker.checkGroup(Integer.parseInt(groupID));

		t_memberIterator iterator = MemberAction.search(optimizer, groupID);

		Map<String, SheepdogMemberHeader> map = U.newHashMap();
		List<String> defaultOrder = U.newLinkedList();
		for (t_memberDTO dto : iterator) {
			t_userDTO userDTO = dto.gett_userByt_member_user_id_fkey();
			Integer userID = userDTO.getid();

			defaultOrder.add(userID.toString());

			map.put(
				userID.toString(),
				new SheepdogMemberHeader(userID, userDTO.getname(), U.join(
					new String[] {
						U.care(userDTO.getdepartment()),
						U.care(userDTO.gettitle()) },
					" ")));
		}

		t_member_orderDAO dao = new t_member_orderDAO();

		t_member_orderDTO dto = dao.select(
			RowLockOption.FOR_UPDATE_WAIT,
			new IntBinder(UserManager.getUserID()),
			new IntBinder(Integer.parseInt(groupID)));

		String[] memberOrder;
		if (dto == null) {
			dto = new t_member_orderDTO();
			dto.setowner(UserManager.getUserID());
			dto.setgroup_id(Integer.parseInt(groupID));

			memberOrder = defaultOrder.toArray(new String[defaultOrder.size()]);

			dto.setmember_order(U.join(memberOrder, memberDelimiter));

			dao.insert(dto);
		} else {
			memberOrder = dto.getmember_order().split(memberDelimiter);

			//もし、一度順序登録してからメンバーの変更があった場合、なるべく元の状態を保つ
			List<String> tempList = U.newLinkedList();

			for (String id : memberOrder) {
				if (defaultOrder.contains(id)) {
					tempList.add(id);
					defaultOrder.remove(id);
				}
			}

			tempList.addAll(defaultOrder);

			String[] newMemberOrder = tempList.toArray(new String[defaultOrder.size()]);

			String[] memberOrderClone = memberOrder.clone();
			String[] newMemberOrderClone = newMemberOrder.clone();

			Arrays.sort(memberOrderClone);
			Arrays.sort(newMemberOrderClone);
			if (!Arrays.equals(memberOrderClone, newMemberOrderClone)) {
				dto.setmember_order(U.join(newMemberOrder, memberDelimiter));
				dto.setupdate_time(new Timestamp(System.currentTimeMillis()));
				dto.update();

				memberOrder = newMemberOrder;
			}
		}

		List<SheepdogMemberHeader> list = newLinkedList();
		for (String userID : memberOrder) {
			list.add(map.get(userID));
		}

		headers = list.toArray(new SheepdogMemberHeader[list.size()]);
		return headers;
	}

	@Override
	protected Body[][] buildBodies() {
		final int currentUserID = UserManager.getUserID();
		final String groupID = getRequest().getParameter("id");

		DateHeader[] dateHeaders = getDateHeaders();

		final CollectionMap<EventKey, t_threadDTO> events = ThreadAction.selectEvents(
			ThreadAction.createApplyingLevelCondition(
				new GroupConditionComplementer() {

					@Override
					public String getApplyingLevelColumnName() {
						return t_thread.applying_level;
					}

					@Override
					public String getGroupIDColumnName() {
						return t_thread.group_id;
					}

					@Override
					public String getOwnerColumnName() {
						return t_thread.owner;
					}

					@Override
					public Relationship getThreadGroupRelationship() {
						return Relationship.getInstance(
							t_thread.RESOURCE_LOCATOR).find(
							t_thread.t_group_BY_t_thread_group_id_fkey);
					}
				},
				Integer.parseInt(groupID)),
			dateHeaders[0].getDate(),
			dateHeaders[dateHeaders.length - 1].getDate());

		Body[][] bodies = new Body[headers.length][];
		for (int i = 0; i < bodies.length; i++) {
			final int userID = headers[i].getUserID();
			List<Body> list = buildDates(new DateBuilderCallback<Body>() {

				@Override
				public Body create(
					Calendar calendar,
					String defaultCellCalssName) {
					EventKey key = new EventKey(userID, U.formatDate(
						"yyyyMMdd",
						calendar.getTime()));

					Collection<t_threadDTO> values = events.get(key);

					return new SheepdogBody(
						defaultCellCalssName,
						SheepdogBody.buildUserBody(
							currentUserID,
							userID,
							groupID,
							calendar,
							values.toArray(new t_threadDTO[values.size()])));
				}
			});

			bodies[i] = list.toArray(new Body[list.size()]);
		}

		return bodies;
	}
}
