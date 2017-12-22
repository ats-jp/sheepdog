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
 * �e�[�u���� t_content_attach
 * �e�N���X�� java.lang.Object
 */
public class t_content_attachDTO extends java.lang.Object implements DTO {

	/**
	 * �����Ŏg�p���� {@link UpdatableDataObject}
	 */
	private final UpdatableDataObject data;

	private final Relationship relationship;

	/**
	 * �o�^�p�R���X�g���N�^�ł��B
	 */
	public t_content_attachDTO() {
		relationship = Relationship.getInstance(t_content_attach.RESOURCE_LOCATOR);
		data = new UpdatableDataObject(relationship);
	}

	/**
	 * �Q�ƁA�X�V�p�R���X�g���N�^�ł��B
	 *
	 * @param data �l������ {@link UpdatableDataObject}
	 */
	public t_content_attachDTO(UpdatableDataObject data) {
		relationship = Relationship.getInstance(t_content_attach.RESOURCE_LOCATOR);
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
	 * ���ږ� attach_id
	 * �^ java.lang.Integer
	 *
	 * @param value �X�V�l
	 */
	public void setattach_id(java.lang.Integer value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("attach_id").getType());
		data.setValue("attach_id", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * ���ږ� attach_id
	 * �^ java.lang.Integer
	 *
	 * @return �ԋp�l
	 */
	public java.lang.Integer getattach_id() {
		Binder binder = data.getBinder("attach_id");
		if (binder == null) return null;
		return (java.lang.Integer) binder.getValue();
	}

	/**
	 * setter
	 *
	 * ���ږ� content_id
	 * �^ java.lang.Integer
	 *
	 * @param value �X�V�l
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
	 * ���ږ� content_id
	 * �^ java.lang.Integer
	 *
	 * @return �ԋp�l
	 */
	public java.lang.Integer getcontent_id() {
		Binder binder = data.getBinder("content_id");
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
	 * �Q�Ɛ�e�[�u���� t_attach
	 * �O���L�[�� t_content_attach_attach_id_fkey
	 * ���ږ� attach_id
	 *
	 * @return �Q�Ƃ��Ă��郌�R�[�h�� DTO
	 */
	public t_attachDTO gett_attachByt_content_attach_attach_id_fkey() {
		return new t_attachDTO(
			data.getDataObject(t_content_attach.t_attach_BY_t_content_attach_attach_id_fkey));
	}

	/**
	 * ���̃��R�[�h���Q�Ƃ��Ă��郌�R�[�h�� DTO ��Ԃ��܂��B
	 *
	 * �Q�Ɛ�e�[�u���� t_content
	 * �O���L�[�� t_content_attach_content_id_fkey
	 * ���ږ� content_id
	 *
	 * @return �Q�Ƃ��Ă��郌�R�[�h�� DTO
	 */
	public t_contentDTO gett_contentByt_content_attach_content_id_fkey() {
		return new t_contentDTO(
			data.getDataObject(t_content_attach.t_content_BY_t_content_attach_content_id_fkey));
	}

}
