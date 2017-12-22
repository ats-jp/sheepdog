package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * �����������ꂽ�萔��`�C���^�[�t�F�C�X�ł��B
 *
 * �p�b�P�[�W�� jp.ats.sheepdog.dataobjects
 * �e�[�u���� t_member
 */
public interface t_member {

	/**
	 * ���̃C���^�[�t�F�C�X�̃e�[�u�����w�� {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("t_member");

	/**
	 * ���ږ� id
	 */
	String id = "id";

	/**
	 * ���ږ� user_id
	 */
	String user_id = "user_id";

	/**
	 * ���ږ� group_id
	 */
	String group_id = "group_id";

	/**
	 * ���ږ� create_time
	 */
	String create_time = "create_time";

	/**
	 * �Q�Ɛ�e�[�u���� t_group
	 * �O���L�[�� t_member_group_id_fkey
	 */
	String t_group_BY_t_member_group_id_fkey = "t_member_group_id_fkey";

	/**
	 * �Q�Ɛ�e�[�u���� t_user
	 * �O���L�[�� t_member_user_id_fkey
	 */
	String t_user_BY_t_member_user_id_fkey = "t_member_user_id_fkey";

}
