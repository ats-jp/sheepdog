package jp.ats.sheepdog.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ats.liverwort.ormapping.DataAccessHelper.RowLockOption;
import jp.ats.liverwort.selector.AnchorOptimizer;
import jp.ats.liverwort.sql.Column;
import jp.ats.liverwort.sql.Condition;
import jp.ats.liverwort.sql.ConditionFactory;
import jp.ats.liverwort.sql.ConditionFactory.NullComparisonOperator;
import jp.ats.liverwort.sql.OrderByClause;
import jp.ats.liverwort.sql.Relationship;
import jp.ats.liverwort.sql.SQLAdjuster;
import jp.ats.liverwort.sql.binder.IntBinder;
import jp.ats.liverwort.sql.binder.LongBinder;
import jp.ats.sheepdog.Sheepdog;
import jp.ats.sheepdog.UserManager;
import jp.ats.sheepdog.Utilities;
import jp.ats.sheepdog.dataobjects.t_commentDTO;
import jp.ats.sheepdog.dataobjects.t_notice;
import jp.ats.sheepdog.dataobjects.t_noticeDAO;
import jp.ats.sheepdog.dataobjects.t_noticeDAO.t_noticeIterator;
import jp.ats.sheepdog.dataobjects.t_noticeDTO;
import jp.ats.sheepdog.dataobjects.t_thread;
import jp.ats.sheepdog.dataobjects.t_threadDTO;
import jp.ats.substrate.U;
import jp.ats.webkit.util.Sanitizer;
import jp.ats.webkit.util.ServletUtilities;

public class NoticeServlet extends HttpServlet {

	private static final int NOTICE_LIMIT = 20;

	private static final long serialVersionUID = -4558475872038898089L;

	private static final AnchorOptimizer optimizer = AnchorOptimizer.getInstance();

	public static void deleteNotice(String id) {
		if (!U.isAvailable(id)) return;

		Condition condition = ConditionFactory.createCondition(
			t_notice.id,
			new LongBinder(Long.parseLong(id)));
		//IDを書き換えて送信された場合に備え、ユーザーIDも付与しておく
		condition.and(UserManager.createCondition(t_notice.owner));

		new t_noticeDAO().delete(condition);
	}

	@Override
	protected void doGet(
		HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	@Override
	protected void doPost(
		HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	private static void execute(
		HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		deleteNotice(request.getParameter("deleteID"));

		String mode = request.getParameter("mode");

		String script;
		if (mode.equals("event")) {
			script = buildEventNotice();
		} else if (mode.equals("topic")) {
			script = buildTopicNotice();
		} else if (mode.equals("comment")) {
			script = buildCommentNotice();
		} else {
			throw new IllegalStateException();
		}

		ServletUtilities.noCache(response);

		response.setContentType("text/javascript");
		response.setCharacterEncoding("UTF-8");

		PrintWriter writer = response.getWriter();
		writer.write(script.toCharArray());

		writer.flush();
		writer.close();
	}

	private static String buildEventNotice() {
		Condition condition = ConditionFactory.createNullCondition(
			NullComparisonOperator.IS_NOT_NULL,
			t_notice.group_id);

		Column column = Relationship.getInstance(t_notice.RESOURCE_LOCATOR)
			.find(t_notice.t_thread_BY_t_notice_thread_id_fkey)
			.getColumn(t_thread.event_flag);

		condition.and(ConditionFactory.createCondition(column, new IntBinder(1)));

		final String mode = "event";

		String body = build(condition, new CallBack() {

			@Override
			public String buildLine(t_noticeDTO dto) {
				t_threadDTO thread = dto.gett_threadByt_notice_thread_id_fkey();
				return "グループ 「"
					+ Sanitizer.sanitize(dto.gett_groupByt_notice_group_id_fkey()
						.getname())
					+ "」 の "
					+ Utilities.convertMMdd(thread.getcalendar())
					+ " に、"
					+ Sheepdog.EVENT_NAME
					+ " 「"
					+ Sanitizer.sanitize(thread.getlast_title())
					+ "」 が "
					+ Sanitizer.sanitize(thread.gett_userByt_thread_owner_fkey()
						.getname())
					+ " さんによって登録されました。";
			}

			@Override
			public String getClickAction(t_noticeDTO dto) {
				return "href=\"common/view.jsp?id="
					+ dto.getthread_id()
					+ "\" onclick=\"deleteNotice("
					+ dto.getid()
					+ ", '"
					+ mode
					+ "');\"";
			}

			@Override
			public String getLinkClass() {
				return " fwindow";
			}
		},
			mode);

		return "eventCallback(\"" + body + "\");";
	}

	private static String buildTopicNotice() {
		Condition condition = ConditionFactory.createNullCondition(
			NullComparisonOperator.IS_NOT_NULL,
			t_notice.group_id);

		Column column = Relationship.getInstance(t_notice.RESOURCE_LOCATOR)
			.find(t_notice.t_thread_BY_t_notice_thread_id_fkey)
			.getColumn(t_thread.event_flag);

		condition.and(ConditionFactory.createCondition(column, new IntBinder(0)));

		final String mode = "topic";

		String body = build(condition, new CallBack() {

			@Override
			public String buildLine(t_noticeDTO dto) {
				t_threadDTO event = dto.gett_threadByt_notice_thread_id_fkey();

				return "グループ 「"
					+ Sanitizer.sanitize(dto.gett_groupByt_notice_group_id_fkey()
						.getname())
					+ "」 に、"
					+ Sheepdog.TOPIC_NAME
					+ " 「"
					+ Sanitizer.sanitize(event.getlast_title())
					+ "」 が "
					+ Sanitizer.sanitize(event.gett_userByt_thread_owner_fkey()
						.getname())
					+ " さんによって登録されました。";
			}

			@Override
			public String getClickAction(t_noticeDTO dto) {
				return "href=\"common/view.jsp?id="
					+ dto.getthread_id()
					+ "\" onclick=\"deleteNotice("
					+ dto.getid()
					+ ", '"
					+ mode
					+ "');\"";
			}

			@Override
			public String getLinkClass() {
				return " fwindow";
			}
		},
			mode);

		return "topicCallback(\"" + body + "\");";
	}

	private static String buildCommentNotice() {
		Condition condition = ConditionFactory.createNullCondition(
			NullComparisonOperator.IS_NOT_NULL,
			t_notice.comment_id);

		final String mode = "comment";

		String body = build(condition, new CallBack() {

			@Override
			public String buildLine(t_noticeDTO dto) {
				t_threadDTO thread = dto.gett_threadByt_notice_thread_id_fkey();
				if (thread.getevent_flag().intValue() == 1) {
					return buildEventCommentMessage(dto, thread);
				}

				return buildTopicCommentMessage(dto);
			}

			@Override
			public String getClickAction(t_noticeDTO dto) {
				return "href=\"common/view.jsp?id="
					+ dto.getthread_id()
					+ "\" onclick=\"deleteNotice("
					+ dto.getid()
					+ ", '"
					+ mode
					+ "');\"";
			}

			@Override
			public String getLinkClass() {
				return " fwindow";
			}
		},
			mode);

		return "commentCallback(\"" + body + "\");";
	}

	private static String buildEventCommentMessage(
		t_noticeDTO dto,
		t_threadDTO event) {
		String groupString = "";
		if (event.getgroup_id() != null) groupString = "グループ 「"
			+ Sanitizer.sanitize(event.gett_groupByt_thread_group_id_fkey()
				.getname()) + "」 の ";

		t_commentDTO comment = dto.gett_commentByt_notice_comment_id_fkey();

		return groupString
			+ Utilities.convertMMdd(event.getcalendar())
			+ " の"
			+ Sheepdog.EVENT_NAME
			+ " 「"
			+ Sanitizer.sanitize(event.getlast_title())
			+ "」 に、コメント No."
			+ comment.getv_comment_relationshipByt_comment_id_fkey()
				.getcomment_number()
			+ " が "
			+ Sanitizer.sanitize(comment.gett_userByt_comment_owner_fkey()
				.getname())
			+ " さんによって登録されました。";
	}

	private static String buildTopicCommentMessage(t_noticeDTO dto) {
		t_threadDTO topic = dto.gett_threadByt_notice_thread_id_fkey();

		t_commentDTO comment = dto.gett_commentByt_notice_comment_id_fkey();

		return "グループ 「"
			+ Sanitizer.sanitize(topic.gett_groupByt_thread_group_id_fkey()
				.getname())
			+ "」 の "
			+ Sheepdog.TOPIC_NAME
			+ " 「"
			+ Sanitizer.sanitize(topic.getlast_title())
			+ "」 に、コメント No."
			+ comment.getv_comment_relationshipByt_comment_id_fkey()
				.getcomment_number()
			+ " が "
			+ Sanitizer.sanitize(comment.gett_userByt_comment_owner_fkey()
				.getname())
			+ " さんによって登録されました。";
	}

	private static String build(
		Condition condition,
		CallBack callback,
		String mode) {
		condition.and(UserManager.createCondition(t_notice.owner));

		OrderByClause order = new OrderByClause();
		order.desc(t_notice.id);

		t_noticeIterator iterator = new t_noticeDAO().select(
			optimizer,
			condition,
			order,
			new SQLAdjuster() {

				@Override
				public String adjustSQL(String sql) {
					return sql + " LIMIT " + NOTICE_LIMIT;
				}
			},
			RowLockOption.NONE);

		StringBuilder builder = new StringBuilder();
		for (t_noticeDTO dto : iterator) {
			long id = dto.getid();
			builder.append("<div class=\"noticeLine\"><a class=\"noticeMessage"
				+ callback.getLinkClass()
				+ "\" "
				+ callback.getClickAction(dto)
				+ ">"
				+ callback.buildLine(dto)
				+ "</a> <a class=\"functionalLink\" href=\"javascript:void(0);\" onclick=\"deleteNotice("
				+ id
				+ ", '"
				+ mode
				+ "');\">[削除]</a></div>\r\n");
		}

		try {
			return URLEncoder.encode(builder.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}

	private static interface CallBack {

		String buildLine(t_noticeDTO dto);

		String getClickAction(t_noticeDTO dto);

		String getLinkClass();
	}
}
