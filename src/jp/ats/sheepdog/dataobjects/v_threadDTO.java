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
 * テーブル名 v_thread
 * 親クラス名 java.lang.Object
 */
public class v_threadDTO extends java.lang.Object implements DTO {

	/**
	 * 内部で使用する {@link UpdatableDataObject}
	 */
	private final UpdatableDataObject data;

	private final Relationship relationship;

	/**
	 * 登録用コンストラクタです。
	 */
	public v_threadDTO() {
		relationship = Relationship.getInstance(v_thread.RESOURCE_LOCATOR);
		data = new UpdatableDataObject(relationship);
	}

	/**
	 * 参照、更新用コンストラクタです。
	 *
	 * @param data 値を持つ {@link UpdatableDataObject}
	 */
	public v_threadDTO(UpdatableDataObject data) {
		relationship = Relationship.getInstance(v_thread.RESOURCE_LOCATOR);
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
	 * 項目名 title
	 * 型 java.lang.String
	 *
	 * @param value 更新値
	 */
	public void settitle(java.lang.String value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("title").getType());
		data.setValue("title", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 title
	 * 型 java.lang.String
	 *
	 * @return 返却値
	 */
	public java.lang.String gettitle() {
		Binder binder = data.getBinder("title");
		if (binder == null) return null;
		return (java.lang.String) binder.getValue();
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
	 * 項目名 content_count
	 * 型 java.lang.Long
	 *
	 * @param value 更新値
	 */
	public void setcontent_count(java.lang.Long value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(
				relationship.getColumn("content_count").getType());
		data.setValue("content_count", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 content_count
	 * 型 java.lang.Long
	 *
	 * @return 返却値
	 */
	public java.lang.Long getcontent_count() {
		Binder binder = data.getBinder("content_count");
		if (binder == null) return null;
		return (java.lang.Long) binder.getValue();
	}

	/**
	 * setter
	 *
	 * 項目名 comment_count
	 * 型 java.lang.Long
	 *
	 * @param value 更新値
	 */
	public void setcomment_count(java.lang.Long value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(
				relationship.getColumn("comment_count").getType());
		data.setValue("comment_count", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 comment_count
	 * 型 java.lang.Long
	 *
	 * @return 返却値
	 */
	public java.lang.Long getcomment_count() {
		Binder binder = data.getBinder("comment_count");
		if (binder == null) return null;
		return (java.lang.Long) binder.getValue();
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
	 * setter
	 *
	 * 項目名 content_create_time
	 * 型 java.sql.Timestamp
	 *
	 * @param value 更新値
	 */
	public void setcontent_create_time(java.sql.Timestamp value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(
				relationship.getColumn("content_create_time").getType());
		data.setValue(
			"content_create_time",
			valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 content_create_time
	 * 型 java.sql.Timestamp
	 *
	 * @return 返却値
	 */
	public java.sql.Timestamp getcontent_create_time() {
		Binder binder = data.getBinder("content_create_time");
		if (binder == null) return null;
		return (java.sql.Timestamp) binder.getValue();
	}

	/**
	 * このレコードが参照しているレコードの DTO を返します。
	 *
	 * 参照先テーブル名 t_user
	 * 外部キー名 v_thread_owner_fkey
	 * 項目名 owner
	 *
	 * @return 参照しているレコードの DTO
	 */
	public t_userDTO gett_userByv_thread_owner_fkey() {
		return new t_userDTO(
			data.getDataObject(v_thread.t_user_BY_v_thread_owner_fkey));
	}

	/**
	 * このレコードが参照しているレコードの DTO を返します。
	 *
	 * 参照先テーブル名 t_group
	 * 外部キー名 v_thread_group_id_fkey
	 * 項目名 group_id
	 *
	 * @return 参照しているレコードの DTO
	 */
	public t_groupDTO gett_groupByv_thread_group_id_fkey() {
		return new t_groupDTO(
			data.getDataObject(v_thread.t_group_BY_v_thread_group_id_fkey));
	}

}
