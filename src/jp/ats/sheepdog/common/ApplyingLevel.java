package jp.ats.sheepdog.common;

public enum ApplyingLevel {

	�O���[�v�O�S�ĂɌ��J,

	�O���[�v�����o�[�̂݌��J,

	����J;

	public boolean canChangeTo(ApplyingLevel to) {
		return ordinal() - to.ordinal() >= 0;
	}
}
