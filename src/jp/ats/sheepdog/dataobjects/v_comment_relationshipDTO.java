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
 * �e�[�u���� v_comment_relationship
 * �e�N���X�� java.lang.Object
 */
public class v_comment_relationshipDTO extends java.lang.Object implements DTO {

	/**
	 * �����Ŏg�p���� {@link UpdatableDataObject}
	 */
	private final UpdatableDataObject data;

	private final Relationship relationship;

	/**
	 * �o�^�p�R���X�g���N�^�ł��B
	 */
	public v_comment_relationshipDTO() {
		relationship = Relationship.getInstance(v_comment_relationship.RESOURCE_LOCATOR);
		data = new UpdatableDataObject(relationship);
	}

	/**
	 * �Q�ƁA�X�V�p�R���X�g���N�^�ł��B
	 *
	 * @param data �l������ {@link UpdatableDataObject}
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
	 * ���ږ� comment_id
	 * �^ java.lang.Integer
	 *
	 * @param value �X�V�l
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
	 * ���ږ� comment_id
	 * �^ java.lang.Integer
	 *
	 * @return �ԋp�l
	 */
	public java.lang.Integer getcomment_id() {
		Binder binder = data.getBinder("comment_id");
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
	 * ���ږ� parent_number
	 * �^ java.lang.Integer
	 *
	 * @param value �X�V�l
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
	 * ���ږ� parent_number
	 * �^ java.lang.Integer
	 *
	 * @return �ԋp�l
	 */
	public java.lang.Integer getparent_number() {
		Binder binder = data.getBinder("parent_number");
		if (binder == null) return null;
		return (java.lang.Integer) binder.getValue();
	}

	/**
	 * setter
	 *
	 * ���ږ� attach_count
	 * �^ java.lang.Long
	 *
	 * @param value �X�V�l
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
	 * ���ږ� attach_count
	 * �^ java.lang.Long
	 *
	 * @return �ԋp�l
	 */
	public java.lang.Long getattach_count() {
		Binder binder = data.getBinder("attach_count");
		if (binder == null) return null;
		return (java.lang.Long) binder.getValue();
	}

	/**
	 * ���̃��R�[�h���Q�Ƃ��Ă��郌�R�[�h�� DTO ��Ԃ��܂��B
	 *
	 * �Q�Ɛ�e�[�u���� t_thread
	 * �O���L�[�� v_comment_relationship_thread_id_fkey
	 * ���ږ� thread_id
	 *
	 * @return �Q�Ƃ��Ă��郌�R�[�h�� DTO
	 */
	public t_threadDTO gett_threadByv_comment_relationship_thread_id_fkey() {
		return new t_threadDTO(
			data.getDataObject(v_comment_relationship.t_thread_BY_v_comment_relationship_thread_id_fkey));
	}

}
