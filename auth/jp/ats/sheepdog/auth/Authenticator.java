package jp.ats.sheepdog.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ats.sheepdog.Role;

public class Authenticator extends jp.ats.authenticator.Authenticator {

	private static final Map<String, IDContainer> ids = new HashMap<String, IDContainer>();

	private static final long interval = 60 * 1000;

	private static final String APPLICATION_PATH = "/ats-groupwear";

	static {
		Thread remover = new Thread(new Runnable() {

			@Override
			public void run() {
				do {
					long now = new Date().getTime();
					synchronized (ids) {
						for (Iterator<IDContainer> iterator = ids.values()
							.iterator(); iterator.hasNext();) {
							if (iterator.next().expire.getTime() < now) iterator.remove();
						}
					}

					try {
						Thread.sleep(interval);
					} catch (InterruptedException e) {}
				} while (true);
			}
		});

		remover.setDaemon(true);

		remover.start();
	}

	public static void removeLongLifeID(
		HttpServletRequest request,
		HttpServletResponse response) {
		String id = LongLifeServlet.getID(request);
		synchronized (ids) {
			ids.remove(id);
		}

		Cookie cookie = new Cookie(LongLifeServlet.idName, "");
		cookie.setMaxAge(0);
		cookie.setPath(request.getContextPath());
		response.addCookie(cookie);
	}

	@Override
	protected boolean authenticate(Result result, String password) {
		boolean contains;
		synchronized (ids) {
			contains = ids.remove(password) != null;
		}

		if (!contains) return super.authenticate(result, password);

		return true;
	}

	@Override
	protected Result fetch(Connection connection, String username)
		throws SQLException {
		String sql = "select salt, password, expiration_date, role from t_user where delete_flag = 0 and mail_address = ?";
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, username);

		ResultSet resultSet = statement.executeQuery();
		if (!resultSet.next()) return null;

		Result result = new Result();
		result.salt = resultSet.getString("salt");
		result.password = resultSet.getString("password");
		result.expirationDate = resultSet.getTimestamp("expiration_date");
		result.roles = new String[] { Role.values()[resultSet.getInt("role")].name() };
		return result;
	}

	@Override
	protected String getApplicationPath() {
		return APPLICATION_PATH;
	}

	static void addNewLongLifeID(String id, String username, Date expire) {
		synchronized (ids) {
			ids.put(id, new IDContainer(username, expire));
		}
	}

	static String getUsername(String id) {
		synchronized (ids) {
			IDContainer container = ids.get(id);
			if (container != null) return container.username;
			return null;
		}
	}

	static boolean contains(String id) {
		synchronized (ids) {
			return ids.containsKey(id);
		}
	}

	private static class IDContainer {

		private final String username;

		private final Date expire;

		private IDContainer(String username, Date expire) {
			this.username = username;
			this.expire = expire;
		}
	}
}
