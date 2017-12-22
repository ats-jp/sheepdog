package jp.ats.sheepdog;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.ats.liverwort.sql.Column;
import jp.ats.liverwort.sql.Condition;
import jp.ats.liverwort.sql.ConditionFactory;
import jp.ats.liverwort.sql.binder.IntBinder;
import jp.ats.liverwort.sql.binder.StringBinder;
import jp.ats.sheepdog.dataobjects.t_user;
import jp.ats.sheepdog.dataobjects.t_userDAO;
import jp.ats.sheepdog.dataobjects.t_userDAO.t_userIterator;
import jp.ats.sheepdog.dataobjects.t_userDTO;

public class UserManager implements Filter {

	private static final String key = UserManager.class.getName() + ".key";

	private static final ThreadLocal<UserBean> userContainer = new ThreadLocal<UserBean>();

	public static UserBean getUser() {
		return userContainer.get();
	}

	public static int getUserID() {
		return userContainer.get().getID();
	}

	public static Condition createCondition(String columnName) {
		return ConditionFactory.createCondition(columnName, new IntBinder(
			getUserID()));
	}

	public static Condition createCondition(Column column) {
		return ConditionFactory.createCondition(column, new IntBinder(
			getUserID()));
	}

	public static void clearUser(HttpSession session) {
		synchronized (session) {
			session.removeAttribute(key);
		}
	}

	public static boolean isUserInRole(Role... roles) {
		Role current = getUser().getRole();
		for (Role role : roles) {
			if (current.equals(role)) return true;
		}

		return false;
	}

	public static boolean equals(User user) {
		return getUser().getID() == user.getID();
	}

	public static boolean equals(UserBean user) {
		return getUser().getID() == user.getID();
	}

	@Override
	public void init(FilterConfig config) throws ServletException {}

	@Override
	public void doFilter(
		ServletRequest request,
		ServletResponse response,
		FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		Principal principal = httpRequest.getUserPrincipal();
		if (principal == null) throw new ApplicationException("ÉçÉOÉCÉìÇµÇƒÇ¢Ç‹ÇπÇÒÅB");

		HttpSession session = httpRequest.getSession();
		UserBean user;
		synchronized (session) {
			user = (UserBean) session.getAttribute(key);

			if (user == null) {
				Condition condition = ConditionFactory.createCondition(
					t_user.mail_address,
					new StringBinder(principal.getName()));

				t_userIterator iterator = new t_userDAO().select(
					condition,
					null);

				t_userDTO dto = iterator.next();
				iterator.close();

				user = new UserBean(dto);

				session.setAttribute(key, user);
			}
		}

		userContainer.set(user);
		try {
			chain.doFilter(request, response);
		} finally {
			userContainer.set(null);
		}
	}

	@Override
	public void destroy() {}
}
