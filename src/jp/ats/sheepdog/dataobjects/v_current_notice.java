package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * �����������ꂽ�萔��`�C���^�[�t�F�C�X�ł��B
 *
 * �p�b�P�[�W�� jp.ats.sheepdog.dataobjects
 * �e�[�u���� v_current_notice
 */
public interface v_current_notice {

	/**
	 * ���̃C���^�[�t�F�C�X�̃e�[�u�����w�� {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("v_current_notice");

	/**
	 * ���ږ� owner
	 */
	String owner = "owner";

	/**
	 * ���ږ� max_id
	 */
	String max_id = "max_id";

	/**
	 * ���ږ� last_id
	 */
	String last_id = "last_id";

}
