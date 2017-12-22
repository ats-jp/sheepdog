package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.extension.DTO;
import jp.ats.liverwort.extension.DTOIterator;
import jp.ats.liverwort.jdbc.BatchStatement;
import jp.ats.liverwort.jdbc.ResourceLocator;
import jp.ats.liverwort.ormapping.DataAccessHelper;
import jp.ats.liverwort.ormapping.DataAccessHelper.RowLockOption;
import jp.ats.liverwort.ormapping.DataObjectNotFoundException;
import jp.ats.liverwort.ormapping.PrimaryKey;
import jp.ats.liverwort.ormapping.SequenceGenerator;
import jp.ats.liverwort.ormapping.UpdatableDataObject;
import jp.ats.liverwort.selector.Optimizer;
import jp.ats.liverwort.selector.SimpleOptimizer;
import jp.ats.liverwort.sql.Bindable;
import jp.ats.liverwort.sql.Condition;
import jp.ats.liverwort.sql.SQLAdjuster;
import jp.ats.liverwort.sql.Updatable;

/**
 * 自動生成された基底 DAO クラスです。
 *
 * パッケージ名 jp.ats.sheepdog.dataobjects
 * 親クラス名 java.lang.Object
 *
 * @param <T> DTOの型
 */
public abstract class GenericDAO<T extends DTO> extends java.lang.Object {

	/**
	 * 空の検索結果
	 */
	private static final DTOIterator<DTO> emptyIterator = new DTOIterator<DTO>(
		DataAccessHelper.EMPTY_UPDATABLE_DATA_OBJECT_ITERATOR) {

		@Override
		public DTO next() {
			throw new UnsupportedOperationException();
		}
	};

	/**
	 * この DAO で使用する {@link DataAccessHelper}
	 */
	protected final DataAccessHelper helper = new DataAccessHelper();

	/**
	 * 空の DTOIterator を返します。
	 *
	 * @param <T> {@link DTOIterator} の要素型
	 * @return {@link DTOIterator}
	 */
	@SuppressWarnings("unchecked")
	public static <T extends DTO> DTOIterator<T> getEmptyDTOIterator() {
		return (DTOIterator<T>) emptyIterator;
	}

	/**
	 * パラメータの主キーの値を持つ {@link DTO} を検索し返します。
	 * <br>
	 * {@link Optimizer} には {@link SimpleOptimizer} が使用されます。
	 *
	 * @param rowLockOption 行ロックオプション
	 * @param primaryKeyMembers 主キーを構成する文字列
	 * @return {@link DTO} 存在しなければ null
	 */
	public T select(RowLockOption rowLockOption, String... primaryKeyMembers) {
		return select(
			new SimpleOptimizer(getResourceLocator()),
			rowLockOption,
			primaryKeyMembers);
	}

	/**
	 * パラメータの主キーの値を持つ {@link DTO} を検索し返します。
	 * <br>
	 * {@link Optimizer} には {@link SimpleOptimizer} が使用されます。
	 * <br>
	 * {@link RowLockOption} には {@link RowLockOption#NONE} が使用されます。
	 *
	 * @param primaryKeyMembers 主キーを構成する文字列
	 * @return {@link DTO} 存在しなければ null
	 */
	public T select(String... primaryKeyMembers) {
		return select(RowLockOption.NONE, primaryKeyMembers);
	}

	/**
	 * パラメータの主キーの値を持つ {@link DTO} を検索し返します。
	 * <br>
	 * {@link Optimizer} には {@link SimpleOptimizer} が使用されます。
	 *
	 * @param rowLockOption 行ロックオプション
	 * @param primaryKeyMembers 主キーを構成する値
	 * @return {@link DTO} 存在しなければ null
	 */
	public T select(RowLockOption rowLockOption, Bindable... primaryKeyMembers) {
		return select(
			new SimpleOptimizer(getResourceLocator()),
			rowLockOption,
			primaryKeyMembers);
	}

	/**
	 * パラメータの主キーの値を持つ {@link DTO} を検索し返します。
	 * <br>
	 * {@link Optimizer} には {@link SimpleOptimizer} が使用されます。
	 * <br>
	 * {@link RowLockOption} には {@link RowLockOption#NONE} が使用されます。
	 *
	 * @param primaryKeyMembers 主キーを構成する値
	 * @return {@link DTO} 存在しなければ null
	 */
	public T select(Bindable... primaryKeyMembers) {
		return select(RowLockOption.NONE, primaryKeyMembers);
	}

	/**
	 * パラメータの主キーの値を持つ {@link DTO} を検索し返します。
	 *
	 * @param optimizer SELECT 句を制御する {@link Optimizer}
	 * @param rowLockOption 行ロックオプション
	 * @param primaryKeyMembers 主キーを構成する文字列
	 * @return {@link DTO} 存在しなければ null
	 */
	public T select(
		Optimizer optimizer,
		RowLockOption rowLockOption,
		String... primaryKeyMembers) {
		UpdatableDataObject object;
		try {
			object = helper.getUpdatableDataObject(
				optimizer,
				PrimaryKey.getInstance(getResourceLocator(), primaryKeyMembers),
				rowLockOption);
		} catch (DataObjectNotFoundException e) {
			return null;
		}
		return createDTO(object);
	}

	/**
	 * パラメータの主キーの値を持つ {@link DTO} を検索し返します。
	 * <br>
	 * {@link RowLockOption} には {@link RowLockOption#NONE} が使用されます。
	 *
	 * @param optimizer SELECT 句を制御する {@link Optimizer}
	 * @param primaryKeyMembers 主キーを構成する文字列
	 * @return {@link DTO} 存在しなければ null
	 */
	public T select(Optimizer optimizer, String... primaryKeyMembers) {
		return select(optimizer, RowLockOption.NONE, primaryKeyMembers);
	}

	/**
	 * パラメータの主キーの値を持つ {@link DTO} を検索し返します。
	 *
	 * @param optimizer SELECT 句を制御する {@link Optimizer}
	 * @param rowLockOption 行ロックオプション
	 * @param primaryKeyMembers 主キーを構成する値
	 * @return {@link DTO} 存在しなければ null
	 */
	public T select(
		Optimizer optimizer,
		RowLockOption rowLockOption,
		Bindable... primaryKeyMembers) {
		UpdatableDataObject object;
		try {
			object = helper.getUpdatableDataObject(optimizer, new PrimaryKey(
				getResourceLocator(),
				primaryKeyMembers), rowLockOption);
		} catch (DataObjectNotFoundException e) {
			return null;
		}
		return createDTO(object);
	}

	/**
	 * パラメータの主キーの値を持つ {@link DTO} を検索し返します。
	 * <br>
	 * {@link RowLockOption} には {@link RowLockOption#NONE} が使用されます。
	 *
	 * @param optimizer SELECT 句を制御する {@link Optimizer}
	 * @param primaryKeyMembers 主キーを構成する値
	 * @return {@link DTO} 存在しなければ null
	 */
	public T select(Optimizer optimizer, Bindable... primaryKeyMembers) {
		return select(optimizer, RowLockOption.NONE, primaryKeyMembers);
	}

	/**
	 * パラメータの条件にマッチする件数を返します。
	 *
	 * @param condition WHERE 句となる条件
	 * @return パラメータの条件にマッチする件数
	 */
	public int count(Condition condition) {
		return helper.count(getResourceLocator(), condition);
	}

	/**
	 * パラメータの DTO の INSERT を行います。
	 *
	 * @param dto INSERT 対象
	 */
	public void insert(T dto) {
		helper.insert(getResourceLocator(), dto, null);
	}

	/**
	 * パラメータの DTO の INSERT をバッチ実行します。
	 *
	 * @param statement バッチ実行を依頼する {@link BatchStatement}
	 * @param dto INSERT 対象
	 */
	public void insert(BatchStatement statement, T dto) {
		helper.insert(statement, getResourceLocator(), dto, null);
	}

	/**
	 * パラメータの DTO の INSERT を行います。
	 *
	 * @param dto INSERT 対象
	 * @param adjuster INSERT 文を調整する {@link SQLAdjuster}
	 */
	public void insert(T dto, SQLAdjuster adjuster) {
		helper.insert(getResourceLocator(), dto, adjuster);
	}

	/**
	 * パラメータの DTO の INSERT をバッチ実行します。
	 *
	 * @param statement バッチ実行を依頼する {@link BatchStatement}
	 * @param dto INSERT 対象
	 * @param adjuster INSERT 文を調整する {@link SQLAdjuster}
	 */
	public void insert(BatchStatement statement, T dto, SQLAdjuster adjuster) {
		helper.insert(statement, getResourceLocator(), dto, adjuster);
	}

	/**
	 * パラメータの DTO の INSERT を行います。
	 *
	 * @param generator 対象となる項目と値を持つ {@link SequenceGenerator}
	 * @param dto INSERT 対象
	 * @param retry {@link SequenceGenerator} のリトライ回数
	 * @return INSERT された実際の連続値
	 */
	public Bindable insert(SequenceGenerator generator, T dto, int retry) {
		return helper.insert(getResourceLocator(), generator, dto, retry, null);
	}

	/**
	 * パラメータの DTO の INSERT をバッチ実行します。
	 *
	 * @param statement バッチ実行を依頼する {@link BatchStatement}
	 * @param generator 対象となる項目と値を持つ {@link SequenceGenerator}
	 * @param dto INSERT 対象
	 * @param retry {@link SequenceGenerator} のリトライ回数
	 * @return INSERT された実際の連続値
	 */
	public Bindable insert(
		BatchStatement statement,
		SequenceGenerator generator,
		T dto,
		int retry) {
		return helper.insert(
			statement,
			getResourceLocator(),
			generator,
			dto,
			retry,
			null);
	}

	/**
	 * パラメータの DTO の INSERT を行います。
	 *
	 * @param generator 対象となる項目と値を持つ {@link SequenceGenerator}
	 * @param dto INSERT 対象
	 * @param retry {@link SequenceGenerator} のリトライ回数
	 * @param adjuster INSERT 文を調整する {@link SQLAdjuster}
	 * @return INSERT された実際の連続値
	 */
	public Bindable insert(
		SequenceGenerator generator,
		T dto,
		int retry,
		SQLAdjuster adjuster) {
		return helper.insert(
			getResourceLocator(),
			generator,
			dto,
			retry,
			adjuster);
	}

	/**
	 * パラメータの DTO の INSERT をバッチ実行します。
	 *
	 * @param statement バッチ実行を依頼する {@link BatchStatement}
	 * @param generator 対象となる項目と値を持つ {@link SequenceGenerator}
	 * @param dto INSERT 対象
	 * @param retry {@link SequenceGenerator} のリトライ回数
	 * @param adjuster INSERT 文を調整する {@link SQLAdjuster}
	 * @return INSERT された実際の連続値
	 */
	public Bindable insert(
		BatchStatement statement,
		SequenceGenerator generator,
		T dto,
		int retry,
		SQLAdjuster adjuster) {
		return helper.insert(
			statement,
			getResourceLocator(),
			generator,
			dto,
			retry,
			adjuster);
	}

	/**
	 * パラメータの DTO の DELETE を行います。
	 *
	 * @param dto DELETE 対象
	 * @return 削除が成功した場合、 true
	 */
	public boolean delete(T dto) {
		int result = helper.delete(getResourceLocator(), dto.getPrimaryKey()
			.getCondition());
		if (result > 1) throw new IllegalStateException("削除件数が複数件あります。");
		return result == 1;
	}

	/**
	 * パラメータの DTO の DELETE をバッチ実行します。
	 *
	 * @param statement バッチ実行を依頼する {@link BatchStatement}
	 * @param dto DELETE 対象
	 */
	public void delete(BatchStatement statement, T dto) {
		helper.delete(statement, getResourceLocator(), dto.getPrimaryKey()
			.getCondition());
	}

	/**
	 * パラメータの条件に該当する行を更新します。
	 *
	 * @param condition WHERE 句となる条件
	 * @param updatable UPDATE する値を持つ {@link Updatable}
	 * @return 更新件数
	 */
	public int update(Condition condition, Updatable updatable) {
		return helper.update(getResourceLocator(), updatable, condition, null);
	}

	/**
	 * パラメータの条件に該当する行の更新をバッチ実行します。
	 *
	 * @param statement バッチ実行を依頼する {@link BatchStatement}
	 * @param condition WHERE 句となる条件
	 * @param updatable UPDATE する値を持つ {@link Updatable}
	 */
	public void update(
		BatchStatement statement,
		Condition condition,
		Updatable updatable) {
		helper.update(
			statement,
			getResourceLocator(),
			updatable,
			condition,
			null);
	}

	/**
	 * パラメータの条件に該当する行を更新します。
	 *
	 * @param condition WHERE 句となる条件
	 * @param updatable UPDATE する値を持つ {@link Updatable}
	 * @param adjuster UPDATE 文を調整する {@link SQLAdjuster}
	 * @return 更新件数
	 */
	public int update(
		Condition condition,
		Updatable updatable,
		SQLAdjuster adjuster) {
		return helper.update(
			getResourceLocator(),
			updatable,
			condition,
			adjuster);
	}

	/**
	 * パラメータの条件に該当する行の更新をバッチ実行します。
	 *
	 * @param statement バッチ実行を依頼する {@link BatchStatement}
	 * @param condition WHERE 句となる条件
	 * @param adjuster UPDATE 文を調整する {@link SQLAdjuster}
	 * @param updatable UPDATE する値を持つ {@link Updatable}
	 */
	public void update(
		BatchStatement statement,
		Condition condition,
		Updatable updatable,
		SQLAdjuster adjuster) {
		helper.update(
			statement,
			getResourceLocator(),
			updatable,
			condition,
			adjuster);
	}

	/**
	 * パラメータの条件に該当する行を削除します。
	 *
	 * @param condition WHERE 句となる条件
	 * @return 削除件数
	 */
	public int delete(Condition condition) {
		return helper.delete(getResourceLocator(), condition);
	}

	/**
	 * パラメータの条件に該当する行の削除をバッチ実行します。
	 *
	 * @param statement バッチ実行を依頼する {@link BatchStatement}
	 * @param condition WHERE 句となる条件
	 */
	public void delete(BatchStatement statement, Condition condition) {
		helper.delete(statement, getResourceLocator(), condition);
	}

	/**
	 * DTO を生成するメソッドです。
	 *
	 * @param data この DTO の全要素の値を持つ検索結果オブジェクト
	 * @return 生成された DTO
	 */
	protected abstract T createDTO(UpdatableDataObject data);

	/**
	 * サブクラスで固有の {@link ResourceLocator} を返します。
	 *
	 * @return 固有の {@link ResourceLocator}
	 */
	protected abstract ResourceLocator getResourceLocator();
}
