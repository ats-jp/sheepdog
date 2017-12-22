package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * �����������ꂽ�萔��`�C���^�[�t�F�C�X�ł��B
 *
 * �p�b�P�[�W�� jp.ats.sheepdog.dataobjects
 * �e�[�u���� t_thread
 */
public interface t_thread {

	/**
	 * ���̃C���^�[�t�F�C�X�̃e�[�u�����w�� {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("t_thread");

	/**
	 * ���ږ� id
	 */
	String id = "id";

	/**
	 * ���ږ� calendar
	 */
	String calendar = "calendar";

	/**
	 * ���ږ� group_id
	 */
	String group_id = "group_id";

	/**
	 * ���ږ� event_flag
	 */
	String event_flag = "event_flag";

	/**
	 * ���ږ� last_title
	 */
	String last_title = "last_title";

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
	 * �Q�Ɛ�e�[�u���� t_group
	 * �O���L�[�� t_thread_group_id_fkey
	 */
	String t_group_BY_t_thread_group_id_fkey = "t_thread_group_id_fkey";

	/**
	 * �Q�Ɛ�e�[�u���� t_user
	 * �O���L�[�� t_thread_owner_fkey
	 */
	String t_user_BY_t_thread_owner_fkey = "t_thread_owner_fkey";

}
