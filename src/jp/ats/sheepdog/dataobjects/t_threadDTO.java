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
 * �����������ꂽ DTO �N���X�ł��B
 *
 * �p�b�P�[�W�� jp.ats.sheepdog.dataobjects
 * �e�[�u���� t_thread
 * �e�N���X�� java.lang.Object
 */
public class t_threadDTO extends java.lang.Object implements DTO {

	/**
	 * �����Ŏg�p���� {@link UpdatableDataObject}
	 */
	private final UpdatableDataObject data;

	private final Relationship relationship;

	/**
	 * �o�^�p�R���X�g���N�^�ł��B
	 */
	public t_threadDTO() {
		relationship = Relationship.getInstance(t_thread.RESOURCE_LOCATOR);
		data = new UpdatableDataObject(relationship);
	}

	/**
	 * �Q�ƁA�X�V�p�R���X�g���N�^�ł��B
	 *
	 * @param data �l������ {@link UpdatableDataObject}
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
	 * ���ږ� id
	 * �^ java.lang.Integer
	 *
	 * @param value �X�V�l
	 */
	public void setid(java.lang.Integer value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("id").getType());
		data.setValue("id", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * ���ږ� id
	 * �^ java.lang.Integer
	 *
	 * @return �ԋp�l
	 */
	public java.lang.Integer getid() {
		Binder binder = data.getBinder("id");
		if (binder == null) return null;
		return (java.lang.Integer) binder.getValue();
	}

	/**
	 * setter
	 *
	 * ���ږ� calendar
	 * �^ java.lang.String
	 *
	 * @param value �X�V�l
	 */
	public void setcalendar(java.lang.String value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("calendar").getType());
		data.setValue("calendar", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * ���ږ� calendar
	 * �^ java.lang.String
	 *
	 * @return �ԋp�l
	 */
	public java.lang.String getcalendar() {
		Binder binder = data.getBinder("calendar");
		if (binder == null) return null;
		return (java.lang.String) binder.getValue();
	}

	/**
	 * setter
	 *
	 * ���ږ� group_id
	 * �^ java.lang.Integer
	 *
	 * @param value �X�V�l
	 */
	public void setgroup_id(java.lang.Integer value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("group_id").getType());
		data.setValue("group_id", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * ���ږ� group_id
	 * �^ java.lang.Integer
	 *
	 * @return �ԋp�l
	 */
	public java.lang.Integer getgroup_id() {
		Binder binder = data.getBinder("group_id");
		if (binder == null) return null;
		return (java.lang.Integer) binder.getValue();
	}

	/**
	 * setter
	 *
	 * ���ږ� event_flag
	 * �^ java.lang.Integer
	 *
	 * @param value �X�V�l
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
	 * ���ږ� event_flag
	 * �^ java.lang.Integer
	 *
	 * @return �ԋp�l
	 */
	public java.lang.Integer getevent_flag() {
		Binder binder = data.getBinder("event_flag");
		if (binder == null) return null;
		return (java.lang.Integer) binder.getValue();
	}

	/**
	 * setter
	 *
	 * ���ږ� last_title
	 * �^ java.lang.String
	 *
	 * @param value �X�V�l
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
	 * ���ږ� last_title
	 * �^ java.lang.String
	 *
	 * @return �ԋp�l
	 */
	public java.lang.String getlast_title() {
		Binder binder = data.getBinder("last_title");
		if (binder == null) return null;
		return (java.lang.String) binder.getValue();
	}

	/**
	 * setter
	 *
	 * ���ږ� owner
	 * �^ java.lang.Integer
	 *
	 * @param value �X�V�l
	 */
	public void setowner(java.lang.Integer value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("owner").getType());
		data.setValue("owner", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * ���ږ� owner
	 * �^ java.lang.Integer
	 *
	 * @return �ԋp�l
	 */
	public java.lang.Integer getowner() {
		Binder binder = data.getBinder("owner");
		if (binder == null) return null;
		return (java.lang.Integer) binder.getValue();
	}

	/**
	 * setter
	 *
	 * ���ږ� applying_level
	 * �^ java.lang.Integer
	 *
	 * @param value �X�V�l
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
	 * ���ږ� applying_level
	 * �^ java.lang.Integer
	 *
	 * @return �ԋp�l
	 */
	public java.lang.Integer getapplying_level() {
		Binder binder = data.getBinder("applying_level");
		if (binder == null) return null;
		return (java.lang.Integer) binder.getValue();
	}

	/**
	 * setter
	 *
	 * ���ږ� private_flag
	 * �^ java.lang.Integer
	 *
	 * @param value �X�V�l
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
	 * ���ږ� private_flag
	 * �^ java.lang.Integer
	 *
	 * @return �ԋp�l
	 */
	public java.lang.Integer getprivate_flag() {
		Binder binder = data.getBinder("private_flag");
		if (binder == null) return null;
		return (java.lang.Integer) binder.getValue();
	}

	/**
	 * setter
	 *
	 * ���ږ� delete_flag
	 * �^ java.lang.Integer
	 *
	 * @param value �X�V�l
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
	 * ���ږ� delete_flag
	 * �^ java.lang.Integer
	 *
	 * @return �ԋp�l
	 */
	public java.lang.Integer getdelete_flag() {
		Binder binder = data.getBinder("delete_flag");
		if (binder == null) return null;
		return (java.lang.Integer) binder.getValue();
	}

	/**
	 * setter
	 *
	 * ���ږ� create_time
	 * �^ java.sql.Timestamp
	 *
	 * @param value �X�V�l
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
	 * ���ږ� create_time
	 * �^ java.sql.Timestamp
	 *
	 * @return �ԋp�l
	 */
	public java.sql.Timestamp getcreate_time() {
		Binder binder = data.getBinder("create_time");
		if (binder == null) return null;
		return (java.sql.Timestamp) binder.getValue();
	}

	/**
	 * setter
	 *
	 * ���ږ� update_time
	 * �^ java.sql.Timestamp
	 *
	 * @param value �X�V�l
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
	 * ���ږ� update_time
	 * �^ java.sql.Timestamp
	 *
	 * @return �ԋp�l
	 */
	public java.sql.Timestamp getupdate_time() {
		Binder binder = data.getBinder("update_time");
		if (binder == null) return null;
		return (java.sql.Timestamp) binder.getValue();
	}

	/**
	 * ���̃��R�[�h���Q�Ƃ��Ă��郌�R�[�h�� DTO ��Ԃ��܂��B
	 *
	 * �Q�Ɛ�e�[�u���� t_group
	 * �O���L�[�� t_thread_group_id_fkey
	 * ���ږ� group_id
	 *
	 * @return �Q�Ƃ��Ă��郌�R�[�h�� DTO
	 */
	public t_groupDTO gett_groupByt_thread_group_id_fkey() {
		return new t_groupDTO(
			data.getDataObject(t_thread.t_group_BY_t_thread_group_id_fkey));
	}

	/**
	 * ���̃��R�[�h���Q�Ƃ��Ă��郌�R�[�h�� DTO ��Ԃ��܂��B
	 *
	 * �Q�Ɛ�e�[�u���� t_user
	 * �O���L�[�� t_thread_owner_fkey
	 * ���ږ� owner
	 *
	 * @return �Q�Ƃ��Ă��郌�R�[�h�� DTO
	 */
	public t_userDTO gett_userByt_thread_owner_fkey() {
		return new t_userDTO(
			data.getDataObject(t_thread.t_user_BY_t_thread_owner_fkey));
	}

}