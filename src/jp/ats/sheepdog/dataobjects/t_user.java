package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * �����������ꂽ�萔��`�C���^�[�t�F�C�X�ł��B
 *
 * �p�b�P�[�W�� jp.ats.sheepdog.dataobjects
 * �e�[�u���� t_user
 */
public interface t_user {

	/**
	 * ���̃C���^�[�t�F�C�X�̃e�[�u�����w�� {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("t_user");

	/**
	 * ���ږ� id
	 */
	String id = "id";

	/**
	 * ���ږ� mail_address
	 */
	String mail_address = "mail_address";

	/**
	 * ���ږ� evacuated_mail_address
	 */
	String evacuated_mail_address = "evacuated_mail_address";

	/**
	 * ���ږ� name
	 */
	String name = "name";

	/**
	 * ���ږ� department
	 */
	String department = "department";

	/**
	 * ���ږ� title
	 */
	String title = "title";

	/**
	 * ���ږ� salt
	 */
	String salt = "salt";

	/**
	 * ���ږ� password
	 */
	String password = "password";

	/**
	 * ���ږ� expiration_date
	 */
	String expiration_date = "expiration_date";

	/**
	 * ���ږ� role
	 */
	String role = "role";

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
