package jp.ats.sheepdog;

import javax.servlet.http.HttpServletRequest;

import jp.ats.liverwort.sql.SQLAdjuster;
import jp.ats.substrate.U;

public class Pager implements SQLAdjuster {

	private static final String KEY = Pager.class.getName() + ".key";

	private static final String URI_KEY = Pager.class.getName() + ".uri";

	private static final String PARAMETER_KEY = Pager.class.getName()
		+ ".parameter";

	private final int page;

	private final int offset;

	private final int lineCount;

	private final int allLineCount;

	private final int lastPageCount;

	public static Pager getPager(HttpServletRequest request) {
		return (Pager) request.getAttribute(KEY);
	}

	public static String getURI(HttpServletRequest request) {
		return (String) request.getAttribute(URI_KEY);
	}

	public static String getParameter(HttpServletRequest request) {
		String parameter = (String) request.getAttribute(PARAMETER_KEY);
		if (!U.isAvailable(parameter)) return "";

		return parameter.endsWith("&") ? parameter : (parameter + "&");
	}

	public Pager(int page, int lineCount, int allLineCount) {
		this.lineCount = lineCount;
		this.allLineCount = allLineCount;

		int quotient = allLineCount / lineCount;
		int remainder = allLineCount % lineCount;
		lastPageCount = remainder > 0 ? quotient + 1 : quotient;

		this.page = lastPageCount < page
			? lastPageCount
			: (page < 1 ? 1 : page);

		offset = ((page < 1 ? 1 : page) - 1) * lineCount;
	}

	public void setPager(HttpServletRequest request, String uri) {
		request.setAttribute(KEY, this);
		request.setAttribute(URI_KEY, uri);
	}

	public void setPager(
		HttpServletRequest request,
		String uri,
		String parameterString) {
		request.setAttribute(KEY, this);
		request.setAttribute(URI_KEY, uri);
		request.setAttribute(PARAMETER_KEY, parameterString);
	}

	public boolean hasPrev() {
		return page > 1;
	}

	public int getPrevPageCount() {
		return page - 1;
	}

	public boolean hasNext() {
		return (page * lineCount) < allLineCount;
	}

	public int getNextPageCount() {
		return page + 1;
	}

	public int getLastPageCount() {
		return lastPageCount;
	}

	public boolean hasLastPage() {
		return page < getLastPageCount();
	}

	public int getPage() {
		return page;
	}

	@Override
	public String adjustSQL(String sql) {
		return sql + " LIMIT " + lineCount + " OFFSET " + offset;
	}
}
