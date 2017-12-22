package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * 自動生成された定数定義インターフェイスです。
 *
 * パッケージ名 jp.ats.sheepdog.dataobjects
 * テーブル名 t_user
 */
public interface t_user {

	/**
	 * このインターフェイスのテーブルを指す {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("t_user");

	/**
	 * 項目名 id
	 */
	String id = "id";

	/**
	 * 項目名 mail_address
	 */
	String mail_address = "mail_address";

	/**
	 * 項目名 evacuated_mail_address
	 */
	String evacuated_mail_address = "evacuated_mail_address";

	/**
	 * 項目名 name
	 */
	String name = "name";

	/**
	 * 項目名 department
	 */
	String department = "department";

	/**
	 * 項目名 title
	 */
	String title = "title";

	/**
	 * 項目名 salt
	 */
	String salt = "salt";

	/**
	 * 項目名 password
	 */
	String password = "password";

	/**
	 * 項目名 expiration_date
	 */
	String expiration_date = "expiration_date";

	/**
	 * 項目名 role
	 */
	String role = "role";

	/**
	 * 項目名 delete_flag
	 */
	String delete_flag = "delete_flag";

	/**
	 * 項目名 create_time
	 */
	String create_time = "create_time";

	/**
	 * 項目名 update_time
	 */
	String update_time = "update_time";

}
