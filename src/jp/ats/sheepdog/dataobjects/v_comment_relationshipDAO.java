package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.extension.DTOIterator;
import jp.ats.liverwort.jdbc.ResourceLocator;
import jp.ats.liverwort.ormapping.DataAccessHelper.RowLockOption;
import jp.ats.liverwort.ormapping.DataObjectIterator;
import jp.ats.liverwort.ormapping.UpdatableDataObject;
import jp.ats.liverwort.selector.Optimizer;
import jp.ats.liverwort.selector.SimpleOptimizer;
import jp.ats.liverwort.sql.Condition;
import jp.ats.liverwort.sql.OrderByClause;
import jp.ats.liverwort.sql.SQLAdjuster;

/**
 * �����������ꂽ DAO �̎����N���X�ł��B
 *
 * �p�b�P�[�W�� jp.ats.sheepdog.dataobjects
 * �e�[�u���� v_comment_relationship
 */
public class v_comment_relationshipDAO
	extends GenericDAO<v_comment_relationshipDTO> {

	/**
	 * �p�����[�^�̏����Ƀ}�b�`���郌�R�[�h���������A {@link v_comment_relationshipIterator} �Ƃ��ĕԂ��܂��B
	 * <br>
	 * {@link Optimizer} �ɂ� {@link SimpleOptimizer} ���g�p����܂��B
	 *
	 * @param condition WHERE ��ƂȂ����
	 * @param order  ORDER ��
	 * @param adjuster �������ʂ𒲐����� {@link SQLAdjuster}
	 * @param rowLockOption �s���b�N�I�v�V����
	 * @return {@link DTOIterator}
	 */
	public v_comment_relationshipIterator select(
		Condition condition,
		OrderByClause order,
		SQLAdjuster adjuster,
		RowLockOption rowLockOption) {
		return select(
			new SimpleOptimizer(getResourceLocator()),
			condition,
			order,
			adjuster,
			rowLockOption);
	}

	/**
	 * �p�����[�^�̏����Ƀ}�b�`���郌�R�[�h���������A {@link v_comment_relationshipIterator} �Ƃ��ĕԂ��܂��B
	 * <br>
	 * {@link Optimizer} �ɂ� {@link SimpleOptimizer} ���g�p����܂��B
	 * <br>
	 * {@link RowLockOption} �ɂ� {@link RowLockOption#NONE} ���g�p����܂��B
	 *
	 * @param condition WHERE ��ƂȂ����
	 * @param order  ORDER ��
	 * @return {@link DTOIterator}
	 */
	public v_comment_relationshipIterator select(
		Condition condition,
		OrderByClause order) {
		return select(condition, order, null, RowLockOption.NONE);
	}

	/**
	 * �p�����[�^�̏����Ƀ}�b�`���郌�R�[�h���������A {@link v_comment_relationshipIterator} �Ƃ��ĕԂ��܂��B
	 *
	 * @param optimizer SELECT ��𐧌䂷�� {@link Optimizer}
	 * @param condition WHERE ��ƂȂ����
	 * @param order  ORDER ��
	 * @param adjuster �������ʂ𒲐����� {@link SQLAdjuster}
	 * @param rowLockOption �s���b�N�I�v�V����
	 * @return {@link DTOIterator}
	 */
	public v_comment_relationshipIterator select(
		Optimizer optimizer,
		Condition condition,
		OrderByClause order,
		SQLAdjuster adjuster,
		RowLockOption rowLockOption) {
		return new v_comment_relationshipIterator(
			helper.getUpdatableDataObjects(
				optimizer,
				condition,
				order,
				adjuster,
				rowLockOption));
	}

	/**
	 * �p�����[�^�̏����Ƀ}�b�`���郌�R�[�h���������A {@link v_comment_relationshipIterator} �Ƃ��ĕԂ��܂��B
	 * <br>
	 * {@link RowLockOption} �ɂ� {@link RowLockOption#NONE} ���g�p����܂��B
	 *
	 * @param optimizer SELECT ��𐧌䂷�� {@link Optimizer}
	 * @param condition WHERE ��ƂȂ����
	 * @param order  ORDER ��
	 * @return {@link DTOIterator}
	 */
	public v_comment_relationshipIterator select(
		Optimizer optimizer,
		Condition condition,
		OrderByClause order) {
		return select(optimizer, condition, order, null, RowLockOption.NONE);
	}

	/**
	 * v_comment_relationshipDTO �𐶐����郁�\�b�h�ł��B
	 */
	@Override
	protected v_comment_relationshipDTO createDTO(UpdatableDataObject data) {
		return new v_comment_relationshipDTO(data);
	}

	@Override
	protected ResourceLocator getResourceLocator() {
		return v_comment_relationship.RESOURCE_LOCATOR;
	}

	/**
	 * {@link v_comment_relationshipDAO} ���g�p���� Iterator �N���X�ł��B
	 */
	public class v_comment_relationshipIterator
		extends DTOIterator<v_comment_relationshipDTO> {

		/**
		 * �B��̃R���X�g���N�^�ł��B
		 *
		 * @param iterator 
		 */
		private v_comment_relationshipIterator(
			DataObjectIterator<UpdatableDataObject> iterator) {
			super(iterator);
		}

		@Override
		public v_comment_relationshipDTO next() {
			return createDTO(nextDataObject());
		}
	}
}
