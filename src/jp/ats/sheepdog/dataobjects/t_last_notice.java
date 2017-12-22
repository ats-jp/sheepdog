package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * �����������ꂽ�萔��`�C���^�[�t�F�C�X�ł��B
 *
 * �p�b�P�[�W�� jp.ats.sheepdog.dataobjects
 * �e�[�u���� t_last_notice
 */
public interface t_last_notice {

	/**
	 * ���̃C���^�[�t�F�C�X�̃e�[�u�����w�� {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("t_last_notice");

	/**
	 * ���ږ� owner
	 */
	String owner = "owner";

	/**
	 * ���ږ� last_notice
	 */
	String last_notice = "last_notice";

	/**
	 * ���ږ� create_time
	 */
	String create_time = "create_time";

	/**
	 * �Q�Ɛ�e�[�u���� t_user
	 * �O���L�[�� t_last_notice_owner_fkey
	 */
	String t_user_BY_t_last_notice_owner_fkey = "t_last_notice_owner_fkey";

}
