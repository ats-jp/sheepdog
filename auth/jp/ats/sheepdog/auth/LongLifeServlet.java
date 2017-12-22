package jp.ats.sheepdog.auth;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LongLifeServlet extends HttpServlet {

	private static final long serialVersionUID = -6839638332143532686L;

	static final String idName = "LLID";

	private String loginForm;

	private int expireDays;

	@Override
	public void init(ServletConfig config) throws ServletException {
		expireDays = Integer.parseInt(config.getServletContext()
			.getInitParameter("expire-days"));
		loginForm = config.getInitParameter("login-form");
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

	private void execute(
		HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		String id = null;

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (!idName.equals(cookie.getName())) continue;
				id = cookie.getValue();
				break;
			}
		}

		String username;
		if (id == null || (username = Authenticator.getUsername(id)) == null) {
			request.getRequestDispatcher(loginForm).forward(request, response);
			return;
		}

		response.addCookie(LongLifeFilter.createCookie(
			expireDays,
			username,
			request.getContextPath()));

		response.sendRedirect(request.getContextPath()
			+ "/j_security_check?j_username="
			+ username
			+ "&j_password="
			+ id);
	}

	static String getID(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String id = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (!idName.equals(cookie.getName())) continue;
				id = cookie.getValue();
				break;
			}
		}

		return id;
	}
}
