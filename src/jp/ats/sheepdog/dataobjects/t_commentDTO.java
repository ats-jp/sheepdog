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
 * テーブル名 t_comment
 * 親クラス名 java.lang.Object
 */
public class t_commentDTO extends java.lang.Object implements DTO {

	/**
	 * 内部で使用する {@link UpdatableDataObject}
	 */
	private final UpdatableDataObject data;

	private final Relationship relationship;

	/**
	 * 登録用コンストラクタです。
	 */
	public t_commentDTO() {
		relationship = Relationship.getInstance(t_comment.RESOURCE_LOCATOR);
		data = new UpdatableDataObject(relationship);
	}

	/**
	 * 参照、更新用コンストラクタです。
	 *
	 * @param data 値を持つ {@link UpdatableDataObject}
	 */
	public t_commentDTO(UpdatableDataObject data) {
		relationship = Relationship.getInstance(t_comment.RESOURCE_LOCATOR);
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
	 * 項目名 body
	 * 型 java.lang.String
	 *
	 * @param value 更新値
	 */
	public void setbody(java.lang.String value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("body").getType());
		data.setValue("body", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 body
	 * 型 java.lang.String
	 *
	 * @return 返却値
	 */
	public java.lang.String getbody() {
		Binder binder = data.getBinder("body");
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

	/**
	 * このレコードが参照しているレコードの DTO を返します。
	 *
	 * 参照先テーブル名 v_comment_relationship
	 * 外部キー名 t_comment_id_fkey
	 * 項目名 id
	 *
	 * @return 参照しているレコードの DTO
	 */
	public v_comment_relationshipDTO getv_comment_relationshipByt_comment_id_fkey() {
		return new v_comment_relationshipDTO(
			data.getDataObject(t_comment.v_comment_relationship_BY_t_comment_id_fkey));
	}

	/**
	 * このレコードが参照しているレコードの DTO を返します。
	 *
	 * 参照先テーブル名 t_comment
	 * 外部キー名 t_comment_parent_id_fkey
	 * 項目名 parent_id
	 *
	 * @return 参照しているレコードの DTO
	 */
	public t_commentDTO gett_commentByt_comment_parent_id_fkey() {
		return new t_commentDTO(
			data.getDataObject(t_comment.t_comment_BY_t_comment_parent_id_fkey));
	}

	/**
	 * このレコードが参照しているレコードの DTO を返します。
	 *
	 * 参照先テーブル名 t_thread
	 * 外部キー名 t_comment_thread_id_fkey
	 * 項目名 thread_id
	 *
	 * @return 参照しているレコードの DTO
	 */
	public t_threadDTO gett_threadByt_comment_thread_id_fkey() {
		return new t_threadDTO(
			data.getDataObject(t_comment.t_thread_BY_t_comment_thread_id_fkey));
	}

	/**
	 * このレコードが参照しているレコードの DTO を返します。
	 *
	 * 参照先テーブル名 t_user
	 * 外部キー名 t_comment_owner_fkey
	 * 項目名 owner
	 *
	 * @return 参照しているレコードの DTO
	 */
	public t_userDTO gett_userByt_comment_owner_fkey() {
		return new t_userDTO(
			data.getDataObject(t_comment.t_user_BY_t_comment_owner_fkey));
	}

}
