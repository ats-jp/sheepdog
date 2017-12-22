package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.extension.DTO;
import jp.ats.liverwort.extension.DTOIterator;
import jp.ats.liverwort.jdbc.BatchStatement;
import jp.ats.liverwort.jdbc.ResourceLocator;
import jp.ats.liverwort.ormapping.DataAccessHelper;
import jp.ats.liverwort.ormapping.DataAccessHelper.RowLockOption;
import jp.ats.liverwort.ormapping.DataObjectNotFoundException;
import jp.ats.liverwort.ormapping.PrimaryKey;
import jp.ats.liverwort.ormapping.SequenceGenerator;
import jp.ats.liverwort.ormapping.UpdatableDataObject;
import jp.ats.liverwort.selector.Optimizer;
import jp.ats.liverwort.selector.SimpleOptimizer;
import jp.ats.liverwort.sql.Bindable;
import jp.ats.liverwort.sql.Condition;
import jp.ats.liverwort.sql.SQLAdjuster;
import jp.ats.liverwort.sql.Updatable;

/**
 * �����������ꂽ��� DAO �N���X�ł��B
 *
 * �p�b�P�[�W�� jp.ats.sheepdog.dataobjects
 * �e�N���X�� java.lang.Object
 *
 * @param <T> DTO�̌^
 */
public abstract class GenericDAO<T extends DTO> extends java.lang.Object {

	/**
	 * ��̌�������
	 */
	private static final DTOIterator<DTO> emptyIterator = new DTOIterator<DTO>(
		DataAccessHelper.EMPTY_UPDATABLE_DATA_OBJECT_ITERATOR) {

		@Override
		public DTO next() {
			throw new UnsupportedOperationException();
		}
	};

	/**
	 * ���� DAO �Ŏg�p���� {@link DataAccessHelper}
	 */
	protected final DataAccessHelper helper = new DataAccessHelper();

	/**
	 * ��� DTOIterator ��Ԃ��܂��B
	 *
	 * @param <T> {@link DTOIterator} �̗v�f�^
	 * @return {@link DTOIterator}
	 */
	@SuppressWarnings("unchecked")
	public static <T extends DTO> DTOIterator<T> getEmptyDTOIterator() {
		return (DTOIterator<T>) emptyIterator;
	}

	/**
	 * �p�����[�^�̎�L�[�̒l������ {@link DTO} ���������Ԃ��܂��B
	 * <br>
	 * {@link Optimizer} �ɂ� {@link SimpleOptimizer} ���g�p����܂��B
	 *
	 * @param rowLockOption �s���b�N�I�v�V����
	 * @param primaryKeyMembers ��L�[���\�����镶����
	 * @return {@link DTO} ���݂��Ȃ���� null
	 */
	public T select(RowLockOption rowLockOption, String... primaryKeyMembers) {
		return select(
			new SimpleOptimizer(getResourceLocator()),
			rowLockOption,
			primaryKeyMembers);
	}

	/**
	 * �p�����[�^�̎�L�[�̒l������ {@link DTO} ���������Ԃ��܂��B
	 * <br>
	 * {@link Optimizer} �ɂ� {@link SimpleOptimizer} ���g�p����܂��B
	 * <br>
	 * {@link RowLockOption} �ɂ� {@link RowLockOption#NONE} ���g�p����܂��B
	 *
	 * @param primaryKeyMembers ��L�[���\�����镶����
	 * @return {@link DTO} ���݂��Ȃ���� null
	 */
	public T select(String... primaryKeyMembers) {
		return select(RowLockOption.NONE, primaryKeyMembers);
	}

	/**
	 * �p�����[�^�̎�L�[�̒l������ {@link DTO} ���������Ԃ��܂��B
	 * <br>
	 * {@link Optimizer} �ɂ� {@link SimpleOptimizer} ���g�p����܂��B
	 *
	 * @param rowLockOption �s���b�N�I�v�V����
	 * @param primaryKeyMembers ��L�[���\������l
	 * @return {@link DTO} ���݂��Ȃ���� null
	 */
	public T select(RowLockOption rowLockOption, Bindable... primaryKeyMembers) {
		return select(
			new SimpleOptimizer(getResourceLocator()),
			rowLockOption,
			primaryKeyMembers);
	}

	/**
	 * �p�����[�^�̎�L�[�̒l������ {@link DTO} ���������Ԃ��܂��B
	 * <br>
	 * {@link Optimizer} �ɂ� {@link SimpleOptimizer} ���g�p����܂��B
	 * <br>
	 * {@link RowLockOption} �ɂ� {@link RowLockOption#NONE} ���g�p����܂��B
	 *
	 * @param primaryKeyMembers ��L�[���\������l
	 * @return {@link DTO} ���݂��Ȃ���� null
	 */
	public T select(Bindable... primaryKeyMembers) {
		return select(RowLockOption.NONE, primaryKeyMembers);
	}

	/**
	 * �p�����[�^�̎�L�[�̒l������ {@link DTO} ���������Ԃ��܂��B
	 *
	 * @param optimizer SELECT ��𐧌䂷�� {@link Optimizer}
	 * @param rowLockOption �s���b�N�I�v�V����
	 * @param primaryKeyMembers ��L�[���\�����镶����
	 * @return {@link DTO} ���݂��Ȃ���� null
	 */
	public T select(
		Optimizer optimizer,
		RowLockOption rowLockOption,
		String... primaryKeyMembers) {
		UpdatableDataObject object;
		try {
			object = helper.getUpdatableDataObject(
				optimizer,
				PrimaryKey.getInstance(getResourceLocator(), primaryKeyMembers),
				rowLockOption);
		} catch (DataObjectNotFoundException e) {
			return null;
		}
		return createDTO(object);
	}

	/**
	 * �p�����[�^�̎�L�[�̒l������ {@link DTO} ���������Ԃ��܂��B
	 * <br>
	 * {@link RowLockOption} �ɂ� {@link RowLockOption#NONE} ���g�p����܂��B
	 *
	 * @param optimizer SELECT ��𐧌䂷�� {@link Optimizer}
	 * @param primaryKeyMembers ��L�[���\�����镶����
	 * @return {@link DTO} ���݂��Ȃ���� null
	 */
	public T select(Optimizer optimizer, String... primaryKeyMembers) {
		return select(optimizer, RowLockOption.NONE, primaryKeyMembers);
	}

	/**
	 * �p�����[�^�̎�L�[�̒l������ {@link DTO} ���������Ԃ��܂��B
	 *
	 * @param optimizer SELECT ��𐧌䂷�� {@link Optimizer}
	 * @param rowLockOption �s���b�N�I�v�V����
	 * @param primaryKeyMembers ��L�[���\������l
	 * @return {@link DTO} ���݂��Ȃ���� null
	 */
	public T select(
		Optimizer optimizer,
		RowLockOption rowLockOption,
		Bindable... primaryKeyMembers) {
		UpdatableDataObject object;
		try {
			object = helper.getUpdatableDataObject(optimizer, new PrimaryKey(
				getResourceLocator(),
				primaryKeyMembers), rowLockOption);
		} catch (DataObjectNotFoundException e) {
			return null;
		}
		return createDTO(object);
	}

	/**
	 * �p�����[�^�̎�L�[�̒l������ {@link DTO} ���������Ԃ��܂��B
	 * <br>
	 * {@link RowLockOption} �ɂ� {@link RowLockOption#NONE} ���g�p����܂��B
	 *
	 * @param optimizer SELECT ��𐧌䂷�� {@link Optimizer}
	 * @param primaryKeyMembers ��L�[���\������l
	 * @return {@link DTO} ���݂��Ȃ���� null
	 */
	public T select(Optimizer optimizer, Bindable... primaryKeyMembers) {
		return select(optimizer, RowLockOption.NONE, primaryKeyMembers);
	}

	/**
	 * �p�����[�^�̏����Ƀ}�b�`���錏����Ԃ��܂��B
	 *
	 * @param condition WHERE ��ƂȂ����
	 * @return �p�����[�^�̏����Ƀ}�b�`���錏��
	 */
	public int count(Condition condition) {
		return helper.count(getResourceLocator(), condition);
	}

	/**
	 * �p�����[�^�� DTO �� INSERT ���s���܂��B
	 *
	 * @param dto INSERT �Ώ�
	 */
	public void insert(T dto) {
		helper.insert(getResourceLocator(), dto, null);
	}

	/**
	 * �p�����[�^�� DTO �� INSERT ���o�b�`���s���܂��B
	 *
	 * @param statement �o�b�`���s���˗����� {@link BatchStatement}
	 * @param dto INSERT �Ώ�
	 */
	public void insert(BatchStatement statement, T dto) {
		helper.insert(statement, getResourceLocator(), dto, null);
	}

	/**
	 * �p�����[�^�� DTO �� INSERT ���s���܂��B
	 *
	 * @param dto INSERT �Ώ�
	 * @param adjuster INSERT ���𒲐����� {@link SQLAdjuster}
	 */
	public void insert(T dto, SQLAdjuster adjuster) {
		helper.insert(getResourceLocator(), dto, adjuster);
	}

	/**
	 * �p�����[�^�� DTO �� INSERT ���o�b�`���s���܂��B
	 *
	 * @param statement �o�b�`���s���˗����� {@link BatchStatement}
	 * @param dto INSERT �Ώ�
	 * @param adjuster INSERT ���𒲐����� {@link SQLAdjuster}
	 */
	public void insert(BatchStatement statement, T dto, SQLAdjuster adjuster) {
		helper.insert(statement, getResourceLocator(), dto, adjuster);
	}

	/**
	 * �p�����[�^�� DTO �� INSERT ���s���܂��B
	 *
	 * @param generator �ΏۂƂȂ鍀�ڂƒl������ {@link SequenceGenerator}
	 * @param dto INSERT �Ώ�
	 * @param retry {@link SequenceGenerator} �̃��g���C��
	 * @return INSERT ���ꂽ���ۂ̘A���l
	 */
	public Bindable insert(SequenceGenerator generator, T dto, int retry) {
		return helper.insert(getResourceLocator(), generator, dto, retry, null);
	}

	/**
	 * �p�����[�^�� DTO �� INSERT ���o�b�`���s���܂��B
	 *
	 * @param statement �o�b�`���s���˗����� {@link BatchStatement}
	 * @param generator �ΏۂƂȂ鍀�ڂƒl������ {@link SequenceGenerator}
	 * @param dto INSERT �Ώ�
	 * @param retry {@link SequenceGenerator} �̃��g���C��
	 * @return INSERT ���ꂽ���ۂ̘A���l
	 */
	public Bindable insert(
		BatchStatement statement,
		SequenceGenerator generator,
		T dto,
		int retry) {
		return helper.insert(
			statement,
			getResourceLocator(),
			generator,
			dto,
			retry,
			null);
	}

	/**
	 * �p�����[�^�� DTO �� INSERT ���s���܂��B
	 *
	 * @param generator �ΏۂƂȂ鍀�ڂƒl������ {@link SequenceGenerator}
	 * @param dto INSERT �Ώ�
	 * @param retry {@link SequenceGenerator} �̃��g���C��
	 * @param adjuster INSERT ���𒲐����� {@link SQLAdjuster}
	 * @return INSERT ���ꂽ���ۂ̘A���l
	 */
	public Bindable insert(
		SequenceGenerator generator,
		T dto,
		int retry,
		SQLAdjuster adjuster) {
		return helper.insert(
			getResourceLocator(),
			generator,
			dto,
			retry,
			adjuster);
	}

	/**
	 * �p�����[�^�� DTO �� INSERT ���o�b�`���s���܂��B
	 *
	 * @param statement �o�b�`���s���˗����� {@link BatchStatement}
	 * @param generator �ΏۂƂȂ鍀�ڂƒl������ {@link SequenceGenerator}
	 * @param dto INSERT �Ώ�
	 * @param retry {@link SequenceGenerator} �̃��g���C��
	 * @param adjuster INSERT ���𒲐����� {@link SQLAdjuster}
	 * @return INSERT ���ꂽ���ۂ̘A���l
	 */
	public Bindable insert(
		BatchStatement statement,
		SequenceGenerator generator,
		T dto,
		int retry,
		SQLAdjuster adjuster) {
		return helper.insert(
			statement,
			getResourceLocator(),
			generator,
			dto,
			retry,
			adjuster);
	}

	/**
	 * �p�����[�^�� DTO �� DELETE ���s���܂��B
	 *
	 * @param dto DELETE �Ώ�
	 * @return �폜�����������ꍇ�A true
	 */
	public boolean delete(T dto) {
		int result = helper.delete(getResourceLocator(), dto.getPrimaryKey()
			.getCondition());
		if (result > 1) throw new IllegalStateException("�폜����������������܂��B");
		return result == 1;
	}

	/**
	 * �p�����[�^�� DTO �� DELETE ���o�b�`���s���܂��B
	 *
	 * @param statement �o�b�`���s���˗����� {@link BatchStatement}
	 * @param dto DELETE �Ώ�
	 */
	public void delete(BatchStatement statement, T dto) {
		helper.delete(statement, getResourceLocator(), dto.getPrimaryKey()
			.getCondition());
	}

	/**
	 * �p�����[�^�̏����ɊY������s���X�V���܂��B
	 *
	 * @param condition WHERE ��ƂȂ����
	 * @param updatable UPDATE ����l������ {@link Updatable}
	 * @return �X�V����
	 */
	public int update(Condition condition, Updatable updatable) {
		return helper.update(getResourceLocator(), updatable, condition, null);
	}

	/**
	 * �p�����[�^�̏����ɊY������s�̍X�V���o�b�`���s���܂��B
	 *
	 * @param statement �o�b�`���s���˗����� {@link BatchStatement}
	 * @param condition WHERE ��ƂȂ����
	 * @param updatable UPDATE ����l������ {@link Updatable}
	 */
	public void update(
		BatchStatement statement,
		Condition condition,
		Updatable updatable) {
		helper.update(
			statement,
			getResourceLocator(),
			updatable,
			condition,
			null);
	}

	/**
	 * �p�����[�^�̏����ɊY������s���X�V���܂��B
	 *
	 * @param condition WHERE ��ƂȂ����
	 * @param updatable UPDATE ����l������ {@link Updatable}
	 * @param adjuster UPDATE ���𒲐����� {@link SQLAdjuster}
	 * @return �X�V����
	 */
	public int update(
		Condition condition,
		Updatable updatable,
		SQLAdjuster adjuster) {
		return helper.update(
			getResourceLocator(),
			updatable,
			condition,
			adjuster);
	}

	/**
	 * �p�����[�^�̏����ɊY������s�̍X�V���o�b�`���s���܂��B
	 *
	 * @param statement �o�b�`���s���˗����� {@link BatchStatement}
	 * @param condition WHERE ��ƂȂ����
	 * @param adjuster UPDATE ���𒲐����� {@link SQLAdjuster}
	 * @param updatable UPDATE ����l������ {@link Updatable}
	 */
	public void update(
		BatchStatement statement,
		Condition condition,
		Updatable updatable,
		SQLAdjuster adjuster) {
		helper.update(
			statement,
			getResourceLocator(),
			updatable,
			condition,
			adjuster);
	}

	/**
	 * �p�����[�^�̏����ɊY������s���폜���܂��B
	 *
	 * @param condition WHERE ��ƂȂ����
	 * @return �폜����
	 */
	public int delete(Condition condition) {
		return helper.delete(getResourceLocator(), condition);
	}

	/**
	 * �p�����[�^�̏����ɊY������s�̍폜���o�b�`���s���܂��B
	 *
	 * @param statement �o�b�`���s���˗����� {@link BatchStatement}
	 * @param condition WHERE ��ƂȂ����
	 */
	public void delete(BatchStatement statement, Condition condition) {
		helper.delete(statement, getResourceLocator(), condition);
	}

	/**
	 * DTO �𐶐����郁�\�b�h�ł��B
	 *
	 * @param data ���� DTO �̑S�v�f�̒l�����������ʃI�u�W�F�N�g
	 * @return �������ꂽ DTO
	 */
	protected abstract T createDTO(UpdatableDataObject data);

	/**
	 * �T�u�N���X�ŌŗL�� {@link ResourceLocator} ��Ԃ��܂��B
	 *
	 * @return �ŗL�� {@link ResourceLocator}
	 */
	protected abstract ResourceLocator getResourceLocator();
}
