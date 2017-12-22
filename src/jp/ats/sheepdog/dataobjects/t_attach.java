package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * 自動生成された定数定義インターフェイスです。
 *
 * パッケージ名 jp.ats.sheepdog.dataobjects
 * テーブル名 t_attach
 */
public interface t_attach {

	/**
	 * このインターフェイスのテーブルを指す {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("t_attach");

	/**
	 * 項目名 id
	 */
	String id = "id";

	/**
	 * 項目名 name
	 */
	String name = "name";

	/**
	 * 項目名 size
	 */
	String size = "size";

	/**
	 * 項目名 path
	 */
	String path = "path";

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

}
