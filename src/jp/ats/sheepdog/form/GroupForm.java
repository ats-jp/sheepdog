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

public class GroupForm extends java.lang.Object implements Jspod {

	public static final String name = "name";

	public static final String description = "description";

	public static final String applyingLevel = "applyingLevel";

	public static final String owner = "owner";

	private final CollectionMap<String, String> values = new CollectionMap<String, String>() {

		@Override
		protected Collection<String> createNewCollection() {
			return newArrayList();
		}
	};

	private final JspodToken token;

	public GroupForm() {
		token = new JspodToken();
	}

	@SuppressWarnings("unchecked")
	public GroupForm(HttpServletRequest request) {
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

	public static GroupForm findInstance() {
		return (GroupForm) JspodManager.getJspod();
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

	public void override(GroupForm another) {
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

	public void setName(String value) {
		setName(0, value);
	}

	public void setName(int index, String value) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(name));
			while (list.size() <= index) {
				list.add(null);
			}
			list.set(index, value);
		}
	}

	public String getName() {
		return getName(0);
	}

	public String getName(int index) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(name));
			if (list.size() <= index) return null;
			return list.get(index);
		}
	}

	public String[] getNameArray() {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(name));
			return list.toArray(new String[list.size()]);
		}
	}

	public String getNameSafely() {
		return care(Sanitizer.sanitize(getName()));
	}

	public String getNameSafely(int index) {
		return care(Sanitizer.sanitize(getName(index)));
	}

	public String[] getNameArraySafely() {
		String[] array = getNameArray();
		String[] result = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = care(Sanitizer.sanitize(array[i]));
		}
		return result;
	}

	public void setDescription(String value) {
		setDescription(0, value);
	}

	public void setDescription(int index, String value) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(description));
			while (list.size() <= index) {
				list.add(null);
			}
			list.set(index, value);
		}
	}

	public String getDescription() {
		return getDescription(0);
	}

	public String getDescription(int index) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(description));
			if (list.size() <= index) return null;
			return list.get(index);
		}
	}

	public String[] getDescriptionArray() {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(description));
			return list.toArray(new String[list.size()]);
		}
	}

	public String getDescriptionSafely() {
		return care(Sanitizer.sanitize(getDescription()));
	}

	public String getDescriptionSafely(int index) {
		return care(Sanitizer.sanitize(getDescription(index)));
	}

	public String[] getDescriptionArraySafely() {
		String[] array = getDescriptionArray();
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

	public void setOwner(String value) {
		setOwner(0, value);
	}

	public void setOwner(int index, String value) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(owner));
			while (list.size() <= index) {
				list.add(null);
			}
			list.set(index, value);
		}
	}

	public String getOwner() {
		return getOwner(0);
	}

	public String getOwner(int index) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(owner));
			if (list.size() <= index) return null;
			return list.get(index);
		}
	}

	public String[] getOwnerArray() {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(owner));
			return list.toArray(new String[list.size()]);
		}
	}

	public String getOwnerSafely() {
		return care(Sanitizer.sanitize(getOwner()));
	}

	public String getOwnerSafely(int index) {
		return care(Sanitizer.sanitize(getOwner(index)));
	}

	public String[] getOwnerArraySafely() {
		String[] array = getOwnerArray();
		String[] result = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = care(Sanitizer.sanitize(array[i]));
		}
		return result;
	}

}
