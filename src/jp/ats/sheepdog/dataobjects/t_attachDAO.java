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
 * �e�[�u���� t_attach
 */
public class t_attachDAO extends GenericDAO<t_attachDTO> {

	/**
	 * �p�����[�^�̏����Ƀ}�b�`���郌�R�[�h���������A {@link t_attachIterator} �Ƃ��ĕԂ��܂��B
	 * <br>
	 * {@link Optimizer} �ɂ� {@link SimpleOptimizer} ���g�p����܂��B
	 *
	 * @param condition WHERE ��ƂȂ����
	 * @param order  ORDER ��
	 * @param adjuster �������ʂ𒲐����� {@link SQLAdjuster}
	 * @param rowLockOption �s���b�N�I�v�V����
	 * @return {@link DTOIterator}
	 */
	public t_attachIterator select(
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
	 * �p�����[�^�̏����Ƀ}�b�`���郌�R�[�h���������A {@link t_attachIterator} �Ƃ��ĕԂ��܂��B
	 * <br>
	 * {@link Optimizer} �ɂ� {@link SimpleOptimizer} ���g�p����܂��B
	 * <br>
	 * {@link RowLockOption} �ɂ� {@link RowLockOption#NONE} ���g�p����܂��B
	 *
	 * @param condition WHERE ��ƂȂ����
	 * @param order  ORDER ��
	 * @return {@link DTOIterator}
	 */
	public t_attachIterator select(Condition condition, OrderByClause order) {
		return select(condition, order, null, RowLockOption.NONE);
	}

	/**
	 * �p�����[�^�̏����Ƀ}�b�`���郌�R�[�h���������A {@link t_attachIterator} �Ƃ��ĕԂ��܂��B
	 *
	 * @param optimizer SELECT ��𐧌䂷�� {@link Optimizer}
	 * @param condition WHERE ��ƂȂ����
	 * @param order  ORDER ��
	 * @param adjuster �������ʂ𒲐����� {@link SQLAdjuster}
	 * @param rowLockOption �s���b�N�I�v�V����
	 * @return {@link DTOIterator}
	 */
	public t_attachIterator select(
		Optimizer optimizer,
		Condition condition,
		OrderByClause order,
		SQLAdjuster adjuster,
		RowLockOption rowLockOption) {
		return new t_attachIterator(helper.getUpdatableDataObjects(
			optimizer,
			condition,
			order,
			adjuster,
			rowLockOption));
	}

	/**
	 * �p�����[�^�̏����Ƀ}�b�`���郌�R�[�h���������A {@link t_attachIterator} �Ƃ��ĕԂ��܂��B
	 * <br>
	 * {@link RowLockOption} �ɂ� {@link RowLockOption#NONE} ���g�p����܂��B
	 *
	 * @param optimizer SELECT ��𐧌䂷�� {@link Optimizer}
	 * @param condition WHERE ��ƂȂ����
	 * @param order  ORDER ��
	 * @return {@link DTOIterator}
	 */
	public t_attachIterator select(
		Optimizer optimizer,
		Condition condition,
		OrderByClause order) {
		return select(optimizer, condition, order, null, RowLockOption.NONE);
	}

	/**
	 * t_attachDTO �𐶐����郁�\�b�h�ł��B
	 */
	@Override
	protected t_attachDTO createDTO(UpdatableDataObject data) {
		return new t_attachDTO(data);
	}

	@Override
	protected ResourceLocator getResourceLocator() {
		return t_attach.RESOURCE_LOCATOR;
	}

	/**
	 * {@link t_attachDAO} ���g�p���� Iterator �N���X�ł��B
	 */
	public class t_attachIterator extends DTOIterator<t_attachDTO> {

		/**
		 * �B��̃R���X�g���N�^�ł��B
		 *
		 * @param iterator 
		 */
		private t_attachIterator(
			DataObjectIterator<UpdatableDataObject> iterator) {
			super(iterator);
		}

		@Override
		public t_attachDTO next() {
			return createDTO(nextDataObject());
		}
	}
}