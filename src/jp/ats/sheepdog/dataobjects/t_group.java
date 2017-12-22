package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * �����������ꂽ�萔��`�C���^�[�t�F�C�X�ł��B
 *
 * �p�b�P�[�W�� jp.ats.sheepdog.dataobjects
 * �e�[�u���� t_group
 */
public interface t_group {

	/**
	 * ���̃C���^�[�t�F�C�X�̃e�[�u�����w�� {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("t_group");

	/**
	 * ���ږ� id
	 */
	String id = "id";

	/**
	 * ���ږ� name
	 */
	String name = "name";

	/**
	 * ���ږ� description
	 */
	String description = "description";

	/**
	 * ���ږ� owner
	 */
	String owner = "owner";

	/**
	 * ���ږ� applying_level
	 */
	String applying_level = "applying_level";

	/**
	 * ���ږ� private_flag
	 */
	String private_flag = "private_flag";

	/**
	 * ���ږ� delete_flag
	 */
	String delete_flag = "delete_flag";

	/**
	 * ���ږ� create_time
	 */
	String create_time = "create_time";

	/**
	 * ���ږ� update_time
	 */
	String update_time = "update_time";

	/**
	 * �Q�Ɛ�e�[�u���� t_user
	 * �O���L�[�� t_group_owner_fkey
	 */
	String t_user_BY_t_group_owner_fkey = "t_group_owner_fkey";

}
