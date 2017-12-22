package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * �����������ꂽ�萔��`�C���^�[�t�F�C�X�ł��B
 *
 * �p�b�P�[�W�� jp.ats.sheepdog.dataobjects
 * �e�[�u���� t_content_attach
 */
public interface t_content_attach {

	/**
	 * ���̃C���^�[�t�F�C�X�̃e�[�u�����w�� {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("t_content_attach");

	/**
	 * ���ږ� id
	 */
	String id = "id";

	/**
	 * ���ږ� attach_id
	 */
	String attach_id = "attach_id";

	/**
	 * ���ږ� content_id
	 */
	String content_id = "content_id";

	/**
	 * ���ږ� create_time
	 */
	String create_time = "create_time";

	/**
	 * �Q�Ɛ�e�[�u���� t_attach
	 * �O���L�[�� t_content_attach_attach_id_fkey
	 */
	String t_attach_BY_t_content_attach_attach_id_fkey = "t_content_attach_attach_id_fkey";

	/**
	 * �Q�Ɛ�e�[�u���� t_content
	 * �O���L�[�� t_content_attach_content_id_fkey
	 */
	String t_content_BY_t_content_attach_content_id_fkey = "t_content_attach_content_id_fkey";

}
