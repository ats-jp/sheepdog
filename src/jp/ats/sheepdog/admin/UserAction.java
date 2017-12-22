package jp.ats.sheepdog.admin;

import static jp.ats.substrate.U.isAvailable;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import jp.ats.authenticator.Digester;
import jp.ats.liverwort.ormapping.DataAccessHelper.RowLockOption;
import jp.ats.liverwort.sql.Binder;
import jp.ats.liverwort.sql.Condition;
import jp.ats.liverwort.sql.ConditionFactory;
import jp.ats.liverwort.sql.OrderByClause;
import jp.ats.liverwort.sql.binder.IntBinder;
import jp.ats.sheepdog.ApplicationException;
import jp.ats.sheepdog.Pager;
import jp.ats.sheepdog.SearchResult;
import jp.ats.sheepdog.Sheepdog;
import jp.ats.sheepdog.User;
import jp.ats.sheepdog.UserManager;
import jp.ats.sheepdog.Utilities;
import jp.ats.sheepdog.WrapIterator;
import jp.ats.sheepdog.dataobjects.t_user;
import jp.ats.sheepdog.dataobjects.t_userDAO;
import jp.ats.sheepdog.dataobjects.t_userDAO.t_userIterator;
import jp.ats.sheepdog.dataobjects.t_userDTO;
import jp.ats.sheepdog.form.PasswordForm;
import jp.ats.sheepdog.form.UserForm;
import jp.ats.substrate.U;
import jp.ats.substrate.revision.OldRevisionException;
import jp.ats.substrate.revision.Precondition;
import jp.ats.substrate.revision.SimpleRevisionRepository;
import jp.ats.webkit.jspod.JspodManager;
import jp.ats.webkit.jspod.JspodRevisionManager;

public class UserAction {

	private static final JspodRevisionManager<Integer> manager = new JspodRevisionManager<Integer>(
		new SimpleRevisionRepository<Integer>());

	public static void prepareForEdit(UserForm form, String id) {
		manager.start(new Integer(id));

		t_userDTO dto = new t_userDAO().select(new Binder[] { new IntBinder(
			new Integer(id)) });

		if (dto.getdelete_flag().intValue() == 1) throw new IllegalStateException();

		form.setMailAddress(dto.getmail_address());
		form.setName(dto.getname());
		form.setDepartment(dto.getdepartment());
		form.setTitle(dto.gettitle());
		form.setRole(dto.getrole().toString());

		Date expirationDate = dto.getexpiration_date();
		if (expirationDate != null) {
			form.setExpirationDate(U.formatDate("yyyy/MM/dd", expirationDate));
		} else {
			form.setExpirationDate("");
		}
	}

	public static SearchResult<User> search(HttpServletRequest request) {
		String pageString = request.getParameter("page");

		int page = isAvailable(pageString) ? Integer.parseInt(pageString) : 1;

		t_userDAO dao = new t_userDAO();

		Condition condition = ConditionFactory.createCondition(
			t_user.delete_flag,
			new IntBinder(0));

		Pager pager = new Pager(
			page,
			Sheepdog.DEFAULT_PAGE_COUNT,
			dao.count(condition));

		OrderByClause order = new OrderByClause();
		order.asc(t_user.id);

		return new SearchResult<User>(new UserIterator(new t_userDAO().select(
			condition,
			order,
			pager,
			RowLockOption.NONE)), pager);
	}

	public static void prepareForPasswordChange() {
		manager.start(UserManager.getUserID());
	}

	public static void changePassword(HttpServletRequest request) {
		if (!JspodManager.isValidToken()) throw new ApplicationException(
			"この操作は既に完了しています。");

		PasswordForm form = new PasswordForm(request);

		final t_userDTO dto = new t_userDAO().select(
			RowLockOption.FOR_UPDATE_WAIT,
			new IntBinder(manager.getCurrentTarget()));

		generatePassword(dto, form.getPassword());

		dto.setupdate_time(new Timestamp(new Date().getTime()));

		try {
			manager.finish(new Precondition() {

				@Override
				public boolean execute() {
					return dto.update();
				}
			});
		} catch (OldRevisionException e) {
			throw new ApplicationException("このユーザーはすでに他で更新されています。");
		}

		JspodManager.removeToken();
	}

	public static void store(HttpServletRequest request) {
		if (!JspodManager.isValidToken()) throw new ApplicationException(
			"この操作は既に完了しています。");

		UserForm form = new UserForm(request);

		if (Utilities.isUpdate(request.getParameter("mode"))) {
			update(form, request);
		} else {
			insert(form);
		}

		JspodManager.removeToken();
	}

	public static void delete(String id) {
		if (!JspodManager.isValidToken()) throw new ApplicationException(
			"この操作は既に完了しています。");

		manager.start(new Integer(id));

		final t_userDTO dto = new t_userDAO().select(
			RowLockOption.FOR_UPDATE_WAIT,
			new Binder[] { new IntBinder(manager.getCurrentTarget()) });

		dto.setevacuated_mail_address(dto.getmail_address());
		dto.setmail_address(null);
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
			throw new ApplicationException("このユーザーはすでに他で更新されています。");
		}

		JspodManager.removeToken();
	}

	private static void insert(UserForm form) {
		t_userDTO dto = new t_userDTO();

		dto.setmail_address(form.getMailAddress());

		setValues(form, dto);

		String salt = Digester.createRandomSalt(20);
		dto.setsalt(salt);
		dto.setpassword(Digester.digest(salt, form.getPassword()));

		new t_userDAO().insert(dto);
	}

	private static void update(UserForm form, HttpServletRequest request) {
		final t_userDTO dto = new t_userDAO().select(
			RowLockOption.FOR_UPDATE_WAIT,
			new IntBinder(manager.getCurrentTarget()));

		setValues(form, dto);

		String mailAddress = form.getNewMailAddress();
		if (isAvailable(mailAddress)) {
			dto.setmail_address(mailAddress);
		}

		String password = form.getPassword();
		if (isAvailable(password)) generatePassword(dto, password);

		dto.setupdate_time(new Timestamp(new Date().getTime()));

		try {
			manager.finish(new Precondition() {

				@Override
				public boolean execute() {
					return dto.update();
				}
			});
		} catch (OldRevisionException e) {
			throw new ApplicationException("このユーザーはすでに他で更新されています。");
		}

		if (UserManager.getUserID() == dto.getid()) {
			UserManager.clearUser(request.getSession());
		}
	}

	private static void setValues(UserForm form, t_userDTO dto) {
		dto.setname(form.getName());
		dto.setdepartment(form.getDepartment());
		dto.settitle(form.getTitle());
		dto.setrole(new Integer(form.getRole()));
		String expirationDate = form.getExpirationDate();
		if (U.isAvailable(expirationDate)) {
			dto.setexpiration_date(new Timestamp(U.parseDate(
				"yyyy/MM/dd",
				expirationDate).getTime()));
		} else {
			dto.setexpiration_date(null);
		}
	}

	private static final void generatePassword(t_userDTO dto, String rawPassword) {
		String salt = Digester.createRandomSalt(20);
		dto.setsalt(salt);
		dto.setpassword(Digester.digest(salt, rawPassword));
	}

	public static class UserIterator extends WrapIterator<User, t_userDTO> {

		private UserIterator(t_userIterator iterator) {
			super(iterator);
		}

		@Override
		protected User wrap(t_userDTO src) {
			return new User(src);
		}
	}
}
