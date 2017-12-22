package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * 自動生成された定数定義インターフェイスです。
 *
 * パッケージ名 jp.ats.sheepdog.dataobjects
 * テーブル名 t_member
 */
public interface t_member {

	/**
	 * このインターフェイスのテーブルを指す {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("t_member");

	/**
	 * 項目名 id
	 */
	String id = "id";

	/**
	 * 項目名 user_id
	 */
	String user_id = "user_id";

	/**
	 * 項目名 group_id
	 */
	String group_id = "group_id";

	/**
	 * 項目名 create_time
	 */
	String create_time = "create_time";

	/**
	 * 参照先テーブル名 t_group
	 * 外部キー名 t_member_group_id_fkey
	 */
	String t_group_BY_t_member_group_id_fkey = "t_member_group_id_fkey";

	/**
	 * 参照先テーブル名 t_user
	 * 外部キー名 t_member_user_id_fkey
	 */
	String t_user_BY_t_member_user_id_fkey = "t_member_user_id_fkey";

}
