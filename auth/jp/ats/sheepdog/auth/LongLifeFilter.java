package jp.ats.sheepdog.auth;

import java.io.IOException;
import java.security.Principal;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LongLifeFilter implements Filter {

	private static final SecureRandom random = new SecureRandom();

	private static AtomicLong counter = new AtomicLong();

	private int expireDays;

	@Override
	public void init(FilterConfig config) throws ServletException {
		expireDays = Integer.parseInt(config.getServletContext()
			.getInitParameter("expire-days"));
	}

	@Override
	public void doFilter(
		ServletRequest request,
		ServletResponse response,
		FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		Cookie[] cookies = httpRequest.getCookies();
		String id = null;

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (!LongLifeServlet.idName.equals(cookie.getName())) continue;
				id = cookie.getValue();
				break;
			}
		}

		Principal principal = httpRequest.getUserPrincipal();
		if (principal != null
			&& (id == null || id.equals("") || !Authenticator.contains(id))) {
			httpResponse.addCookie(createCookie(
				expireDays,
				principal.getName(),
				httpRequest.getContextPath()));
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {}

	static Cookie createCookie(
		int expireDays,
		String username,
		String contextPath) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, expireDays);

		Cookie cookie = new Cookie(
			LongLifeServlet.idName,
			generateNewLongLifeID(username, calendar.getTime()));

		cookie.setMaxAge(expireDays * 24 * 60 * 60);
		cookie.setPath(contextPath);

		return cookie;
	}

	private static String generateNewLongLifeID(String username, Date expire) {
		byte[] bytes = new byte[40];
		random.nextBytes(bytes);

		StringBuilder builder = new StringBuilder();
		for (byte b : bytes) {
			builder.append(Integer.toString((b & 0xf0) >> 4, 16));
			builder.append(Integer.toString(b & 0x0f, 16));
		}

		String id = builder.append("-")
			.append(counter.getAndIncrement())
			.toString();

		Authenticator.addNewLongLifeID(id, username, expire);

		return id;
	}
}
