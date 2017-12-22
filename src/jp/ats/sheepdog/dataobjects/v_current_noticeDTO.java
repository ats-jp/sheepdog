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
 * �e�[�u���� v_current_notice
 * �e�N���X�� java.lang.Object
 */
public class v_current_noticeDTO extends java.lang.Object implements DTO {

	/**
	 * �����Ŏg�p���� {@link UpdatableDataObject}
	 */
	private final UpdatableDataObject data;

	private final Relationship relationship;

	/**
	 * �o�^�p�R���X�g���N�^�ł��B
	 */
	public v_current_noticeDTO() {
		relationship = Relationship.getInstance(v_current_notice.RESOURCE_LOCATOR);
		data = new UpdatableDataObject(relationship);
	}

	/**
	 * �Q�ƁA�X�V�p�R���X�g���N�^�ł��B
	 *
	 * @param data �l������ {@link UpdatableDataObject}
	 */
	public v_current_noticeDTO(UpdatableDataObject data) {
		relationship = Relationship.getInstance(v_current_notice.RESOURCE_LOCATOR);
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
	 * ���ږ� max_id
	 * �^ java.lang.Long
	 *
	 * @param value �X�V�l
	 */
	public void setmax_id(java.lang.Long value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("max_id").getType());
		data.setValue("max_id", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * ���ږ� max_id
	 * �^ java.lang.Long
	 *
	 * @return �ԋp�l
	 */
	public java.lang.Long getmax_id() {
		Binder binder = data.getBinder("max_id");
		if (binder == null) return null;
		return (java.lang.Long) binder.getValue();
	}

	/**
	 * setter
	 *
	 * ���ږ� last_id
	 * �^ java.lang.Long
	 *
	 * @param value �X�V�l
	 */
	public void setlast_id(java.lang.Long value) {
		ValueExtractor valueExtractor = Selector.getValueExtractors()
			.selectValueExtractor(relationship.getColumn("last_id").getType());
		data.setValue("last_id", valueExtractor.extractAsBinder(value));
	}

	/**
	 * getter
	 *
	 * ���ږ� last_id
	 * �^ java.lang.Long
	 *
	 * @return �ԋp�l
	 */
	public java.lang.Long getlast_id() {
		Binder binder = data.getBinder("last_id");
		if (binder == null) return null;
		return (java.lang.Long) binder.getValue();
	}

}
