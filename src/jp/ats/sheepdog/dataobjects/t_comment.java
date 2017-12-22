package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * 自動生成された定数定義インターフェイスです。
 *
 * パッケージ名 jp.ats.sheepdog.dataobjects
 * テーブル名 t_comment
 */
public interface t_comment {

	/**
	 * このインターフェイスのテーブルを指す {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("t_comment");

	/**
	 * 項目名 id
	 */
	String id = "id";

	/**
	 * 項目名 thread_id
	 */
	String thread_id = "thread_id";

	/**
	 * 項目名 comment_number
	 */
	String comment_number = "comment_number";

	/**
	 * 項目名 parent_id
	 */
	String parent_id = "parent_id";

	/**
	 * 項目名 owner
	 */
	String owner = "owner";

	/**
	 * 項目名 body
	 */
	String body = "body";

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
	 * 参照先テーブル名 v_comment_relationship
	 * 外部キー名 t_comment_id_fkey
	 */
	String v_comment_relationship_BY_t_comment_id_fkey = "t_comment_id_fkey";

	/**
	 * 参照先テーブル名 t_comment
	 * 外部キー名 t_comment_parent_id_fkey
	 */
	String t_comment_BY_t_comment_parent_id_fkey = "t_comment_parent_id_fkey";

	/**
	 * 参照先テーブル名 t_thread
	 * 外部キー名 t_comment_thread_id_fkey
	 */
	String t_thread_BY_t_comment_thread_id_fkey = "t_comment_thread_id_fkey";

	/**
	 * 参照先テーブル名 t_user
	 * 外部キー名 t_comment_owner_fkey
	 */
	String t_user_BY_t_comment_owner_fkey = "t_comment_owner_fkey";

}
