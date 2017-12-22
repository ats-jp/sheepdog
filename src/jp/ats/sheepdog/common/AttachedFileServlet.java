package jp.ats.sheepdog.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ats.liverwort.selector.AnchorOptimizer;
import jp.ats.liverwort.sql.Condition;
import jp.ats.liverwort.sql.ConditionFactory;
import jp.ats.liverwort.sql.OrderByClause;
import jp.ats.liverwort.sql.binder.IntBinder;
import jp.ats.sheepdog.PermissionChecker;
import jp.ats.sheepdog.dataobjects.t_attachDTO;
import jp.ats.sheepdog.dataobjects.t_commentDTO;
import jp.ats.sheepdog.dataobjects.t_comment_attachDAO;
import jp.ats.sheepdog.dataobjects.t_comment_attachDTO;
import jp.ats.sheepdog.dataobjects.t_content_attach;
import jp.ats.sheepdog.dataobjects.t_content_attachDAO;
import jp.ats.sheepdog.dataobjects.t_content_attachDAO.t_content_attachIterator;
import jp.ats.sheepdog.dataobjects.t_content_attachDTO;
import jp.ats.sheepdog.dataobjects.t_threadDTO;
import jp.ats.substrate.U;

public class AttachedFileServlet extends HttpServlet {

	private static final long serialVersionUID = -5519523195929860131L;

	private static final AnchorOptimizer contentOptimizer = AnchorOptimizer.getInstance();

	private static final AnchorOptimizer commentOptimizer = AnchorOptimizer.getInstance();

	@Override
	protected void doGet(
		HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String attachID = request.getParameter("id");

		t_attachDTO attach;
		if (type.equals("content")) {
			Condition condition = ConditionFactory.createCondition(
				t_content_attach.attach_id,
				new IntBinder(Integer.parseInt(attachID)));

			OrderByClause order = new OrderByClause();
			order.desc(t_content_attach.content_id);

			t_content_attachIterator iterator = new t_content_attachDAO().select(
				contentOptimizer,
				condition,
				null);

			t_content_attachDTO dto = iterator.next();

			PermissionChecker.checkThread(dto.gett_contentByt_content_attach_content_id_fkey()
				.gett_threadByt_content_thread_id_fkey());

			attach = dto.gett_attachByt_content_attach_attach_id_fkey();
		} else if (type.equals("comment")) {
			t_comment_attachDTO dto = new t_comment_attachDAO().select(
				commentOptimizer,
				new IntBinder(Integer.parseInt(attachID)));

			t_commentDTO comment = dto.gett_commentByt_comment_attach_comment_id_fkey();

			t_threadDTO event = comment.getv_comment_relationshipByt_comment_id_fkey()
				.gett_threadByv_comment_relationship_thread_id_fkey();

			PermissionChecker.checkThread(event);

			attach = dto.gett_attachByt_comment_attach_attach_id_fkey();
		} else {
			throw new IllegalStateException();
		}

		String userAgent = request.getHeader("User-Agent");
		String fileNamePart;
		if (userAgent != null && userAgent.indexOf("MSIE") != -1) {
			fileNamePart = "attachment; filename=\""
				+ URLEncoder.encode(attach.getname(), "UTF-8")
				+ "\"";
		} else {
			fileNamePart = "attachment; filename*=UTF-8''"
				+ URLEncoder.encode(attach.getname(), "UTF-8");
		}

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", fileNamePart);

		String repository = getServletContext().getInitParameter(
			AttachedFileManager.FILE_REPOSITORY_KEY);

		File file = new File(repository, attach.getpath());

		if (!file.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		BufferedInputStream input = new BufferedInputStream(
			new FileInputStream(file));
		BufferedOutputStream output = new BufferedOutputStream(
			response.getOutputStream());

		U.sendBytes(input, output);

		output.close();
		input.close();
	}
}
