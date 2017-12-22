package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * �����������ꂽ�萔��`�C���^�[�t�F�C�X�ł��B
 *
 * �p�b�P�[�W�� jp.ats.sheepdog.dataobjects
 * �e�[�u���� t_member_order
 */
public interface t_member_order {

	/**
	 * ���̃C���^�[�t�F�C�X�̃e�[�u�����w�� {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("t_member_order");

	/**
	 * ���ږ� owner
	 */
	String owner = "owner";

	/**
	 * ���ږ� group_id
	 */
	String group_id = "group_id";

	/**
	 * ���ږ� member_order
	 */
	String member_order = "member_order";

	/**
	 * ���ږ� create_time
	 */
	String create_time = "create_time";

	/**
	 * ���ږ� update_time
	 */
	String update_time = "update_time";

	/**
	 * �Q�Ɛ�e�[�u���� t_group
	 * �O���L�[�� t_member_order_group_id_fkey
	 */
	String t_group_BY_t_member_order_group_id_fkey = "t_member_order_group_id_fkey";

	/**
	 * �Q�Ɛ�e�[�u���� t_user
	 * �O���L�[�� t_member_order_owner_fkey
	 */
	String t_user_BY_t_member_order_owner_fkey = "t_member_order_owner_fkey";

}
