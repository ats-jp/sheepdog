package jp.ats.sheepdog.common;

public enum ApplyingLevel {

	グループ外全てに公開,

	グループメンバーのみ公開,

	非公開;

	public boolean canChangeTo(ApplyingLevel to) {
		return ordinal() - to.ordinal() >= 0;
	}
}
