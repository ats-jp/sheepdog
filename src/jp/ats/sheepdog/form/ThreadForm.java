package jp.ats.sheepdog.form;

import static jp.ats.substrate.U.newArrayList;
import static jp.ats.substrate.U.care;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import jp.ats.substrate.util.CollectionMap;
import jp.ats.webkit.jspod.Jspod;
import jp.ats.webkit.jspod.JspodManager;
import jp.ats.webkit.jspod.JspodToken;
import jp.ats.webkit.util.Sanitizer;

public class ThreadForm extends java.lang.Object implements Jspod {

	public static final String groupID = "groupID";

	public static final String date = "date";

	public static final String title = "title";

	public static final String body = "body";

	public static final String applyingLevel = "applyingLevel";

	public static final String eject = "eject";

	private final CollectionMap<String, String> values = new CollectionMap<String, String>() {

		@Override
		protected Collection<String> createNewCollection() {
			return newArrayList();
		}
	};

	private final JspodToken token;

	public ThreadForm() {
		token = new JspodToken();
	}

	@SuppressWarnings("unchecked")
	public ThreadForm(HttpServletRequest request) {
		token = JspodManager.getToken();
		Enumeration<String> keys = request.getParameterNames();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			String[] values = request.getParameterValues(key);
			for (String value : values) {
				this.values.put(key, value);
			}
		}
	}

	public static ThreadForm findInstance() {
		return (ThreadForm) JspodManager.getJspod();
	}

	@Override
	public JspodToken token() {
		return token;
	}

	public String tokenName() {
		return JspodManager.getTokenName();
	}

	public String tokenValue() {
		return token.toString();
	}

	public void override(ThreadForm another) {
		CollectionMap<String, String> anotherMap;
		synchronized (another.values) {
			anotherMap = another.values.clone();
		}

		synchronized (values) {
			for (String key : anotherMap.keySet()) {
				for (String value : anotherMap.get(key)) {
					values.put(key, value);
				}
			}
		}
	}

	public void setGroupID(String value) {
		setGroupID(0, value);
	}

	public void setGroupID(int index, String value) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(groupID));
			while (list.size() <= index) {
				list.add(null);
			}
			list.set(index, value);
		}
	}

	public String getGroupID() {
		return getGroupID(0);
	}

	public String getGroupID(int index) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(groupID));
			if (list.size() <= index) return null;
			return list.get(index);
		}
	}

	public String[] getGroupIDArray() {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(groupID));
			return list.toArray(new String[list.size()]);
		}
	}

	public String getGroupIDSafely() {
		return care(Sanitizer.sanitize(getGroupID()));
	}

	public String getGroupIDSafely(int index) {
		return care(Sanitizer.sanitize(getGroupID(index)));
	}

	public String[] getGroupIDArraySafely() {
		String[] array = getGroupIDArray();
		String[] result = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = care(Sanitizer.sanitize(array[i]));
		}
		return result;
	}

	public void setDate(String value) {
		setDate(0, value);
	}

	public void setDate(int index, String value) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(date));
			while (list.size() <= index) {
				list.add(null);
			}
			list.set(index, value);
		}
	}

	public String getDate() {
		return getDate(0);
	}

	public String getDate(int index) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(date));
			if (list.size() <= index) return null;
			return list.get(index);
		}
	}

	public String[] getDateArray() {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(date));
			return list.toArray(new String[list.size()]);
		}
	}

	public String getDateSafely() {
		return care(Sanitizer.sanitize(getDate()));
	}

	public String getDateSafely(int index) {
		return care(Sanitizer.sanitize(getDate(index)));
	}

	public String[] getDateArraySafely() {
		String[] array = getDateArray();
		String[] result = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = care(Sanitizer.sanitize(array[i]));
		}
		return result;
	}

	public void setTitle(String value) {
		setTitle(0, value);
	}

	public void setTitle(int index, String value) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(title));
			while (list.size() <= index) {
				list.add(null);
			}
			list.set(index, value);
		}
	}

	public String getTitle() {
		return getTitle(0);
	}

	public String getTitle(int index) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(title));
			if (list.size() <= index) return null;
			return list.get(index);
		}
	}

	public String[] getTitleArray() {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(title));
			return list.toArray(new String[list.size()]);
		}
	}

	public String getTitleSafely() {
		return care(Sanitizer.sanitize(getTitle()));
	}

	public String getTitleSafely(int index) {
		return care(Sanitizer.sanitize(getTitle(index)));
	}

	public String[] getTitleArraySafely() {
		String[] array = getTitleArray();
		String[] result = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = care(Sanitizer.sanitize(array[i]));
		}
		return result;
	}

	public void setBody(String value) {
		setBody(0, value);
	}

	public void setBody(int index, String value) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(body));
			while (list.size() <= index) {
				list.add(null);
			}
			list.set(index, value);
		}
	}

	public String getBody() {
		return getBody(0);
	}

	public String getBody(int index) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(body));
			if (list.size() <= index) return null;
			return list.get(index);
		}
	}

	public String[] getBodyArray() {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(body));
			return list.toArray(new String[list.size()]);
		}
	}

	public String getBodySafely() {
		return care(Sanitizer.sanitize(getBody()));
	}

	public String getBodySafely(int index) {
		return care(Sanitizer.sanitize(getBody(index)));
	}

	public String[] getBodyArraySafely() {
		String[] array = getBodyArray();
		String[] result = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = care(Sanitizer.sanitize(array[i]));
		}
		return result;
	}

	public void setApplyingLevel(String value) {
		setApplyingLevel(0, value);
	}

	public void setApplyingLevel(int index, String value) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(applyingLevel));
			while (list.size() <= index) {
				list.add(null);
			}
			list.set(index, value);
		}
	}

	public String getApplyingLevel() {
		return getApplyingLevel(0);
	}

	public String getApplyingLevel(int index) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(applyingLevel));
			if (list.size() <= index) return null;
			return list.get(index);
		}
	}

	public String[] getApplyingLevelArray() {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(applyingLevel));
			return list.toArray(new String[list.size()]);
		}
	}

	public String getApplyingLevelSafely() {
		return care(Sanitizer.sanitize(getApplyingLevel()));
	}

	public String getApplyingLevelSafely(int index) {
		return care(Sanitizer.sanitize(getApplyingLevel(index)));
	}

	public String[] getApplyingLevelArraySafely() {
		String[] array = getApplyingLevelArray();
		String[] result = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = care(Sanitizer.sanitize(array[i]));
		}
		return result;
	}

	public void setEject(String value) {
		setEject(0, value);
	}

	public void setEject(int index, String value) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(eject));
			while (list.size() <= index) {
				list.add(null);
			}
			list.set(index, value);
		}
	}

	public String getEject() {
		return getEject(0);
	}

	public String getEject(int index) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(eject));
			if (list.size() <= index) return null;
			return list.get(index);
		}
	}

	public String[] getEjectArray() {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(eject));
			return list.toArray(new String[list.size()]);
		}
	}

	public String getEjectSafely() {
		return care(Sanitizer.sanitize(getEject()));
	}

	public String getEjectSafely(int index) {
		return care(Sanitizer.sanitize(getEject(index)));
	}

	public String[] getEjectArraySafely() {
		String[] array = getEjectArray();
		String[] result = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = care(Sanitizer.sanitize(array[i]));
		}
		return result;
	}

}
