package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.extension.DTOIterator;
import jp.ats.liverwort.jdbc.ResourceLocator;
import jp.ats.liverwort.ormapping.DataAccessHelper.RowLockOption;
import jp.ats.liverwort.ormapping.DataObjectIterator;
import jp.ats.liverwort.ormapping.UpdatableDataObject;
import jp.ats.liverwort.selector.Optimizer;
import jp.ats.liverwort.selector.SimpleOptimizer;
import jp.ats.liverwort.sql.Condition;
import jp.ats.liverwort.sql.OrderByClause;
import jp.ats.liverwort.sql.SQLAdjuster;

/**
 * 自動生成された DAO の実装クラスです。
 *
 * パッケージ名 jp.ats.sheepdog.dataobjects
 * テーブル名 t_comment
 */
public class t_commentDAO extends GenericDAO<t_commentDTO> {

	/**
	 * パラメータの条件にマッチするレコードを検索し、 {@link t_commentIterator} として返します。
	 * <br>
	 * {@link Optimizer} には {@link SimpleOptimizer} が使用されます。
	 *
	 * @param condition WHERE 句となる条件
	 * @param order  ORDER 句
	 * @param adjuster 検索結果を調整する {@link SQLAdjuster}
	 * @param rowLockOption 行ロックオプション
	 * @return {@link DTOIterator}
	 */
	public t_commentIterator select(
		Condition condition,
		OrderByClause order,
		SQLAdjuster adjuster,
		RowLockOption rowLockOption) {
		return select(
			new SimpleOptimizer(getResourceLocator()),
			condition,
			order,
			adjuster,
			rowLockOption);
	}

	/**
	 * パラメータの条件にマッチするレコードを検索し、 {@link t_commentIterator} として返します。
	 * <br>
	 * {@link Optimizer} には {@link SimpleOptimizer} が使用されます。
	 * <br>
	 * {@link RowLockOption} には {@link RowLockOption#NONE} が使用されます。
	 *
	 * @param condition WHERE 句となる条件
	 * @param order  ORDER 句
	 * @return {@link DTOIterator}
	 */
	public t_commentIterator select(Condition condition, OrderByClause order) {
		return select(condition, order, null, RowLockOption.NONE);
	}

	/**
	 * パラメータの条件にマッチするレコードを検索し、 {@link t_commentIterator} として返します。
	 *
	 * @param optimizer SELECT 句を制御する {@link Optimizer}
	 * @param condition WHERE 句となる条件
	 * @param order  ORDER 句
	 * @param adjuster 検索結果を調整する {@link SQLAdjuster}
	 * @param rowLockOption 行ロックオプション
	 * @return {@link DTOIterator}
	 */
	public t_commentIterator select(
		Optimizer optimizer,
		Condition condition,
		OrderByClause order,
		SQLAdjuster adjuster,
		RowLockOption rowLockOption) {
		return new t_commentIterator(helper.getUpdatableDataObjects(
			optimizer,
			condition,
			order,
			adjuster,
			rowLockOption));
	}

	/**
	 * パラメータの条件にマッチするレコードを検索し、 {@link t_commentIterator} として返します。
	 * <br>
	 * {@link RowLockOption} には {@link RowLockOption#NONE} が使用されます。
	 *
	 * @param optimizer SELECT 句を制御する {@link Optimizer}
	 * @param condition WHERE 句となる条件
	 * @param order  ORDER 句
	 * @return {@link DTOIterator}
	 */
	public t_commentIterator select(
		Optimizer optimizer,
		Condition condition,
		OrderByClause order) {
		return select(optimizer, condition, order, null, RowLockOption.NONE);
	}

	/**
	 * t_commentDTO を生成するメソッドです。
	 */
	@Override
	protected t_commentDTO createDTO(UpdatableDataObject data) {
		return new t_commentDTO(data);
	}

	@Override
	protected ResourceLocator getResourceLocator() {
		return t_comment.RESOURCE_LOCATOR;
	}

	/**
	 * {@link t_commentDAO} が使用する Iterator クラスです。
	 */
	public class t_commentIterator extends DTOIterator<t_commentDTO> {

		/**
		 * 唯一のコンストラクタです。
		 *
		 * @param iterator 
		 */
		private t_commentIterator(
			DataObjectIterator<UpdatableDataObject> iterator) {
			super(iterator);
		}

		@Override
		public t_commentDTO next() {
			return createDTO(nextDataObject());
		}
	}
}
