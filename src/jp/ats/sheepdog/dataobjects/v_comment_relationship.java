package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * 自動生成された定数定義インターフェイスです。
 *
 * パッケージ名 jp.ats.sheepdog.dataobjects
 * テーブル名 v_comment_relationship
 */
public interface v_comment_relationship {

	/**
	 * このインターフェイスのテーブルを指す {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator(
		"v_comment_relationship");

	/**
	 * 項目名 comment_id
	 */
	String comment_id = "comment_id";

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
	 * 項目名 parent_number
	 */
	String parent_number = "parent_number";

	/**
	 * 項目名 attach_count
	 */
	String attach_count = "attach_count";

	/**
	 * 参照先テーブル名 t_thread
	 * 外部キー名 v_comment_relationship_thread_id_fkey
	 */
	String t_thread_BY_v_comment_relationship_thread_id_fkey = "v_comment_relationship_thread_id_fkey";

}
