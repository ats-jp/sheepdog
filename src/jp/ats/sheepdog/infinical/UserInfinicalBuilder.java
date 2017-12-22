package jp.ats.sheepdog.infinical;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.ats.liverwort.sql.ConditionFactory;
import jp.ats.sheepdog.UserBean;
import jp.ats.sheepdog.UserManager;
import jp.ats.sheepdog.common.ThreadAction;
import jp.ats.sheepdog.common.ThreadAction.EventKey;
import jp.ats.sheepdog.dataobjects.t_threadDTO;
import jp.ats.substrate.U;
import jp.ats.substrate.util.CollectionMap;
import jp.ats.webkit.infinical.AbstractInfinicalBuilder;
import jp.ats.webkit.infinical.Body;
import jp.ats.webkit.infinical.DateHeader;
import jp.ats.webkit.infinical.MemberHeader;

public class UserInfinicalBuilder extends AbstractInfinicalBuilder {

	private MemberHeader memberHeader;

	public UserInfinicalBuilder(HttpServletRequest request) {
		super(request);
	}

	@Override
	public void moveMember(
		int thisMemberIndex,
		int targetMemberIndex,
		boolean isBefore) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected MemberHeader[] buildMemberHeaders() {
		UserBean user = UserManager.getUser();
		memberHeader = new SheepdogMemberHeader(
			user.getID(),
			user.getName(),
			U.join(new String[] { user.getDepartment(), user.getTitle() }, " "));

		return new MemberHeader[] { memberHeader };
	}

	@Override
	protected Body[][] buildBodies() {
		Body[][] bodies = new Body[1][];
		final int userID = UserManager.getUserID();

		DateHeader[] dateHeaders = getDateHeaders();

		final CollectionMap<EventKey, t_threadDTO> events = ThreadAction.selectEvents(
			ConditionFactory.createCondition(),
			dateHeaders[0].getDate(),
			dateHeaders[dateHeaders.length - 1].getDate());

		List<Body> list = buildDates(new DateBuilderCallback<Body>() {

			@Override
			public Body create(Calendar calendar, String defaultCellCalssName) {
				EventKey key = new EventKey(userID, U.formatDate(
					"yyyyMMdd",
					calendar.getTime()));

				Collection<t_threadDTO> values = events.get(key);

				return new SheepdogBody(
					defaultCellCalssName,
					SheepdogBody.buildUserBody(
						userID,
						userID,
						"",
						calendar,
						values.toArray(new t_threadDTO[values.size()])));
			}
		});

		bodies[0] = list.toArray(new Body[list.size()]);

		return bodies;
	}
}
