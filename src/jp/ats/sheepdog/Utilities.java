package jp.ats.sheepdog;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Date;

import jp.ats.substrate.U;

public class Utilities {

	private static final String[] emptyStringArray = { "", "", "" };

	public static String convertDate(Date date) {
		if (date == null) return "";
		return U.formatDate("yyyy/MM/dd", date);
	}

	public static String convertTimestamp(Date date) {
		if (date == null) return "";
		return U.formatDate("yyyy/MM/dd HH:mm:ss", date);
	}

	public static String getModeCaption(String mode) {
		if (mode.equals("update")) return "çXêV";
		if (mode.equals("insert")) return "ìoò^";
		throw new IllegalStateException();
	}

	public static boolean isUpdate(String mode) {
		if (mode.equals("update")) return true;
		return false;
	}

	public static String[] splitDateString(String date) {
		if (!U.isAvailable(date)) return emptyStringArray;
		return date.split("/");
	}

	public static String addComma(Number number) {
		if (number == null) return "";
		return new DecimalFormat("#,##0").format(number);
	}

	public static String getSelectedString(int id, int another) {
		return id == another ? " selected=\"selected\"" : "";
	}

	public static String restore(String value) {
		return U.care(value)
			.replaceAll(" ", "&nbsp;")
			.replaceAll("\\r\\n|[\\r\\n]", "<br />\r\n");
	}

	public static String convertMMdd(String yyyyMMdd) {
		return Integer.parseInt(yyyyMMdd.substring(4, 6))
			+ "/"
			+ Integer.parseInt(yyyyMMdd.substring(6, 8));
	}

	public static String convertJSDateParameter(String yyyyMMdd) {
		if (!U.isAvailable(yyyyMMdd)) return "";
		return yyyyMMdd.substring(0, 4)
			+ ", "
			+ (Integer.parseInt(yyyyMMdd.substring(4, 6)) - 1)
			+ ", "
			+ yyyyMMdd.substring(6, 8);
	}

	public static String createOmitString(String value, int size) {
		if (!U.isAvailable(value)) return "";

		if (getBytes(value).length < size) return value;

		StringBuilder builder = new StringBuilder();
		int total = 0;
		for (char c : value.toCharArray()) {
			total += getBytes(String.valueOf(c)).length;

			if (total > size) break;

			builder.append(c);
		}
		return builder.toString() + "Åc";
	}

	@SuppressWarnings("unchecked")
	public static <T> SearchResult<T> castResult(Object target) {
		return (SearchResult<T>) target;
	}

	private static byte[] getBytes(String value) {
		try {
			return value.getBytes("Windows-31J");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}
}
