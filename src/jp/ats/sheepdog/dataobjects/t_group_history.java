package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * 自動生成された定数定義インターフェイスです。
 *
 * パッケージ名 jp.ats.sheepdog.dataobjects
 * テーブル名 t_group_history
 */
public interface t_group_history {

	/**
	 * このインターフェイスのテーブルを指す {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("t_group_history");

	/**
	 * 項目名 owner
	 */
	String owner = "owner";

	/**
	 * 項目名 group_id
	 */
	String group_id = "group_id";

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
	 * 外部キー名 t_group_history_group_id_fkey
	 */
	String t_group_BY_t_group_history_group_id_fkey = "t_group_history_group_id_fkey";

	/**
	 * 参照先テーブル名 t_user
	 * 外部キー名 t_group_history_owner_fkey
	 */
	String t_user_BY_t_group_history_owner_fkey = "t_group_history_owner_fkey";

}
