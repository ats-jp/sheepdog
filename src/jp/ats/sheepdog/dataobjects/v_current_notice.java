package jp.ats.sheepdog.dataobjects;

import jp.ats.liverwort.jdbc.ResourceLocator;

/**
 * 自動生成された定数定義インターフェイスです。
 *
 * パッケージ名 jp.ats.sheepdog.dataobjects
 * テーブル名 v_current_notice
 */
public interface v_current_notice {

	/**
	 * このインターフェイスのテーブルを指す {@link ResourceLocator}
	 */
	ResourceLocator RESOURCE_LOCATOR = new ResourceLocator("v_current_notice");

	/**
	 * 項目名 owner
	 */
	String owner = "owner";

	/**
	 * 項目名 max_id
	 */
	String max_id = "max_id";

	/**
	 * 項目名 last_id
	 */
	String last_id = "last_id";

}
