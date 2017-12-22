package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * 自動生成された定数定義インターフェイスです。
 *
 * パッケージ名 jp.ats.sheepdog.dataobjects
 * テーブル名 t_seal
 */
public interface t_seal {

	/**
	 * このインターフェイスのテーブルを指す {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("t_seal");

	/**
	 * 項目名 id
	 */
	String id = "id";

	/**
	 * 項目名 owner
	 */
	String owner = "owner";

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
	 * 外部キー名 t_seal_comment_id_fkey
	 */
	String t_comment_BY_t_seal_comment_id_fkey = "t_seal_comment_id_fkey";

	/**
	 * 参照先テーブル名 t_thread
	 * 外部キー名 t_seal_thread_id_fkey
	 */
	String t_thread_BY_t_seal_thread_id_fkey = "t_seal_thread_id_fkey";

	/**
	 * 参照先テーブル名 t_user
	 * 外部キー名 t_seal_owner_fkey
	 */
	String t_user_BY_t_seal_owner_fkey = "t_seal_owner_fkey";

}
