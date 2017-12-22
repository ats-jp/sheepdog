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
 * テーブル名 t_thread
 * 親クラス名 java.lang.Object
 */
public class t_threadDTO extends java.lang.Object implements DTO {

	/**
	 * 内部で使用する {@link UpdatableDataObject}
	 */
	private final UpdatableDataObject data;

	private final Relationship relationship;

	/**
	 * 登録用コンストラクタです。
	 */
	public t_threadDTO() {
		relationship = Relationship.getInstance(t_thread.RESOURCE_LOCATOR);
		data = new UpdatableDataObject(relationship);
	}

	/**
	 * 参照、更新用コンストラクタです。
	 *
	 * @param data 値を持つ {@link UpdatableDataObject}
	 */
	public t_threadDTO(UpdatableDataObject data) {
		relationship = Relationship.getInstance(t_thread.RESOURCE_LOCATOR);
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
	 * 項目名 calendar
	 * 型 java.lang.String
	 *
	 * @param value 更新値
	 */
	public void setcalendar(java.lang.String value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("calendar").getType());
		data.setValue("calendar", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 calendar
	 * 型 java.lang.String
	 *
	 * @return 返却値
	 */
	public java.lang.String getcalendar() {
		Binder binder = data.getBinder("calendar");
		if (binder == null) return null;
		return (java.lang.String) binder.getValue();
	}

	/**
	 * setter
	 *
	 * 項目名 group_id
	 * 型 java.lang.Integer
	 *
	 * @param value 更新値
	 */
	public void setgroup_id(java.lang.Integer value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("group_id").getType());
		data.setValue("group_id", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 group_id
	 * 型 java.lang.Integer
	 *
	 * @return 返却値
	 */
	public java.lang.Integer getgroup_id() {
		Binder binder = data.getBinder("group_id");
		if (binder == null) return null;
		return (java.lang.Integer) binder.getValue();
	}

	/**
	 * setter
	 *
	 * 項目名 event_flag
	 * 型 java.lang.Integer
	 *
	 * @param value 更新値
	 */
	public void setevent_flag(java.lang.Integer value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(
				relationship.getColumn("event_flag").getType());
		data.setValue("event_flag", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 event_flag
	 * 型 java.lang.Integer
	 *
	 * @return 返却値
	 */
	public java.lang.Integer getevent_flag() {
		Binder binder = data.getBinder("event_flag");
		if (binder == null) return null;
		return (java.lang.Integer) binder.getValue();
	}

	/**
	 * setter
	 *
	 * 項目名 last_title
	 * 型 java.lang.String
	 *
	 * @param value 更新値
	 */
	public void setlast_title(java.lang.String value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(
				relationship.getColumn("last_title").getType());
		data.setValue("last_title", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 last_title
	 * 型 java.lang.String
	 *
	 * @return 返却値
	 */
	public java.lang.String getlast_title() {
		Binder binder = data.getBinder("last_title");
		if (binder == null) return null;
		return (java.lang.String) binder.getValue();
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
	 * 項目名 applying_level
	 * 型 java.lang.Integer
	 *
	 * @param value 更新値
	 */
	public void setapplying_level(java.lang.Integer value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(
				relationship.getColumn("applying_level").getType());
		data.setValue("applying_level", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 applying_level
	 * 型 java.lang.Integer
	 *
	 * @return 返却値
	 */
	public java.lang.Integer getapplying_level() {
		Binder binder = data.getBinder("applying_level");
		if (binder == null) return null;
		return (java.lang.Integer) binder.getValue();
	}

	/**
	 * setter
	 *
	 * 項目名 private_flag
	 * 型 java.lang.Integer
	 *
	 * @param value 更新値
	 */
	public void setprivate_flag(java.lang.Integer value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(
				relationship.getColumn("private_flag").getType());
		data.setValue("private_flag", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 private_flag
	 * 型 java.lang.Integer
	 *
	 * @return 返却値
	 */
	public java.lang.Integer getprivate_flag() {
		Binder binder = data.getBinder("private_flag");
		if (binder == null) return null;
		return (java.lang.Integer) binder.getValue();
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
	 * 参照先テーブル名 t_group
	 * 外部キー名 t_thread_group_id_fkey
	 * 項目名 group_id
	 *
	 * @return 参照しているレコードの DTO
	 */
	public t_groupDTO gett_groupByt_thread_group_id_fkey() {
		return new t_groupDTO(
			data.getDataObject(t_thread.t_group_BY_t_thread_group_id_fkey));
	}

	/**
	 * このレコードが参照しているレコードの DTO を返します。
	 *
	 * 参照先テーブル名 t_user
	 * 外部キー名 t_thread_owner_fkey
	 * 項目名 owner
	 *
	 * @return 参照しているレコードの DTO
	 */
	public t_userDTO gett_userByt_thread_owner_fkey() {
		return new t_userDTO(
			data.getDataObject(t_thread.t_user_BY_t_thread_owner_fkey));
	}

}
