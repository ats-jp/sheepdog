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
 * テーブル名 v_comment_relationship
 * 親クラス名 java.lang.Object
 */
public class v_comment_relationshipDTO extends java.lang.Object implements DTO {

	/**
	 * 内部で使用する {@link UpdatableDataObject}
	 */
	private final UpdatableDataObject data;

	private final Relationship relationship;

	/**
	 * 登録用コンストラクタです。
	 */
	public v_comment_relationshipDTO() {
		relationship = Relationship.getInstance(v_comment_relationship.RESOURCE_LOCATOR);
		data = new UpdatableDataObject(relationship);
	}

	/**
	 * 参照、更新用コンストラクタです。
	 *
	 * @param data 値を持つ {@link UpdatableDataObject}
	 */
	public v_comment_relationshipDTO(UpdatableDataObject data) {
		relationship = Relationship.getInstance(v_comment_relationship.RESOURCE_LOCATOR);
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
	 * 項目名 comment_id
	 * 型 java.lang.Integer
	 *
	 * @param value 更新値
	 */
	public void setcomment_id(java.lang.Integer value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(
				relationship.getColumn("comment_id").getType());
		data.setValue("comment_id", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 comment_id
	 * 型 java.lang.Integer
	 *
	 * @return 返却値
	 */
	public java.lang.Integer getcomment_id() {
		Binder binder = data.getBinder("comment_id");
		if (binder == null) return null;
		return (java.lang.Integer) binder.getValue();
	}

	/**
	 * setter
	 *
	 * 項目名 thread_id
	 * 型 java.lang.Integer
	 *
	 * @param value 更新値
	 */
	public void setthread_id(java.lang.Integer value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("thread_id").getType());
		data.setValue("thread_id", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 thread_id
	 * 型 java.lang.Integer
	 *
	 * @return 返却値
	 */
	public java.lang.Integer getthread_id() {
		Binder binder = data.getBinder("thread_id");
		if (binder == null) return null;
		return (java.lang.Integer) binder.getValue();
	}

	/**
	 * setter
	 *
	 * 項目名 comment_number
	 * 型 java.lang.Integer
	 *
	 * @param value 更新値
	 */
	public void setcomment_number(java.lang.Integer value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(
				relationship.getColumn("comment_number").getType());
		data.setValue("comment_number", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 comment_number
	 * 型 java.lang.Integer
	 *
	 * @return 返却値
	 */
	public java.lang.Integer getcomment_number() {
		Binder binder = data.getBinder("comment_number");
		if (binder == null) return null;
		return (java.lang.Integer) binder.getValue();
	}

	/**
	 * setter
	 *
	 * 項目名 parent_id
	 * 型 java.lang.Integer
	 *
	 * @param value 更新値
	 */
	public void setparent_id(java.lang.Integer value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("parent_id").getType());
		data.setValue("parent_id", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 parent_id
	 * 型 java.lang.Integer
	 *
	 * @return 返却値
	 */
	public java.lang.Integer getparent_id() {
		Binder binder = data.getBinder("parent_id");
		if (binder == null) return null;
		return (java.lang.Integer) binder.getValue();
	}

	/**
	 * setter
	 *
	 * 項目名 parent_number
	 * 型 java.lang.Integer
	 *
	 * @param value 更新値
	 */
	public void setparent_number(java.lang.Integer value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(
				relationship.getColumn("parent_number").getType());
		data.setValue("parent_number", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 parent_number
	 * 型 java.lang.Integer
	 *
	 * @return 返却値
	 */
	public java.lang.Integer getparent_number() {
		Binder binder = data.getBinder("parent_number");
		if (binder == null) return null;
		return (java.lang.Integer) binder.getValue();
	}

	/**
	 * setter
	 *
	 * 項目名 attach_count
	 * 型 java.lang.Long
	 *
	 * @param value 更新値
	 */
	public void setattach_count(java.lang.Long value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(
				relationship.getColumn("attach_count").getType());
		data.setValue("attach_count", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 attach_count
	 * 型 java.lang.Long
	 *
	 * @return 返却値
	 */
	public java.lang.Long getattach_count() {
		Binder binder = data.getBinder("attach_count");
		if (binder == null) return null;
		return (java.lang.Long) binder.getValue();
	}

	/**
	 * このレコードが参照しているレコードの DTO を返します。
	 *
	 * 参照先テーブル名 t_thread
	 * 外部キー名 v_comment_relationship_thread_id_fkey
	 * 項目名 thread_id
	 *
	 * @return 参照しているレコードの DTO
	 */
	public t_threadDTO gett_threadByv_comment_relationship_thread_id_fkey() {
		return new t_threadDTO(
			data.getDataObject(v_comment_relationship.t_thread_BY_v_comment_relationship_thread_id_fkey));
	}

}
