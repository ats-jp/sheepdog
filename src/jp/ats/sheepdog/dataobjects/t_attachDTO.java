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
 * テーブル名 t_attach
 * 親クラス名 java.lang.Object
 */
public class t_attachDTO extends java.lang.Object implements DTO {

	/**
	 * 内部で使用する {@link UpdatableDataObject}
	 */
	private final UpdatableDataObject data;

	private final Relationship relationship;

	/**
	 * 登録用コンストラクタです。
	 */
	public t_attachDTO() {
		relationship = Relationship.getInstance(t_attach.RESOURCE_LOCATOR);
		data = new UpdatableDataObject(relationship);
	}

	/**
	 * 参照、更新用コンストラクタです。
	 *
	 * @param data 値を持つ {@link UpdatableDataObject}
	 */
	public t_attachDTO(UpdatableDataObject data) {
		relationship = Relationship.getInstance(t_attach.RESOURCE_LOCATOR);
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
	 * 項目名 id
	 * 型 java.lang.Integer
	 *
	 * @param value 更新値
	 */
	public void setid(java.lang.Integer value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("id").getType());
		data.setValue("id", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 id
	 * 型 java.lang.Integer
	 *
	 * @return 返却値
	 */
	public java.lang.Integer getid() {
		Binder binder = data.getBinder("id");
		if (binder == null) return null;
		return (java.lang.Integer) binder.getValue();
	}

	/**
	 * setter
	 *
	 * 項目名 name
	 * 型 java.lang.String
	 *
	 * @param value 更新値
	 */
	public void setname(java.lang.String value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("name").getType());
		data.setValue("name", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 name
	 * 型 java.lang.String
	 *
	 * @return 返却値
	 */
	public java.lang.String getname() {
		Binder binder = data.getBinder("name");
		if (binder == null) return null;
		return (java.lang.String) binder.getValue();
	}

	/**
	 * setter
	 *
	 * 項目名 size
	 * 型 java.lang.Integer
	 *
	 * @param value 更新値
	 */
	public void setsize(java.lang.Integer value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("size").getType());
		data.setValue("size", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 size
	 * 型 java.lang.Integer
	 *
	 * @return 返却値
	 */
	public java.lang.Integer getsize() {
		Binder binder = data.getBinder("size");
		if (binder == null) return null;
		return (java.lang.Integer) binder.getValue();
	}

	/**
	 * setter
	 *
	 * 項目名 path
	 * 型 java.lang.String
	 *
	 * @param value 更新値
	 */
	public void setpath(java.lang.String value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("path").getType());
		data.setValue("path", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 path
	 * 型 java.lang.String
	 *
	 * @return 返却値
	 */
	public java.lang.String getpath() {
		Binder binder = data.getBinder("path");
		if (binder == null) return null;
		return (java.lang.String) binder.getValue();
	}

	/**
	 * setter
	 *
	 * 項目名 delete_flag
	 * 型 java.lang.Integer
	 *
	 * @param value 更新値
	 */
	public void setdelete_flag(java.lang.Integer value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(
				relationship.getColumn("delete_flag").getType());
		data.setValue("delete_flag", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 delete_flag
	 * 型 java.lang.Integer
	 *
	 * @return 返却値
	 */
	public java.lang.Integer getdelete_flag() {
		Binder binder = data.getBinder("delete_flag");
		if (binder == null) return null;
		return (java.lang.Integer) binder.getValue();
	}

	/**
	 * setter
	 *
	 * 項目名 create_time
	 * 型 java.sql.Timestamp
	 *
	 * @param value 更新値
	 */
	public void setcreate_time(java.sql.Timestamp value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(
				relationship.getColumn("create_time").getType());
		data.setValue("create_time", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 create_time
	 * 型 java.sql.Timestamp
	 *
	 * @return 返却値
	 */
	public java.sql.Timestamp getcreate_time() {
		Binder binder = data.getBinder("create_time");
		if (binder == null) return null;
		return (java.sql.Timestamp) binder.getValue();
	}

	/**
	 * setter
	 *
	 * 項目名 update_time
	 * 型 java.sql.Timestamp
	 *
	 * @param value 更新値
	 */
	public void setupdate_time(java.sql.Timestamp value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(
				relationship.getColumn("update_time").getType());
		data.setValue("update_time", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 update_time
	 * 型 java.sql.Timestamp
	 *
	 * @return 返却値
	 */
	public java.sql.Timestamp getupdate_time() {
		Binder binder = data.getBinder("update_time");
		if (binder == null) return null;
		return (java.sql.Timestamp) binder.getValue();
	}

}
