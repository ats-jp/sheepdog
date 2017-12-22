package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * �����������ꂽ�萔��`�C���^�[�t�F�C�X�ł��B
 *
 * �p�b�P�[�W�� jp.ats.sheepdog.dataobjects
 * �e�[�u���� t_notice
 */
public interface t_notice {

	/**
	 * ���̃C���^�[�t�F�C�X�̃e�[�u�����w�� {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("t_notice");

	/**
	 * ���ږ� id
	 */
	String id = "id";

	/**
	 * ���ږ� owner
	 */
	String owner = "owner";

	/**
	 * ���ږ� group_id
	 */
	String group_id = "group_id";

	/**
	 * ���ږ� thread_id
	 */
	String thread_id = "thread_id";

	/**
	 * ���ږ� comment_id
	 */
	String comment_id = "comment_id";

	/**
	 * ���ږ� create_time
	 */
	String create_time = "create_time";

	/**
	 * �Q�Ɛ�e�[�u���� t_comment
	 * �O���L�[�� t_notice_comment_id_fkey
	 */
	String t_comment_BY_t_notice_comment_id_fkey = "t_notice_comment_id_fkey";

	/**
	 * �Q�Ɛ�e�[�u���� t_group
	 * �O���L�[�� t_notice_group_id_fkey
	 */
	String t_group_BY_t_notice_group_id_fkey = "t_notice_group_id_fkey";

	/**
	 * �Q�Ɛ�e�[�u���� t_thread
	 * �O���L�[�� t_notice_thread_id_fkey
	 */
	String t_thread_BY_t_notice_thread_id_fkey = "t_notice_thread_id_fkey";

	/**
	 * �Q�Ɛ�e�[�u���� t_user
	 * �O���L�[�� t_notice_owner_fkey
	 */
	String t_user_BY_t_notice_owner_fkey = "t_notice_owner_fkey";

}
