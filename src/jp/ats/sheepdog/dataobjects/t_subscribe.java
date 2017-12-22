package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * �����������ꂽ�萔��`�C���^�[�t�F�C�X�ł��B
 *
 * �p�b�P�[�W�� jp.ats.sheepdog.dataobjects
 * �e�[�u���� t_subscribe
 */
public interface t_subscribe {

	/**
	 * ���̃C���^�[�t�F�C�X�̃e�[�u�����w�� {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("t_subscribe");

	/**
	 * ���ږ� id
	 */
	String id = "id";

	/**
	 * ���ږ� group_for_thread
	 */
	String group_for_thread = "group_for_thread";

	/**
	 * ���ږ� thread_for_comment
	 */
	String thread_for_comment = "thread_for_comment";

	/**
	 * ���ږ� owner
	 */
	String owner = "owner";

	/**
	 * ���ږ� create_time
	 */
	String create_time = "create_time";

	/**
	 * �Q�Ɛ�e�[�u���� t_group
	 * �O���L�[�� t_subscribe_group_for_thread_fkey
	 */
	String t_group_BY_t_subscribe_group_for_thread_fkey = "t_subscribe_group_for_thread_fkey";

	/**
	 * �Q�Ɛ�e�[�u���� t_thread
	 * �O���L�[�� t_subscribe_thread_for_comment_fkey
	 */
	String t_thread_BY_t_subscribe_thread_for_comment_fkey = "t_subscribe_thread_for_comment_fkey";

	/**
	 * �Q�Ɛ�e�[�u���� t_user
	 * �O���L�[�� t_subscribe_owner_fkey
	 */
	String t_user_BY_t_subscribe_owner_fkey = "t_subscribe_owner_fkey";

}
