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
 * テーブル名 t_subscribe
 * 親クラス名 java.lang.Object
 */
public class t_subscribeDTO extends java.lang.Object implements DTO {

	/**
	 * 内部で使用する {@link UpdatableDataObject}
	 */
	private final UpdatableDataObject data;

	private final Relationship relationship;

	/**
	 * 登録用コンストラクタです。
	 */
	public t_subscribeDTO() {
		relationship = Relationship.getInstance(t_subscribe.RESOURCE_LOCATOR);
		data = new UpdatableDataObject(relationship);
	}

	/**
	 * 参照、更新用コンストラクタです。
	 *
	 * @param data 値を持つ {@link UpdatableDataObject}
	 */
	public t_subscribeDTO(UpdatableDataObject data) {
		relationship = Relationship.getInstance(t_subscribe.RESOURCE_LOCATOR);
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
	 * 項目名 group_for_thread
	 * 型 java.lang.Integer
	 *
	 * @param value 更新値
	 */
	public void setgroup_for_thread(java.lang.Integer value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(
				relationship.getColumn("group_for_thread").getType());
		data.setValue("group_for_thread", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 group_for_thread
	 * 型 java.lang.Integer
	 *
	 * @return 返却値
	 */
	public java.lang.Integer getgroup_for_thread() {
		Binder binder = data.getBinder("group_for_thread");
		if (binder == null) return null;
		return (java.lang.Integer) binder.getValue();
	}

	/**
	 * setter
	 *
	 * 項目名 thread_for_comment
	 * 型 java.lang.Integer
	 *
	 * @param value 更新値
	 */
	public void setthread_for_comment(java.lang.Integer value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(
				relationship.getColumn("thread_for_comment").getType());
		data.setValue(
			"thread_for_comment",
			valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 thread_for_comment
	 * 型 java.lang.Integer
	 *
	 * @return 返却値
	 */
	public java.lang.Integer getthread_for_comment() {
		Binder binder = data.getBinder("thread_for_comment");
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
	 * 参照先テーブル名 t_group
	 * 外部キー名 t_subscribe_group_for_thread_fkey
	 * 項目名 group_for_thread
	 *
	 * @return 参照しているレコードの DTO
	 */
	public t_groupDTO gett_groupByt_subscribe_group_for_thread_fkey() {
		return new t_groupDTO(
			data.getDataObject(t_subscribe.t_group_BY_t_subscribe_group_for_thread_fkey));
	}

	/**
	 * このレコードが参照しているレコードの DTO を返します。
	 *
	 * 参照先テーブル名 t_thread
	 * 外部キー名 t_subscribe_thread_for_comment_fkey
	 * 項目名 thread_for_comment
	 *
	 * @return 参照しているレコードの DTO
	 */
	public t_threadDTO gett_threadByt_subscribe_thread_for_comment_fkey() {
		return new t_threadDTO(
			data.getDataObject(t_subscribe.t_thread_BY_t_subscribe_thread_for_comment_fkey));
	}

	/**
	 * このレコードが参照しているレコードの DTO を返します。
	 *
	 * 参照先テーブル名 t_user
	 * 外部キー名 t_subscribe_owner_fkey
	 * 項目名 owner
	 *
	 * @return 参照しているレコードの DTO
	 */
	public t_userDTO gett_userByt_subscribe_owner_fkey() {
		return new t_userDTO(
			data.getDataObject(t_subscribe.t_user_BY_t_subscribe_owner_fkey));
	}

}
