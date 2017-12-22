package jp.ats.sheepdog.infinical;

import java.util.Calendar;

import jp.ats.sheepdog.Sheepdog;
import jp.ats.sheepdog.Utilities;
import jp.ats.sheepdog.common.SealManager;
import jp.ats.sheepdog.dataobjects.t_threadDTO;
import jp.ats.substrate.U;
import jp.ats.webkit.infinical.ConcreteBody;
import jp.ats.webkit.util.Sanitizer;

public class SheepdogBody extends ConcreteBody {

	private static final String CELL_CLASS_NAME = "bodyContent ";

	public SheepdogBody(String cellClassName, String body) {
		super(CELL_CLASS_NAME + cellClassName, body);
	}

	static String buildUserBody(
		int currentUserID,
		int userID,
		String groupID,
		Calendar calendar,
		t_threadDTO[] events) {
		String addLink = currentUserID == userID
			? "<a class=\"functionalLink fwindow\" href=\"common/thread.jsp?mode=insert&groupID="
				+ groupID
				+ "&date="
				+ U.formatDate("yyyyMMdd", calendar.getTime())
				+ "\">"
				+ Sheepdog.EVENT_NAME
				+ "ÇÃìoò^</a>"
			: "";

		StringBuilder builder = new StringBuilder();
		int counter = 0;
		String sealedThreadsOfVIewAllClass = "";
		for (t_threadDTO dto : events) {
			counter++;

			boolean sealed = false;
			if (SealManager.isSealedThread(dto.getid())) {
				sealedThreadsOfVIewAllClass = " sealedThread";
				sealed = true;
			}

			if (counter >= 3) continue;

			String sealedClass = "";
			if (sealed) sealedClass = " sealedThread";

			builder.append("<a class=\"blockLink fwindow"
				+ sealedClass
				+ "\" href=\"common/view.jsp?id="
				+ dto.getid()
				+ "\">"
				+ Sanitizer.sanitize(Utilities.createOmitString(
					dto.getlast_title(),
					16))
				+ "</a>");
		}

		String viewLink = "<a class=\"blockLink functionalLink fwindow"
			+ sealedThreadsOfVIewAllClass
			+ "\" href=\"common/threads.jsp?userID="
			+ userID
			+ "&groupID="
			+ groupID
			+ "&date="
			+ U.formatDate("yyyyMMdd", calendar.getTime())
			+ "\">ëSÇƒï\é¶ ("
			+ counter
			+ "åè)</a>";

		return (builder + (counter == 0 ? "" : viewLink) + addLink).trim();
	}
}
