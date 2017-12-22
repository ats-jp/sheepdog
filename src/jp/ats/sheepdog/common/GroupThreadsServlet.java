package jp.ats.sheepdog.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ats.sheepdog.Pager;
import jp.ats.sheepdog.SearchResult;
import jp.ats.webkit.util.Sanitizer;
import jp.ats.webkit.util.ServletUtilities;

public class GroupThreadsServlet extends HttpServlet {

	private static final long serialVersionUID = 8456145471505732859L;

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
		int groupID = Integer.parseInt(request.getParameter("id"));

		SearchResult<Thread> result = ThreadAction.searchTopics(
			groupID,
			request.getParameter("page"));

		StringBuilder builder = new StringBuilder();

		builder.append("<table class=\"sheepdogTable\">");

		boolean addHeader = false;

		for (Thread thread : result) {
			if (!addHeader) {
				builder.append("<th nowrap=\"nowrap\">タイトル</th>");
				builder.append("<th nowrap=\"nowrap\">オーナー</th>");
				builder.append("<th nowrap=\"nowrap\">登録時刻</th>");
				addHeader = true;
			}

			String sealed = "";
			if (SealManager.isSealedThread(thread.getID())) sealed = " class=\"sealedThread\"";

			builder.append("<tr" + sealed + ">");
			builder.append("<td><a href=\"common/view.jsp?id="
				+ thread.getID()
				+ "\" class=\"fwindow\">"
				+ Sanitizer.sanitize(thread.getTitle())
				+ "</a></td>");
			builder.append("<td><a href=\"common/user.jsp?id="
				+ thread.getOwnerID()
				+ "\" class=\"fwindow\">"
				+ Sanitizer.sanitize(thread.getOwner().getName())
				+ "</a></td>");
			builder.append("<td>" + thread.getUpdateTime() + "</td>");
			builder.append("</tr>");
		}

		builder.append("</tr></table>");

		Pager pager = result.getPager();

		builder.append("<div class=\"pagerArea\"><span>");
		if (pager.hasPrev()) {
			builder.append("<a class=\"functionalLink\" href=\"javascript:void(0);\" onclick=\"loadThreads("
				+ groupID
				+ ", 0)\">≪</a>");
		} else {
			builder.append("≪");
		}
		builder.append("</span>");

		builder.append("<span>");
		if (pager.hasPrev()) {
			builder.append("<a class=\"functionalLink\" href=\"javascript:void(0);\" onclick=\"loadThreads("
				+ groupID
				+ ", "
				+ pager.getPrevPageCount()
				+ ")\">&lt;</a>");
		} else {
			builder.append("&lt;");
		}
		builder.append("</span>");

		builder.append("<span>"
			+ pager.getPage()
			+ " / "
			+ pager.getLastPageCount()
			+ "</span>");

		builder.append("<span>");
		if (pager.hasNext()) {
			builder.append("<a class=\"functionalLink\" href=\"javascript:void(0);\" onclick=\"loadThreads("
				+ groupID
				+ " ,"
				+ pager.getNextPageCount()
				+ ")\">&gt;</a>");
		} else {
			builder.append("&gt;");
		}
		builder.append("</span>");

		builder.append("<span>");
		if (pager.hasLastPage()) {
			builder.append("<a class=\"functionalLink\" href=\"javascript:void(0);\" onclick=\"loadThreads("
				+ groupID
				+ " ,"
				+ pager.getLastPageCount()
				+ ")\">≫</a>");
		} else {
			builder.append("≫");
		}
		builder.append("</span></div>");

		String script = "groupThreadCallback(\""
			+ URLEncoder.encode(builder.toString(), "UTF-8")
			+ "\")";

		ServletUtilities.noCache(response);

		response.setContentType("text/javascript");
		response.setCharacterEncoding("UTF-8");

		PrintWriter writer = response.getWriter();
		writer.write(script.toCharArray());

		writer.flush();
		writer.close();
	}
}
