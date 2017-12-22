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
 * テーブル名 t_user
 * 親クラス名 java.lang.Object
 */
public class t_userDTO extends java.lang.Object implements DTO {

	/**
	 * 内部で使用する {@link UpdatableDataObject}
	 */
	private final UpdatableDataObject data;

	private final Relationship relationship;

	/**
	 * 登録用コンストラクタです。
	 */
	public t_userDTO() {
		relationship = Relationship.getInstance(t_user.RESOURCE_LOCATOR);
		data = new UpdatableDataObject(relationship);
	}

	/**
	 * 参照、更新用コンストラクタです。
	 *
	 * @param data 値を持つ {@link UpdatableDataObject}
	 */
	public t_userDTO(UpdatableDataObject data) {
		relationship = Relationship.getInstance(t_user.RESOURCE_LOCATOR);
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
	 * 項目名 mail_address
	 * 型 java.lang.String
	 *
	 * @param value 更新値
	 */
	public void setmail_address(java.lang.String value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(
				relationship.getColumn("mail_address").getType());
		data.setValue("mail_address", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 mail_address
	 * 型 java.lang.String
	 *
	 * @return 返却値
	 */
	public java.lang.String getmail_address() {
		Binder binder = data.getBinder("mail_address");
		if (binder == null) return null;
		return (java.lang.String) binder.getValue();
	}

	/**
	 * setter
	 *
	 * 項目名 evacuated_mail_address
	 * 型 java.lang.String
	 *
	 * @param value 更新値
	 */
	public void setevacuated_mail_address(java.lang.String value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(
				relationship.getColumn("evacuated_mail_address").getType());
		data.setValue(
			"evacuated_mail_address",
			valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 evacuated_mail_address
	 * 型 java.lang.String
	 *
	 * @return 返却値
	 */
	public java.lang.String getevacuated_mail_address() {
		Binder binder = data.getBinder("evacuated_mail_address");
		if (binder == null) return null;
		return (java.lang.String) binder.getValue();
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
	 * 項目名 department
	 * 型 java.lang.String
	 *
	 * @param value 更新値
	 */
	public void setdepartment(java.lang.String value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(
				relationship.getColumn("department").getType());
		data.setValue("department", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 department
	 * 型 java.lang.String
	 *
	 * @return 返却値
	 */
	public java.lang.String getdepartment() {
		Binder binder = data.getBinder("department");
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
	 * 項目名 salt
	 * 型 java.lang.String
	 *
	 * @param value 更新値
	 */
	public void setsalt(java.lang.String value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("salt").getType());
		data.setValue("salt", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 salt
	 * 型 java.lang.String
	 *
	 * @return 返却値
	 */
	public java.lang.String getsalt() {
		Binder binder = data.getBinder("salt");
		if (binder == null) return null;
		return (java.lang.String) binder.getValue();
	}

	/**
	 * setter
	 *
	 * 項目名 password
	 * 型 java.lang.String
	 *
	 * @param value 更新値
	 */
	public void setpassword(java.lang.String value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("password").getType());
		data.setValue("password", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 password
	 * 型 java.lang.String
	 *
	 * @return 返却値
	 */
	public java.lang.String getpassword() {
		Binder binder = data.getBinder("password");
		if (binder == null) return null;
		return (java.lang.String) binder.getValue();
	}

	/**
	 * setter
	 *
	 * 項目名 expiration_date
	 * 型 java.sql.Timestamp
	 *
	 * @param value 更新値
	 */
	public void setexpiration_date(java.sql.Timestamp value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(
				relationship.getColumn("expiration_date").getType());
		data.setValue("expiration_date", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 expiration_date
	 * 型 java.sql.Timestamp
	 *
	 * @return 返却値
	 */
	public java.sql.Timestamp getexpiration_date() {
		Binder binder = data.getBinder("expiration_date");
		if (binder == null) return null;
		return (java.sql.Timestamp) binder.getValue();
	}

	/**
	 * setter
	 *
	 * 項目名 role
	 * 型 java.lang.Integer
	 *
	 * @param value 更新値
	 */
	public void setrole(java.lang.Integer value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("role").getType());
		data.setValue("role", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * 項目名 role
	 * 型 java.lang.Integer
	 *
	 * @return 返却値
	 */
	public java.lang.Integer getrole() {
		Binder binder = data.getBinder("role");
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

}
