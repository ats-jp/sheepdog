package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * 自動生成された定数定義インターフェイスです。
 *
 * パッケージ名 jp.ats.sheepdog.dataobjects
 * テーブル名 t_notice
 */
public interface t_notice {

	/**
	 * このインターフェイスのテーブルを指す {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("t_notice");

	/**
	 * 項目名 id
	 */
	String id = "id";

	/**
	 * 項目名 owner
	 */
	String owner = "owner";

	/**
	 * 項目名 group_id
	 */
	String group_id = "group_id";

	/**
	 * 項目名 thread_id
	 */
	String thread_id = "thread_id";

	/**
	 * 項目名 comment_id
	 */
	String comment_id = "comment_id";

	/**
	 * 項目名 create_time
	 */
	String create_time = "create_time";

	/**
	 * 参照先テーブル名 t_comment
	 * 外部キー名 t_notice_comment_id_fkey
	 */
	String t_comment_BY_t_notice_comment_id_fkey = "t_notice_comment_id_fkey";

	/**
	 * 参照先テーブル名 t_group
	 * 外部キー名 t_notice_group_id_fkey
	 */
	String t_group_BY_t_notice_group_id_fkey = "t_notice_group_id_fkey";

	/**
	 * 参照先テーブル名 t_thread
	 * 外部キー名 t_notice_thread_id_fkey
	 */
	String t_thread_BY_t_notice_thread_id_fkey = "t_notice_thread_id_fkey";

	/**
	 * 参照先テーブル名 t_user
	 * 外部キー名 t_notice_owner_fkey
	 */
	String t_user_BY_t_notice_owner_fkey = "t_notice_owner_fkey";

}
