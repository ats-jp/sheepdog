package jp.ats.sheepdog.common;

import static jp.ats.substrate.U.isAvailable;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import jp.ats.liverwort.ormapping.DataAccessHelper.RowLockOption;
import jp.ats.liverwort.selector.AnchorOptimizer;
import jp.ats.liverwort.selector.Optimizer;
import jp.ats.liverwort.sql.Binder;
import jp.ats.liverwort.sql.Condition;
import jp.ats.liverwort.sql.ConditionFactory;
import jp.ats.liverwort.sql.OrderByClause;
import jp.ats.liverwort.sql.Relationship;
import jp.ats.liverwort.sql.binder.IntBinder;
import jp.ats.sheepdog.ApplicationException;
import jp.ats.sheepdog.NewIDInserter;
import jp.ats.sheepdog.Pager;
import jp.ats.sheepdog.PermissionChecker;
import jp.ats.sheepdog.SearchResult;
import jp.ats.sheepdog.Sheepdog;
import jp.ats.sheepdog.UserManager;
import jp.ats.sheepdog.WrapIterator;
import jp.ats.sheepdog.dataobjects.t_comment;
import jp.ats.sheepdog.dataobjects.t_commentDAO;
import jp.ats.sheepdog.dataobjects.t_commentDAO.t_commentIterator;
import jp.ats.sheepdog.dataobjects.t_commentDTO;
import jp.ats.sheepdog.dataobjects.t_threadDTO;
import jp.ats.sheepdog.dataobjects.v_comment_relationship;
import jp.ats.substrate.U;
import jp.ats.substrate.revision.OldRevisionException;
import jp.ats.substrate.revision.Precondition;
import jp.ats.substrate.revision.SimpleRevisionRepository;
import jp.ats.webkit.jspod.JspodManager;
import jp.ats.webkit.jspod.JspodRevisionManager;

public class CommentAction {

	private static final JspodRevisionManager<Integer> manager = new JspodRevisionManager<Integer>(
		new SimpleRevisionRepository<Integer>());

	private static final AnchorOptimizer optimizer = AnchorOptimizer.getInstance();

	private static final AnchorOptimizer historyOptimizer = AnchorOptimizer.getInstance();

	private static final Object eventLock = new Object();

	public static SearchResult<Comment> search(HttpServletRequest request) {
		t_commentDAO dao = new t_commentDAO();

		Relationship comment = Relationship.getInstance(t_comment.RESOURCE_LOCATOR);

		Condition condition = ConditionFactory.createCondition(
			comment.find(t_comment.v_comment_relationship_BY_t_comment_id_fkey)
				.getColumn(v_comment_relationship.thread_id),
			new IntBinder(Integer.parseInt(request.getParameter("id"))));

		String pageString = request.getParameter("page");
		int page = isAvailable(pageString) ? Integer.parseInt(pageString) : 1;

		Pager pager = new Pager(
			page,
			Sheepdog.DEFAULT_PAGE_COUNT,
			dao.count(condition));

		OrderByClause order = new OrderByClause();
		order.desc(comment.getColumn(t_comment.id));

		return new SearchResult<Comment>(new CommentIterator(
			new t_commentDAO().select(
				optimizer,
				condition,
				order,
				pager,
				RowLockOption.NONE)), pager);
	}

	public static SearchResult<Comment> searchForHistory(
		HttpServletRequest request) {
		t_commentDAO dao = new t_commentDAO();

		Relationship relation = Relationship.getInstance(t_comment.RESOURCE_LOCATOR);

		Condition condition = UserManager.createCondition(relation.getColumn(t_comment.owner));

		String pageString = request.getParameter("page");
		int page = isAvailable(pageString) ? Integer.parseInt(pageString) : 1;

		Pager pager = new Pager(
			page,
			Sheepdog.DEFAULT_PAGE_COUNT,
			dao.count(condition));

		OrderByClause order = new OrderByClause();
		order.desc(relation.getColumn(t_comment.id));

		return new SearchResult<Comment>(new CommentIterator(
			new t_commentDAO().select(
				historyOptimizer,
				condition,
				order,
				pager,
				RowLockOption.NONE)), pager);
	}

	public static Comment getComment(Optimizer optimizer, int id) {
		return new Comment(new t_commentDAO().select(optimizer, new IntBinder(
			id)));
	}

	public static void delete(String id) {
		if (!JspodManager.isValidToken()) throw new ApplicationException(
			"この操作は既に完了しています。");

		manager.start(new Integer(id));

		final t_commentDTO dto = new t_commentDAO().select(
			RowLockOption.FOR_UPDATE_WAIT,
			new Binder[] { new IntBinder(manager.getCurrentTarget()) });

		PermissionChecker.checkForStore(dto.getowner());

		dto.setdelete_flag(1);
		dto.setupdate_time(new Timestamp(new Date().getTime()));

		try {
			manager.finish(new Precondition() {

				@Override
				public boolean execute() {
					return dto.update();
				}
			});
		} catch (OldRevisionException e) {
			throw new ApplicationException("このグループはすでに他で更新されています。");
		}

		JspodManager.removeToken();
	}

	public static class CommentIterator
		extends WrapIterator<Comment, t_commentDTO> {

		private CommentIterator(t_commentIterator iterator) {
			super(iterator);
		}

		@Override
		protected Comment wrap(t_commentDTO src) {
			return new Comment(src);
		}
	}

	public static void insertComment(HttpServletRequest request) {
		if (!JspodManager.isValidToken()) throw new ApplicationException(
			"この操作は既に完了しています。");

		int threadID = Integer.parseInt(request.getParameter("id"));

		t_threadDTO thread = ThreadAction.getThreadDTO(threadID);

		PermissionChecker.checkThread(thread);

		int owner = UserManager.getUserID();

		//他人のコメントが登録された時点で公開レベルを下げれなくする
		if (thread.getowner() != owner) {
			thread.setprivate_flag(0);
		}

		thread.setupdate_time(new Timestamp(System.currentTimeMillis()));
		thread.update();

		t_commentDTO comment = new t_commentDTO();
		comment.setowner(owner);
		comment.setbody(request.getParameter("comment"));
		comment.setthread_id(threadID);

		String parentString = request.getParameter("parent");
		if (U.isAvailable(parentString)) {
			comment.setparent_id(Integer.parseInt(parentString));
		}

		int commentID;
		synchronized (eventLock) {
			Relationship relation = Relationship.getInstance(t_comment.RESOURCE_LOCATOR);

			Condition condition = ConditionFactory.createCondition(
				relation.getColumn(t_comment.thread_id),
				new IntBinder(threadID));

			int count = new t_commentDAO().count(condition);

			comment.setcomment_number(count + 1);

			commentID = (int) NewIDInserter.insert(
				t_comment.RESOURCE_LOCATOR,
				comment);
		}

		AttachedFileAction.insertCommentAttachedFiles(request, commentID);

		SubscribeAction.subscribeThreadForComment(threadID);

		SubscribeAction.sendForComment(threadID, commentID);

		JspodManager.removeToken();
	}
}
