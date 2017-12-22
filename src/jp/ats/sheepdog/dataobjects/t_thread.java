package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * 自動生成された定数定義インターフェイスです。
 *
 * パッケージ名 jp.ats.sheepdog.dataobjects
 * テーブル名 t_thread
 */
public interface t_thread {

	/**
	 * このインターフェイスのテーブルを指す {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("t_thread");

	/**
	 * 項目名 id
	 */
	String id = "id";

	/**
	 * 項目名 calendar
	 */
	String calendar = "calendar";

	/**
	 * 項目名 group_id
	 */
	String group_id = "group_id";

	/**
	 * 項目名 event_flag
	 */
	String event_flag = "event_flag";

	/**
	 * 項目名 last_title
	 */
	String last_title = "last_title";

	/**
	 * 項目名 owner
	 */
	String owner = "owner";

	/**
	 * 項目名 applying_level
	 */
	String applying_level = "applying_level";

	/**
	 * 項目名 private_flag
	 */
	String private_flag = "private_flag";

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

	/**
	 * 参照先テーブル名 t_group
	 * 外部キー名 t_thread_group_id_fkey
	 */
	String t_group_BY_t_thread_group_id_fkey = "t_thread_group_id_fkey";

	/**
	 * 参照先テーブル名 t_user
	 * 外部キー名 t_thread_owner_fkey
	 */
	String t_user_BY_t_thread_owner_fkey = "t_thread_owner_fkey";

}
