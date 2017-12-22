package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * 自動生成された定数定義インターフェイスです。
 *
 * パッケージ名 jp.ats.sheepdog.dataobjects
 * テーブル名 t_comment_attach
 */
public interface t_comment_attach {

	/**
	 * このインターフェイスのテーブルを指す {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("t_comment_attach");

	/**
	 * 項目名 attach_id
	 */
	String attach_id = "attach_id";

	/**
	 * 項目名 comment_id
	 */
	String comment_id = "comment_id";

	/**
	 * 項目名 create_time
	 */
	String create_time = "create_time";

	/**
	 * 参照先テーブル名 t_attach
	 * 外部キー名 t_comment_attach_attach_id_fkey
	 */
	String t_attach_BY_t_comment_attach_attach_id_fkey = "t_comment_attach_attach_id_fkey";

	/**
	 * 参照先テーブル名 t_comment
	 * 外部キー名 t_comment_attach_comment_id_fkey
	 */
	String t_comment_BY_t_comment_attach_comment_id_fkey = "t_comment_attach_comment_id_fkey";

}
