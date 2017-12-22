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
 * テーブル名 v_comment_relationship
 */
public class v_comment_relationshipDAO
	extends GenericDAO<v_comment_relationshipDTO> {

	/**
	 * パラメータの条件にマッチするレコードを検索し、 {@link v_comment_relationshipIterator} として返します。
	 * <br>
	 * {@link Optimizer} には {@link SimpleOptimizer} が使用されます。
	 *
	 * @param condition WHERE 句となる条件
	 * @param order  ORDER 句
	 * @param adjuster 検索結果を調整する {@link SQLAdjuster}
	 * @param rowLockOption 行ロックオプション
	 * @return {@link DTOIterator}
	 */
	public v_comment_relationshipIterator select(
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
	 * パラメータの条件にマッチするレコードを検索し、 {@link v_comment_relationshipIterator} として返します。
	 * <br>
	 * {@link Optimizer} には {@link SimpleOptimizer} が使用されます。
	 * <br>
	 * {@link RowLockOption} には {@link RowLockOption#NONE} が使用されます。
	 *
	 * @param condition WHERE 句となる条件
	 * @param order  ORDER 句
	 * @return {@link DTOIterator}
	 */
	public v_comment_relationshipIterator select(
		Condition condition,
		OrderByClause order) {
		return select(condition, order, null, RowLockOption.NONE);
	}

	/**
	 * パラメータの条件にマッチするレコードを検索し、 {@link v_comment_relationshipIterator} として返します。
	 *
	 * @param optimizer SELECT 句を制御する {@link Optimizer}
	 * @param condition WHERE 句となる条件
	 * @param order  ORDER 句
	 * @param adjuster 検索結果を調整する {@link SQLAdjuster}
	 * @param rowLockOption 行ロックオプション
	 * @return {@link DTOIterator}
	 */
	public v_comment_relationshipIterator select(
		Optimizer optimizer,
		Condition condition,
		OrderByClause order,
		SQLAdjuster adjuster,
		RowLockOption rowLockOption) {
		return new v_comment_relationshipIterator(
			helper.getUpdatableDataObjects(
				optimizer,
				condition,
				order,
				adjuster,
				rowLockOption));
	}

	/**
	 * パラメータの条件にマッチするレコードを検索し、 {@link v_comment_relationshipIterator} として返します。
	 * <br>
	 * {@link RowLockOption} には {@link RowLockOption#NONE} が使用されます。
	 *
	 * @param optimizer SELECT 句を制御する {@link Optimizer}
	 * @param condition WHERE 句となる条件
	 * @param order  ORDER 句
	 * @return {@link DTOIterator}
	 */
	public v_comment_relationshipIterator select(
		Optimizer optimizer,
		Condition condition,
		OrderByClause order) {
		return select(optimizer, condition, order, null, RowLockOption.NONE);
	}

	/**
	 * v_comment_relationshipDTO を生成するメソッドです。
	 */
	@Override
	protected v_comment_relationshipDTO createDTO(UpdatableDataObject data) {
		return new v_comment_relationshipDTO(data);
	}

	@Override
	protected ResourceLocator getResourceLocator() {
		return v_comment_relationship.RESOURCE_LOCATOR;
	}

	/**
	 * {@link v_comment_relationshipDAO} が使用する Iterator クラスです。
	 */
	public class v_comment_relationshipIterator
		extends DTOIterator<v_comment_relationshipDTO> {

		/**
		 * 唯一のコンストラクタです。
		 *
		 * @param iterator 
		 */
		private v_comment_relationshipIterator(
			DataObjectIterator<UpdatableDataObject> iterator) {
			super(iterator);
		}

		@Override
		public v_comment_relationshipDTO next() {
			return createDTO(nextDataObject());
		}
	}
}
