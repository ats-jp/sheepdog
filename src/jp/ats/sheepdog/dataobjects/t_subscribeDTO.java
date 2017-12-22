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
 * �e�[�u���� t_subscribe
 * �e�N���X�� java.lang.Object
 */
public class t_subscribeDTO extends java.lang.Object implements DTO {

	/**
	 * �����Ŏg�p���� {@link UpdatableDataObject}
	 */
	private final UpdatableDataObject data;

	private final Relationship relationship;

	/**
	 * �o�^�p�R���X�g���N�^�ł��B
	 */
	public t_subscribeDTO() {
		relationship = Relationship.getInstance(t_subscribe.RESOURCE_LOCATOR);
		data = new UpdatableDataObject(relationship);
	}

	/**
	 * �Q�ƁA�X�V�p�R���X�g���N�^�ł��B
	 *
	 * @param data �l������ {@link UpdatableDataObject}
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
	 * ���ږ� id
	 * �^ java.lang.Long
	 *
	 * @param value �X�V�l
	 */
	public void setid(java.lang.Long value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("id").getType());
		data.setValue("id", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * ���ږ� id
	 * �^ java.lang.Long
	 *
	 * @return �ԋp�l
	 */
	public java.lang.Long getid() {
		Binder binder = data.getBinder("id");
		if (binder == null) return null;
		return (java.lang.Long) binder.getValue();
	}

	/**
	 * setter
	 *
	 * ���ږ� group_for_thread
	 * �^ java.lang.Integer
	 *
	 * @param value �X�V�l
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
	 * ���ږ� group_for_thread
	 * �^ java.lang.Integer
	 *
	 * @return �ԋp�l
	 */
	public java.lang.Integer getgroup_for_thread() {
		Binder binder = data.getBinder("group_for_thread");
		if (binder == null) return null;
		return (java.lang.Integer) binder.getValue();
	}

	/**
	 * setter
	 *
	 * ���ږ� thread_for_comment
	 * �^ java.lang.Integer
	 *
	 * @param value �X�V�l
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
	 * ���ږ� thread_for_comment
	 * �^ java.lang.Integer
	 *
	 * @return �ԋp�l
	 */
	public java.lang.Integer getthread_for_comment() {
		Binder binder = data.getBinder("thread_for_comment");
		if (binder == null) return null;
		return (java.lang.Integer) binder.getValue();
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
	 * ���̃��R�[�h���Q�Ƃ��Ă��郌�R�[�h�� DTO ��Ԃ��܂��B
	 *
	 * �Q�Ɛ�e�[�u���� t_group
	 * �O���L�[�� t_subscribe_group_for_thread_fkey
	 * ���ږ� group_for_thread
	 *
	 * @return �Q�Ƃ��Ă��郌�R�[�h�� DTO
	 */
	public t_groupDTO gett_groupByt_subscribe_group_for_thread_fkey() {
		return new t_groupDTO(
			data.getDataObject(t_subscribe.t_group_BY_t_subscribe_group_for_thread_fkey));
	}

	/**
	 * ���̃��R�[�h���Q�Ƃ��Ă��郌�R�[�h�� DTO ��Ԃ��܂��B
	 *
	 * �Q�Ɛ�e�[�u���� t_thread
	 * �O���L�[�� t_subscribe_thread_for_comment_fkey
	 * ���ږ� thread_for_comment
	 *
	 * @return �Q�Ƃ��Ă��郌�R�[�h�� DTO
	 */
	public t_threadDTO gett_threadByt_subscribe_thread_for_comment_fkey() {
		return new t_threadDTO(
			data.getDataObject(t_subscribe.t_thread_BY_t_subscribe_thread_for_comment_fkey));
	}

	/**
	 * ���̃��R�[�h���Q�Ƃ��Ă��郌�R�[�h�� DTO ��Ԃ��܂��B
	 *
	 * �Q�Ɛ�e�[�u���� t_user
	 * �O���L�[�� t_subscribe_owner_fkey
	 * ���ږ� owner
	 *
	 * @return �Q�Ƃ��Ă��郌�R�[�h�� DTO
	 */
	public t_userDTO gett_userByt_subscribe_owner_fkey() {
		return new t_userDTO(
			data.getDataObject(t_subscribe.t_user_BY_t_subscribe_owner_fkey));
	}

}
