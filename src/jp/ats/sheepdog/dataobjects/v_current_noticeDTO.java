package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.extension.DTO;
import jp.ats.liverwort.jdbc.BatchStatement;
import jp.ats.liverwort.ormapping.PrimaryKey;
import jp.ats.liverwort.ormapping.UpdatableDataObject;
import jp.ats.liverwort.selector.Selector;
import jp.ats.liverwort.selector.ValueExtractor;
import jp.ats.liverwort.sql.Binder;
import jp.ats.liverwort.sql.Relationship;
import jp.ats.liverwort.sql.Updater;

/**
 * 自動生成された DTO クラスです。
 *
 * パッケージ名 jp.ats.sheepdog.dataobjects
 * テーブル名 v_current_notice
 * 親クラス名 java.lang.Object
 */
public class v_current_noticeDTO extends java.lang.Object implements DTO {

	/**
	 * 内部で使用する {@link UpdatableDataObject}
	 */
	private final UpdatableDataObject data;

	private final Relationship relationship;

	/**
	 * 登録用コンストラクタです。
	 */
	public v_current_noticeDTO() {
		relationship = Relationship.getInstance(v_current_notice.RESOURCE_LOCATOR);
		data = new UpdatableDataObject(relationship);
	}

	/**
	 * 参照、更新用コンストラクタです。
	 *
	 * @param data 値を持つ {@link UpdatableDataObject}
	 */
	public v_current_noticeDTO(UpdatableDataObject data) {
		relationship = Relationship.getInstance(v_current_notice.RESOURCE_LOCATOR);
		this.data = data;
	}

	@Override
	public boolean update() {
		return data.update();
	}

	@Override
	public void update(BatchStatement statement) {
		data.update(statement);
	}

	@Override
	public UpdatableDataObject getDataObject() {
		return data;
	}

	@Override
	public PrimaryKey getPrimaryKey() {
		return data.getPrimaryKey();
	}

	@Override
	public void setValuesTo(Updater updater) {
		data.setValuesTo(updater);
	}

	@Override
	public void commit() {
		data.commit();
	}

	@Override
	public void rollback() {
		data.rollback();
	}

	/**
	 * setter
	 *
	 * 項目名 owner
	 * 型 java.lang.Integer
	 *
	 * @param value 更新値
	 */
	public void setowner(java.lang.Integer value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("owner").getType());
		data.setValue("owner", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 owner
	 * 型 java.lang.Integer
	 *
	 * @return 返却値
	 */
	public java.lang.Integer getowner() {
		Binder binder = data.getBinder("owner");
		if (binder == null) return null;
		return (java.lang.Integer) binder.getValue();
	}

	/**
	 * setter
	 *
	 * 項目名 max_id
	 * 型 java.lang.Long
	 *
	 * @param value 更新値
	 */
	public void setmax_id(java.lang.Long value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("max_id").getType());
		data.setValue("max_id", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 max_id
	 * 型 java.lang.Long
	 *
	 * @return 返却値
	 */
	public java.lang.Long getmax_id() {
		Binder binder = data.getBinder("max_id");
		if (binder == null) return null;
		return (java.lang.Long) binder.getValue();
	}

	/**
	 * setter
	 *
	 * 項目名 last_id
	 * 型 java.lang.Long
	 *
	 * @param value 更新値
	 */
	public void setlast_id(java.lang.Long value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("last_id").getType());
		data.setValue("last_id", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 last_id
	 * 型 java.lang.Long
	 *
	 * @return 返却値
	 */
	public java.lang.Long getlast_id() {
		Binder binder = data.getBinder("last_id");
		if (binder == null) return null;
		return (java.lang.Long) binder.getValue();
	}

}
