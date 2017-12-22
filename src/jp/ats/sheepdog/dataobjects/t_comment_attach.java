package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * �����������ꂽ�萔��`�C���^�[�t�F�C�X�ł��B
 *
 * �p�b�P�[�W�� jp.ats.sheepdog.dataobjects
 * �e�[�u���� t_comment_attach
 */
public interface t_comment_attach {

	/**
	 * ���̃C���^�[�t�F�C�X�̃e�[�u�����w�� {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("t_comment_attach");

	/**
	 * ���ږ� attach_id
	 */
	String attach_id = "attach_id";

	/**
	 * ���ږ� comment_id
	 */
	String comment_id = "comment_id";

	/**
	 * ���ږ� create_time
	 */
	String create_time = "create_time";

	/**
	 * �Q�Ɛ�e�[�u���� t_attach
	 * �O���L�[�� t_comment_attach_attach_id_fkey
	 */
	String t_attach_BY_t_comment_attach_attach_id_fkey = "t_comment_attach_attach_id_fkey";

	/**
	 * �Q�Ɛ�e�[�u���� t_comment
	 * �O���L�[�� t_comment_attach_comment_id_fkey
	 */
	String t_comment_BY_t_comment_attach_comment_id_fkey = "t_comment_attach_comment_id_fkey";

}
