package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * �����������ꂽ�萔��`�C���^�[�t�F�C�X�ł��B
 *
 * �p�b�P�[�W�� jp.ats.sheepdog.dataobjects
 * �e�[�u���� v_thread
 */
public interface v_thread {

	/**
	 * ���̃C���^�[�t�F�C�X�̃e�[�u�����w�� {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("v_thread");

	/**
	 * ���ږ� thread_id
	 */
	String thread_id = "thread_id";

	/**
	 * ���ږ� content_id
	 */
	String content_id = "content_id";

	/**
	 * ���ږ� calendar
	 */
	String calendar = "calendar";

	/**
	 * ���ږ� title
	 */
	String title = "title";

	/**
	 * ���ږ� body
	 */
	String body = "body";

	/**
	 * ���ږ� owner
	 */
	String owner = "owner";

	/**
	 * ���ږ� group_id
	 */
	String group_id = "group_id";

	/**
	 * ���ږ� event_flag
	 */
	String event_flag = "event_flag";

	/**
	 * ���ږ� applying_level
	 */
	String applying_level = "applying_level";

	/**
	 * ���ږ� private_flag
	 */
	String private_flag = "private_flag";

	/**
	 * ���ږ� content_count
	 */
	String content_count = "content_count";

	/**
	 * ���ږ� comment_count
	 */
	String comment_count = "comment_count";

	/**
	 * ���ږ� create_time
	 */
	String create_time = "create_time";

	/**
	 * ���ږ� update_time
	 */
	String update_time = "update_time";

	/**
	 * ���ږ� content_create_time
	 */
	String content_create_time = "content_create_time";

	/**
	 * �Q�Ɛ�e�[�u���� t_user
	 * �O���L�[�� v_thread_owner_fkey
	 */
	String t_user_BY_v_thread_owner_fkey = "v_thread_owner_fkey";

	/**
	 * �Q�Ɛ�e�[�u���� t_group
	 * �O���L�[�� v_thread_group_id_fkey
	 */
	String t_group_BY_v_thread_group_id_fkey = "v_thread_group_id_fkey";

}
