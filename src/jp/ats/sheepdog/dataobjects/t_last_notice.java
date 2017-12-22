package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * 自動生成された定数定義インターフェイスです。
 *
 * パッケージ名 jp.ats.sheepdog.dataobjects
 * テーブル名 t_last_notice
 */
public interface t_last_notice {

	/**
	 * このインターフェイスのテーブルを指す {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("t_last_notice");

	/**
	 * 項目名 owner
	 */
	String owner = "owner";

	/**
	 * 項目名 last_notice
	 */
	String last_notice = "last_notice";

	/**
	 * 項目名 create_time
	 */
	String create_time = "create_time";

	/**
	 * 参照先テーブル名 t_user
	 * 外部キー名 t_last_notice_owner_fkey
	 */
	String t_user_BY_t_last_notice_owner_fkey = "t_last_notice_owner_fkey";

}
