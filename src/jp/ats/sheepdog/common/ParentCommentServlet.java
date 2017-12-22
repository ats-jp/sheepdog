package jp.ats.sheepdog.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ats.liverwort.selector.AnchorOptimizer;
import jp.ats.sheepdog.Utilities;
import jp.ats.webkit.util.Sanitizer;
import jp.ats.webkit.util.ServletUtilities;

public class ParentCommentServlet extends HttpServlet {

	private static final long serialVersionUID = -5667366400105405900L;

	private static final AnchorOptimizer optimizer = AnchorOptimizer.getInstance();

	@Override
	protected void doGet(
		HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		Comment comment = CommentAction.getComment(
			optimizer,
			Integer.parseInt(request.getParameter("id")));

		String body;
		if (comment.isDeleted()) {
			body = "<div class=\"parentCommentHeader\">このコメントは削除されました</div>";
		} else {
			body = "<div class=\"parentCommentHeader\">"
				+ comment.getNumber()
				+ " : "
				+ Sanitizer.sanitize(comment.getOwner().getName())
				+ " ("
				+ comment.getCreateTime()
				+ ") のコメント</div><br /><div class=\"parentComment\">"
				+ Utilities.restore(Sanitizer.sanitize(comment.getBody()))
				+ "</div>";
		}

		String script = "minipopupCallback(\""
			+ URLEncoder.encode(body, "UTF-8")
			+ "\");";

		ServletUtilities.noCache(response);

		response.setContentType("text/javascript");
		response.setCharacterEncoding("UTF-8");

		PrintWriter writer = response.getWriter();
		writer.write(script.toCharArray());

		writer.flush();
		writer.close();
	}
}
