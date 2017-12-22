package jp.ats.sheepdog.common;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import jp.ats.liverwort.ormapping.DataAccessHelper.RowLockOption;
import jp.ats.liverwort.sql.SQLAdjuster;
import jp.ats.sheepdog.UserManager;
import jp.ats.sheepdog.dataobjects.t_seal;
import jp.ats.sheepdog.dataobjects.t_sealDAO;
import jp.ats.sheepdog.dataobjects.t_sealDAO.t_sealIterator;
import jp.ats.sheepdog.dataobjects.t_sealDTO;
import jp.ats.substrate.U;

public class SealManager implements Filter {

	private static final ThreadLocal<Set<Integer>> threadsContainer = new ThreadLocal<Set<Integer>>();

	private static final ThreadLocal<Set<Integer>> commentsContainer = new ThreadLocal<Set<Integer>>();

	private static final SQLAdjuster adjuster = new SQLAdjuster() {

		@Override
		public String adjustSQL(String sql) {
			return sql + " LIMIT 300";
		}
	};

	public static boolean isSealedThread(int threadID) {
		Set<Integer> threads = threadsContainer.get();
		if (threads != null) return threads.contains(threadID);
		load();
		return isSealedThread(threadID);
	}

	public static boolean isSealedComment(int commentID) {
		Set<Integer> comments = commentsContainer.get();
		if (comments != null) return comments.contains(commentID);
		load();
		return isSealedComment(commentID);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

	@Override
	public void doFilter(
		ServletRequest request,
		ServletResponse response,
		FilterChain chain) throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
		} finally {
			threadsContainer.set(null);
			commentsContainer.set(null);
		}
	}

	@Override
	public void destroy() {}

	private static void load() {
		t_sealIterator iterator = new t_sealDAO().select(
			UserManager.createCondition(t_seal.owner),
			null,
			adjuster,
			RowLockOption.NONE);

		Set<Integer> threads = U.newHashSet();
		threadsContainer.set(threads);

		Set<Integer> comments = U.newHashSet();
		commentsContainer.set(comments);

		for (t_sealDTO dto : iterator) {
			Integer threadID = dto.getthread_id();
			if (threadID != null) threads.add(threadID);

			Integer commentID = dto.getcomment_id();
			if (commentID != null) comments.add(commentID);
		}
	}
}
