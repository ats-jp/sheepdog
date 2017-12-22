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

public class PasswordForm extends java.lang.Object implements Jspod {

	public static final String password = "password";

	private final CollectionMap<String, String> values = new CollectionMap<String, String>() {

		@Override
		protected Collection<String> createNewCollection() {
			return newArrayList();
		}
	};

	private final JspodToken token;

	public PasswordForm() {
		token = new JspodToken();
	}

	@SuppressWarnings("unchecked")
	public PasswordForm(HttpServletRequest request) {
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

	public static PasswordForm findInstance() {
		return (PasswordForm) JspodManager.getJspod();
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

	public void override(PasswordForm another) {
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

	public void setPassword(String value) {
		setPassword(0, value);
	}

	public void setPassword(int index, String value) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(password));
			while (list.size() <= index) {
				list.add(null);
			}
			list.set(index, value);
		}
	}

	public String getPassword() {
		return getPassword(0);
	}

	public String getPassword(int index) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(password));
			if (list.size() <= index) return null;
			return list.get(index);
		}
	}

	public String[] getPasswordArray() {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(password));
			return list.toArray(new String[list.size()]);
		}
	}

	public String getPasswordSafely() {
		return care(Sanitizer.sanitize(getPassword()));
	}

	public String getPasswordSafely(int index) {
		return care(Sanitizer.sanitize(getPassword(index)));
	}

	public String[] getPasswordArraySafely() {
		String[] array = getPasswordArray();
		String[] result = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = care(Sanitizer.sanitize(array[i]));
		}
		return result;
	}

}
