package jp.ats.sheepdog;

import static jp.ats.substrate.U.care;
import jp.ats.webkit.util.ExceptionForwardFilter;
import jp.ats.webkit.util.ServletUtilities;

@SuppressWarnings("serial")
public class ApplicationException extends RuntimeException {

	public ApplicationException(String message) {
		super(message);
	}

	public static String getExceptionMessage() {
		return care(ServletUtilities.getRootCause(
			ExceptionForwardFilter.getCurrentException()).getMessage());
	}
}
