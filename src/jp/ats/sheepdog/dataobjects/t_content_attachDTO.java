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
 * テーブル名 t_content_attach
 * 親クラス名 java.lang.Object
 */
public class t_content_attachDTO extends java.lang.Object implements DTO {

	/**
	 * 内部で使用する {@link UpdatableDataObject}
	 */
	private final UpdatableDataObject data;

	private final Relationship relationship;

	/**
	 * 登録用コンストラクタです。
	 */
	public t_content_attachDTO() {
		relationship = Relationship.getInstance(t_content_attach.RESOURCE_LOCATOR);
		data = new UpdatableDataObject(relationship);
	}

	/**
	 * 参照、更新用コンストラクタです。
	 *
	 * @param data 値を持つ {@link UpdatableDataObject}
	 */
	public t_content_attachDTO(UpdatableDataObject data) {
		relationship = Relationship.getInstance(t_content_attach.RESOURCE_LOCATOR);
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
	 * 型 java.lang.Long
	 *
	 * @param value 更新値
	 */
	public void setid(java.lang.Long value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("id").getType());
		data.setValue("id", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 id
	 * 型 java.lang.Long
	 *
	 * @return 返却値
	 */
	public java.lang.Long getid() {
		Binder binder = data.getBinder("id");
		if (binder == null) return null;
		return (java.lang.Long) binder.getValue();
	}

	/**
	 * setter
	 *
	 * 項目名 attach_id
	 * 型 java.lang.Integer
	 *
	 * @param value 更新値
	 */
	public void setattach_id(java.lang.Integer value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("attach_id").getType());
		data.setValue("attach_id", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 attach_id
	 * 型 java.lang.Integer
	 *
	 * @return 返却値
	 */
	public java.lang.Integer getattach_id() {
		Binder binder = data.getBinder("attach_id");
		if (binder == null) return null;
		return (java.lang.Integer) binder.getValue();
	}

	/**
	 * setter
	 *
	 * 項目名 content_id
	 * 型 java.lang.Integer
	 *
	 * @param value 更新値
	 */
	public void setcontent_id(java.lang.Integer value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(
				relationship.getColumn("content_id").getType());
		data.setValue("content_id", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 content_id
	 * 型 java.lang.Integer
	 *
	 * @return 返却値
	 */
	public java.lang.Integer getcontent_id() {
		Binder binder = data.getBinder("content_id");
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
	 * このレコードが参照しているレコードの DTO を返します。
	 *
	 * 参照先テーブル名 t_attach
	 * 外部キー名 t_content_attach_attach_id_fkey
	 * 項目名 attach_id
	 *
	 * @return 参照しているレコードの DTO
	 */
	public t_attachDTO gett_attachByt_content_attach_attach_id_fkey() {
		return new t_attachDTO(
			data.getDataObject(t_content_attach.t_attach_BY_t_content_attach_attach_id_fkey));
	}

	/**
	 * このレコードが参照しているレコードの DTO を返します。
	 *
	 * 参照先テーブル名 t_content
	 * 外部キー名 t_content_attach_content_id_fkey
	 * 項目名 content_id
	 *
	 * @return 参照しているレコードの DTO
	 */
	public t_contentDTO gett_contentByt_content_attach_content_id_fkey() {
		return new t_contentDTO(
			data.getDataObject(t_content_attach.t_content_BY_t_content_attach_content_id_fkey));
	}

}
