package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * 自動生成された定数定義インターフェイスです。
 *
 * パッケージ名 jp.ats.sheepdog.dataobjects
 * テーブル名 t_subscribe
 */
public interface t_subscribe {

	/**
	 * このインターフェイスのテーブルを指す {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("t_subscribe");

	/**
	 * 項目名 id
	 */
	String id = "id";

	/**
	 * 項目名 group_for_thread
	 */
	String group_for_thread = "group_for_thread";

	/**
	 * 項目名 thread_for_comment
	 */
	String thread_for_comment = "thread_for_comment";

	/**
	 * 項目名 owner
	 */
	String owner = "owner";

	/**
	 * 項目名 create_time
	 */
	String create_time = "create_time";

	/**
	 * 参照先テーブル名 t_group
	 * 外部キー名 t_subscribe_group_for_thread_fkey
	 */
	String t_group_BY_t_subscribe_group_for_thread_fkey = "t_subscribe_group_for_thread_fkey";

	/**
	 * 参照先テーブル名 t_thread
	 * 外部キー名 t_subscribe_thread_for_comment_fkey
	 */
	String t_thread_BY_t_subscribe_thread_for_comment_fkey = "t_subscribe_thread_for_comment_fkey";

	/**
	 * 参照先テーブル名 t_user
	 * 外部キー名 t_subscribe_owner_fkey
	 */
	String t_user_BY_t_subscribe_owner_fkey = "t_subscribe_owner_fkey";

}
