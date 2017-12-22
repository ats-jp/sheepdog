package jp.ats.sheepdog.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ats.liverwort.sql.Condition;
import jp.ats.liverwort.sql.ConditionFactory;
import jp.ats.liverwort.sql.binder.IntBinder;
import jp.ats.sheepdog.UserManager;
import jp.ats.sheepdog.dataobjects.t_last_notice;
import jp.ats.sheepdog.dataobjects.t_last_noticeDAO;
import jp.ats.sheepdog.dataobjects.t_last_noticeDTO;
import jp.ats.sheepdog.dataobjects.v_current_notice;
import jp.ats.sheepdog.dataobjects.v_current_noticeDAO;
import jp.ats.sheepdog.dataobjects.v_current_noticeDAO.v_current_noticeIterator;
import jp.ats.sheepdog.dataobjects.v_current_noticeDTO;
import jp.ats.webkit.util.ServletUtilities;

public class NewNoticeServlet extends HttpServlet {

	private static final long serialVersionUID = 8175179995909788327L;

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
		IntBinder ownerBinder = new IntBinder(UserManager.getUserID());

		Condition condition = ConditionFactory.createCondition(
			v_current_notice.owner,
			ownerBinder);
		v_current_noticeIterator iterator = new v_current_noticeDAO().select(
			condition,
			null);

		String script = "";

		if (iterator.hasNext()) {
			v_current_noticeDTO dto = iterator.next();
			if (dto.getmax_id() > dto.getlast_id()) {
				t_last_noticeDAO dao = new t_last_noticeDAO();

				new t_last_noticeDAO().delete(ConditionFactory.createCondition(
					t_last_notice.owner,
					ownerBinder));

				t_last_noticeDTO newDTO = new t_last_noticeDTO();
				newDTO.setowner(UserManager.getUserID());
				newDTO.setlast_notice(dto.getmax_id());

				dao.insert(newDTO);

				script = "newNotice();";
			}
		}

		ServletUtilities.noCache(response);

		response.setContentType("text/javascript");
		response.setCharacterEncoding("UTF-8");

		PrintWriter writer = response.getWriter();
		writer.write(script.toCharArray());

		writer.flush();
		writer.close();
	}
}
