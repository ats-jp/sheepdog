package jp.ats.sheepdog;

public class Sheepdog {

	public static final String SYSTEM_NAME = " - ats; グループウェア基盤システム";

	public static final String EVENT_NAME = "イベント";

	public static final String TOPIC_NAME = "トピック";

	public static final int MEMBER_CALENDAR_HEIGHT = 400;

	public static final int USER_CALENDAR_HEIGHT = 100;

	public static final int CALENDAR_WIDTH = 800;

	public static final int DEFAULT_PAGE_COUNT = 20;

	public static int getCalendarHeight(boolean isMemberCalnendar) {
		return isMemberCalnendar
			? MEMBER_CALENDAR_HEIGHT
			: USER_CALENDAR_HEIGHT;
	}
}
