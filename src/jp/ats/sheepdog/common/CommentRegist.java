package jp.ats.sheepdog.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ats.webkit.action.Action;
import jp.ats.webkit.action.ApplicationException;

public class CommentRegist implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
		throws ApplicationException, ServletException, IOException {
		CommentAction.insertComment(request);
	}
}
