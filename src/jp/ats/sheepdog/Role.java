package jp.ats.sheepdog;

/**
 * ���̃N���X�� auth.jar �ł��g�p����邽�߁A���̃N���X�Ɉˑ����Ȃ��悤�ɂ���K�v������܂��B
 */
public enum Role {

	SYSTEM("�V�X�e���Ǘ���"),

	ADMIN("�Ǘ���"),

	USER("���p��");

	private final String name;

	private Role(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
