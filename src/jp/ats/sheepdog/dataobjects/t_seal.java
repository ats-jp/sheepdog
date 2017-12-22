package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * �����������ꂽ�萔��`�C���^�[�t�F�C�X�ł��B
 *
 * �p�b�P�[�W�� jp.ats.sheepdog.dataobjects
 * �e�[�u���� t_seal
 */
public interface t_seal {

	/**
	 * ���̃C���^�[�t�F�C�X�̃e�[�u�����w�� {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("t_seal");

	/**
	 * ���ږ� id
	 */
	String id = "id";

	/**
	 * ���ږ� owner
	 */
	String owner = "owner";

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
	 * �O���L�[�� t_seal_comment_id_fkey
	 */
	String t_comment_BY_t_seal_comment_id_fkey = "t_seal_comment_id_fkey";

	/**
	 * �Q�Ɛ�e�[�u���� t_thread
	 * �O���L�[�� t_seal_thread_id_fkey
	 */
	String t_thread_BY_t_seal_thread_id_fkey = "t_seal_thread_id_fkey";

	/**
	 * �Q�Ɛ�e�[�u���� t_user
	 * �O���L�[�� t_seal_owner_fkey
	 */
	String t_user_BY_t_seal_owner_fkey = "t_seal_owner_fkey";

}
