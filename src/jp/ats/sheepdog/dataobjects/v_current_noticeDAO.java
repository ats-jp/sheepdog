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
 * �e�[�u���� v_current_notice
 */
public class v_current_noticeDAO extends GenericDAO<v_current_noticeDTO> {

	/**
	 * �p�����[�^�̏����Ƀ}�b�`���郌�R�[�h���������A {@link v_current_noticeIterator} �Ƃ��ĕԂ��܂��B
	 * <br>
	 * {@link Optimizer} �ɂ� {@link SimpleOptimizer} ���g�p����܂��B
	 *
	 * @param condition WHERE ��ƂȂ����
	 * @param order  ORDER ��
	 * @param adjuster �������ʂ𒲐����� {@link SQLAdjuster}
	 * @param rowLockOption �s���b�N�I�v�V����
	 * @return {@link DTOIterator}
	 */
	public v_current_noticeIterator select(
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
	 * �p�����[�^�̏����Ƀ}�b�`���郌�R�[�h���������A {@link v_current_noticeIterator} �Ƃ��ĕԂ��܂��B
	 * <br>
	 * {@link Optimizer} �ɂ� {@link SimpleOptimizer} ���g�p����܂��B
	 * <br>
	 * {@link RowLockOption} �ɂ� {@link RowLockOption#NONE} ���g�p����܂��B
	 *
	 * @param condition WHERE ��ƂȂ����
	 * @param order  ORDER ��
	 * @return {@link DTOIterator}
	 */
	public v_current_noticeIterator select(
		Condition condition,
		OrderByClause order) {
		return select(condition, order, null, RowLockOption.NONE);
	}

	/**
	 * �p�����[�^�̏����Ƀ}�b�`���郌�R�[�h���������A {@link v_current_noticeIterator} �Ƃ��ĕԂ��܂��B
	 *
	 * @param optimizer SELECT ��𐧌䂷�� {@link Optimizer}
	 * @param condition WHERE ��ƂȂ����
	 * @param order  ORDER ��
	 * @param adjuster �������ʂ𒲐����� {@link SQLAdjuster}
	 * @param rowLockOption �s���b�N�I�v�V����
	 * @return {@link DTOIterator}
	 */
	public v_current_noticeIterator select(
		Optimizer optimizer,
		Condition condition,
		OrderByClause order,
		SQLAdjuster adjuster,
		RowLockOption rowLockOption) {
		return new v_current_noticeIterator(helper.getUpdatableDataObjects(
			optimizer,
			condition,
			order,
			adjuster,
			rowLockOption));
	}

	/**
	 * �p�����[�^�̏����Ƀ}�b�`���郌�R�[�h���������A {@link v_current_noticeIterator} �Ƃ��ĕԂ��܂��B
	 * <br>
	 * {@link RowLockOption} �ɂ� {@link RowLockOption#NONE} ���g�p����܂��B
	 *
	 * @param optimizer SELECT ��𐧌䂷�� {@link Optimizer}
	 * @param condition WHERE ��ƂȂ����
	 * @param order  ORDER ��
	 * @return {@link DTOIterator}
	 */
	public v_current_noticeIterator select(
		Optimizer optimizer,
		Condition condition,
		OrderByClause order) {
		return select(optimizer, condition, order, null, RowLockOption.NONE);
	}

	/**
	 * v_current_noticeDTO �𐶐����郁�\�b�h�ł��B
	 */
	@Override
	protected v_current_noticeDTO createDTO(UpdatableDataObject data) {
		return new v_current_noticeDTO(data);
	}

	@Override
	protected ResourceLocator getResourceLocator() {
		return v_current_notice.RESOURCE_LOCATOR;
	}

	/**
	 * {@link v_current_noticeDAO} ���g�p���� Iterator �N���X�ł��B
	 */
	public class v_current_noticeIterator
		extends DTOIterator<v_current_noticeDTO> {

		/**
		 * �B��̃R���X�g���N�^�ł��B
		 *
		 * @param iterator 
		 */
		private v_current_noticeIterator(
			DataObjectIterator<UpdatableDataObject> iterator) {
			super(iterator);
		}

		@Override
		public v_current_noticeDTO next() {
			return createDTO(nextDataObject());
		}
	}
}
