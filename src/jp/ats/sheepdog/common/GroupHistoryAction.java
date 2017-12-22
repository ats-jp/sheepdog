package jp.ats.sheepdog.common;

import java.sql.Timestamp;

import jp.ats.liverwort.ormapping.DataAccessHelper.RowLockOption;
import jp.ats.liverwort.selector.AnchorOptimizer;
import jp.ats.liverwort.sql.OrderByClause;
import jp.ats.liverwort.sql.SQLAdjuster;
import jp.ats.liverwort.sql.binder.IntBinder;
import jp.ats.sheepdog.UserManager;
import jp.ats.sheepdog.dataobjects.t_group_history;
import jp.ats.sheepdog.dataobjects.t_group_historyDAO;
import jp.ats.sheepdog.dataobjects.t_group_historyDAO.t_group_historyIterator;
import jp.ats.sheepdog.dataobjects.t_group_historyDTO;

public class GroupHistoryAction {

	private static final int LIMIT = 10;

	private static final AnchorOptimizer optimizer = AnchorOptimizer.getInstance();

	public static void updateHistory(int groupID) {
		t_group_historyDAO dao = new t_group_historyDAO();
		t_group_historyDTO history = dao.select(
			RowLockOption.FOR_UPDATE_WAIT,
			new IntBinder(UserManager.getUserID()),
			new IntBinder(groupID));

		if (history == null) {
			history = new t_group_historyDTO();
			history.setowner(UserManager.getUserID());
			history.setgroup_id(groupID);
			dao.insert(history);
		} else {
			history.setupdate_time(new Timestamp(System.currentTimeMillis()));
			history.update();
		}
	}

	public static t_group_historyIterator select() {
		OrderByClause order = new OrderByClause();
		order.desc(t_group_history.update_time);

		return new t_group_historyDAO().select(
			optimizer,
			UserManager.createCondition(t_group_history.owner),
			order,
			new SQLAdjuster() {

				@Override
				public String adjustSQL(String sql) {
					return sql + " LIMIT " + LIMIT;
				}
			},
			RowLockOption.NONE);
	}
}
