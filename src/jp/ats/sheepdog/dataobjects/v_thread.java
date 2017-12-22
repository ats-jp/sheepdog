package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * 自動生成された定数定義インターフェイスです。
 *
 * パッケージ名 jp.ats.sheepdog.dataobjects
 * テーブル名 v_thread
 */
public interface v_thread {

	/**
	 * このインターフェイスのテーブルを指す {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("v_thread");

	/**
	 * 項目名 thread_id
	 */
	String thread_id = "thread_id";

	/**
	 * 項目名 content_id
	 */
	String content_id = "content_id";

	/**
	 * 項目名 calendar
	 */
	String calendar = "calendar";

	/**
	 * 項目名 title
	 */
	String title = "title";

	/**
	 * 項目名 body
	 */
	String body = "body";

	/**
	 * 項目名 owner
	 */
	String owner = "owner";

	/**
	 * 項目名 group_id
	 */
	String group_id = "group_id";

	/**
	 * 項目名 event_flag
	 */
	String event_flag = "event_flag";

	/**
	 * 項目名 applying_level
	 */
	String applying_level = "applying_level";

	/**
	 * 項目名 private_flag
	 */
	String private_flag = "private_flag";

	/**
	 * 項目名 content_count
	 */
	String content_count = "content_count";

	/**
	 * 項目名 comment_count
	 */
	String comment_count = "comment_count";

	/**
	 * 項目名 create_time
	 */
	String create_time = "create_time";

	/**
	 * 項目名 update_time
	 */
	String update_time = "update_time";

	/**
	 * 項目名 content_create_time
	 */
	String content_create_time = "content_create_time";

	/**
	 * 参照先テーブル名 t_user
	 * 外部キー名 v_thread_owner_fkey
	 */
	String t_user_BY_v_thread_owner_fkey = "v_thread_owner_fkey";

	/**
	 * 参照先テーブル名 t_group
	 * 外部キー名 v_thread_group_id_fkey
	 */
	String t_group_BY_v_thread_group_id_fkey = "v_thread_group_id_fkey";

}
