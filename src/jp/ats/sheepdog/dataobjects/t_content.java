package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * 自動生成された定数定義インターフェイスです。
 *
 * パッケージ名 jp.ats.sheepdog.dataobjects
 * テーブル名 t_content
 */
public interface t_content {

	/**
	 * このインターフェイスのテーブルを指す {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("t_content");

	/**
	 * 項目名 id
	 */
	String id = "id";

	/**
	 * 項目名 thread_id
	 */
	String thread_id = "thread_id";

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
	 * 項目名 create_time
	 */
	String create_time = "create_time";

	/**
	 * 参照先テーブル名 t_thread
	 * 外部キー名 t_content_thread_id_fkey
	 */
	String t_thread_BY_t_content_thread_id_fkey = "t_content_thread_id_fkey";

	/**
	 * 参照先テーブル名 t_user
	 * 外部キー名 t_content_owner_fkey
	 */
	String t_user_BY_t_content_owner_fkey = "t_content_owner_fkey";

}
