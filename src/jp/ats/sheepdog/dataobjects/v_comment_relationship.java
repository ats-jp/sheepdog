package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * �����������ꂽ�萔��`�C���^�[�t�F�C�X�ł��B
 *
 * �p�b�P�[�W�� jp.ats.sheepdog.dataobjects
 * �e�[�u���� v_comment_relationship
 */
public interface v_comment_relationship {

	/**
	 * ���̃C���^�[�t�F�C�X�̃e�[�u�����w�� {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator(
		"v_comment_relationship");

	/**
	 * ���ږ� comment_id
	 */
	String comment_id = "comment_id";

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
	 * ���ږ� parent_number
	 */
	String parent_number = "parent_number";

	/**
	 * ���ږ� attach_count
	 */
	String attach_count = "attach_count";

	/**
	 * �Q�Ɛ�e�[�u���� t_thread
	 * �O���L�[�� v_comment_relationship_thread_id_fkey
	 */
	String t_thread_BY_v_comment_relationship_thread_id_fkey = "v_comment_relationship_thread_id_fkey";

}
