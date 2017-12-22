package jp.ats.sheepdog.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ats.webkit.action.Action;
import jp.ats.webkit.action.ApplicationException;
import jp.ats.webkit.jspod.JspodManager;

public class SubscribeThread implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
		throws ApplicationException, ServletException, IOException {
		if (!JspodManager.isValidToken()) throw new ApplicationException(
			"Ç±ÇÃëÄçÏÇÕä˘Ç…äÆóπÇµÇƒÇ¢Ç‹Ç∑ÅB");

		String action = request.getParameter("action");

		int id = Integer.parseInt(request.getParameter("id"));
		if (action.equals("on")) {
			SubscribeAction.subscribeThreadForComment(id);
		} else if (action.equals("off")) {
			SubscribeAction.deleteSubscribeOnThread(id);
		}

		JspodManager.removeToken();
	}
}
