package jp.ats.sheepdog.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ats.webkit.util.ServletUtilities;

public class MemberServlet extends HttpServlet {

	private static final long serialVersionUID = 8026814279669789529L;

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
		String mode = request.getParameter("mode");
		int userID = Integer.parseInt(request.getParameter("user"));
		int groupID = Integer.parseInt(request.getParameter("group"));

		if (mode.equals("add")) {
			MemberAction.addMember(groupID, userID);
		} else if (mode.equals("remove")) {
			MemberAction.removeMember(groupID, userID);
		} else {
			throw new IllegalStateException();
		}

		String script = "updateStatus(\"" + mode + "\", " + userID + ")";

		ServletUtilities.noCache(response);

		response.setContentType("text/javascript");
		response.setCharacterEncoding("UTF-8");

		PrintWriter writer = response.getWriter();
		writer.write(script.toCharArray());

		writer.flush();
		writer.close();
	}
}
