package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * �����������ꂽ�萔��`�C���^�[�t�F�C�X�ł��B
 *
 * �p�b�P�[�W�� jp.ats.sheepdog.dataobjects
 * �e�[�u���� t_comment
 */
public interface t_comment {

	/**
	 * ���̃C���^�[�t�F�C�X�̃e�[�u�����w�� {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("t_comment");

	/**
	 * ���ږ� id
	 */
	String id = "id";

	/**
	 * ���ږ� thread_id
	 */
	String thread_id = "thread_id";

	/**
	 * ���ږ� comment_number
	 */
	String comment_number = "comment_number";

	/**
	 * ���ږ� parent_id
	 */
	String parent_id = "parent_id";

	/**
	 * ���ږ� owner
	 */
	String owner = "owner";

	/**
	 * ���ږ� body
	 */
	String body = "body";

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
	 * �Q�Ɛ�e�[�u���� v_comment_relationship
	 * �O���L�[�� t_comment_id_fkey
	 */
	String v_comment_relationship_BY_t_comment_id_fkey = "t_comment_id_fkey";

	/**
	 * �Q�Ɛ�e�[�u���� t_comment
	 * �O���L�[�� t_comment_parent_id_fkey
	 */
	String t_comment_BY_t_comment_parent_id_fkey = "t_comment_parent_id_fkey";

	/**
	 * �Q�Ɛ�e�[�u���� t_thread
	 * �O���L�[�� t_comment_thread_id_fkey
	 */
	String t_thread_BY_t_comment_thread_id_fkey = "t_comment_thread_id_fkey";

	/**
	 * �Q�Ɛ�e�[�u���� t_user
	 * �O���L�[�� t_comment_owner_fkey
	 */
	String t_user_BY_t_comment_owner_fkey = "t_comment_owner_fkey";

}
