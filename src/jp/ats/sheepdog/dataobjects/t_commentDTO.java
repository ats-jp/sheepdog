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
 * �e�[�u���� t_comment
 * �e�N���X�� java.lang.Object
 */
public class t_commentDTO extends java.lang.Object implements DTO {

	/**
	 * �����Ŏg�p���� {@link UpdatableDataObject}
	 */
	private final UpdatableDataObject data;

	private final Relationship relationship;

	/**
	 * �o�^�p�R���X�g���N�^�ł��B
	 */
	public t_commentDTO() {
		relationship = Relationship.getInstance(t_comment.RESOURCE_LOCATOR);
		data = new UpdatableDataObject(relationship);
	}

	/**
	 * �Q�ƁA�X�V�p�R���X�g���N�^�ł��B
	 *
	 * @param data �l������ {@link UpdatableDataObject}
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
	 * ���ږ� thread_id
	 * �^ java.lang.Integer
	 *
	 * @param value �X�V�l
	 */
	public void setthread_id(java.lang.Integer value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("thread_id").getType());
		data.setValue("thread_id", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * ���ږ� thread_id
	 * �^ java.lang.Integer
	 *
	 * @return �ԋp�l
	 */
	public java.lang.Integer getthread_id() {
		Binder binder = data.getBinder("thread_id");
		if (binder == null) return null;
		return (java.lang.Integer) binder.getValue();
	}

	/**
	 * setter
	 *
	 * ���ږ� comment_number
	 * �^ java.lang.Integer
	 *
	 * @param value �X�V�l
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
	 * ���ږ� comment_number
	 * �^ java.lang.Integer
	 *
	 * @return �ԋp�l
	 */
	public java.lang.Integer getcomment_number() {
		Binder binder = data.getBinder("comment_number");
		if (binder == null) return null;
		return (java.lang.Integer) binder.getValue();
	}

	/**
	 * setter
	 *
	 * ���ږ� parent_id
	 * �^ java.lang.Integer
	 *
	 * @param value �X�V�l
	 */
	public void setparent_id(java.lang.Integer value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("parent_id").getType());
		data.setValue("parent_id", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * ���ږ� parent_id
	 * �^ java.lang.Integer
	 *
	 * @return �ԋp�l
	 */
	public java.lang.Integer getparent_id() {
		Binder binder = data.getBinder("parent_id");
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
	 * ���ږ� body
	 * �^ java.lang.String
	 *
	 * @param value �X�V�l
	 */
	public void setbody(java.lang.String value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("body").getType());
		data.setValue("body", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * ���ږ� body
	 * �^ java.lang.String
	 *
	 * @return �ԋp�l
	 */
	public java.lang.String getbody() {
		Binder binder = data.getBinder("body");
		if (binder == null) return null;
		return (java.lang.String) binder.getValue();
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
	 * �Q�Ɛ�e�[�u���� v_comment_relationship
	 * �O���L�[�� t_comment_id_fkey
	 * ���ږ� id
	 *
	 * @return �Q�Ƃ��Ă��郌�R�[�h�� DTO
	 */
	public v_comment_relationshipDTO getv_comment_relationshipByt_comment_id_fkey() {
		return new v_comment_relationshipDTO(
			data.getDataObject(t_comment.v_comment_relationship_BY_t_comment_id_fkey));
	}

	/**
	 * ���̃��R�[�h���Q�Ƃ��Ă��郌�R�[�h�� DTO ��Ԃ��܂��B
	 *
	 * �Q�Ɛ�e�[�u���� t_comment
	 * �O���L�[�� t_comment_parent_id_fkey
	 * ���ږ� parent_id
	 *
	 * @return �Q�Ƃ��Ă��郌�R�[�h�� DTO
	 */
	public t_commentDTO gett_commentByt_comment_parent_id_fkey() {
		return new t_commentDTO(
			data.getDataObject(t_comment.t_comment_BY_t_comment_parent_id_fkey));
	}

	/**
	 * ���̃��R�[�h���Q�Ƃ��Ă��郌�R�[�h�� DTO ��Ԃ��܂��B
	 *
	 * �Q�Ɛ�e�[�u���� t_thread
	 * �O���L�[�� t_comment_thread_id_fkey
	 * ���ږ� thread_id
	 *
	 * @return �Q�Ƃ��Ă��郌�R�[�h�� DTO
	 */
	public t_threadDTO gett_threadByt_comment_thread_id_fkey() {
		return new t_threadDTO(
			data.getDataObject(t_comment.t_thread_BY_t_comment_thread_id_fkey));
	}

	/**
	 * ���̃��R�[�h���Q�Ƃ��Ă��郌�R�[�h�� DTO ��Ԃ��܂��B
	 *
	 * �Q�Ɛ�e�[�u���� t_user
	 * �O���L�[�� t_comment_owner_fkey
	 * ���ږ� owner
	 *
	 * @return �Q�Ƃ��Ă��郌�R�[�h�� DTO
	 */
	public t_userDTO gett_userByt_comment_owner_fkey() {
		return new t_userDTO(
			data.getDataObject(t_comment.t_user_BY_t_comment_owner_fkey));
	}

}
