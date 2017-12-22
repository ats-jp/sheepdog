package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * �����������ꂽ�萔��`�C���^�[�t�F�C�X�ł��B
 *
 * �p�b�P�[�W�� jp.ats.sheepdog.dataobjects
 * �e�[�u���� t_content
 */
public interface t_content {

	/**
	 * ���̃C���^�[�t�F�C�X�̃e�[�u�����w�� {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("t_content");

	/**
	 * ���ږ� id
	 */
	String id = "id";

	/**
	 * ���ږ� thread_id
	 */
	String thread_id = "thread_id";

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
	 * ���ږ� create_time
	 */
	String create_time = "create_time";

	/**
	 * �Q�Ɛ�e�[�u���� t_thread
	 * �O���L�[�� t_content_thread_id_fkey
	 */
	String t_thread_BY_t_content_thread_id_fkey = "t_content_thread_id_fkey";

	/**
	 * �Q�Ɛ�e�[�u���� t_user
	 * �O���L�[�� t_content_owner_fkey
	 */
	String t_user_BY_t_content_owner_fkey = "t_content_owner_fkey";

}
