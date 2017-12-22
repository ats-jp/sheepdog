package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * �����������ꂽ�萔��`�C���^�[�t�F�C�X�ł��B
 *
 * �p�b�P�[�W�� jp.ats.sheepdog.dataobjects
 * �e�[�u���� t_attach
 */
public interface t_attach {

	/**
	 * ���̃C���^�[�t�F�C�X�̃e�[�u�����w�� {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("t_attach");

	/**
	 * ���ږ� id
	 */
	String id = "id";

	/**
	 * ���ږ� name
	 */
	String name = "name";

	/**
	 * ���ږ� size
	 */
	String size = "size";

	/**
	 * ���ږ� path
	 */
	String path = "path";

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

}
