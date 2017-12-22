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

public class UserForm extends java.lang.Object implements Jspod {

	public static final String mailAddress = "mailAddress";

	public static final String newMailAddress = "newMailAddress";

	public static final String name = "name";

	public static final String department = "department";

	public static final String title = "title";

	public static final String password = "password";

	public static final String role = "role";

	public static final String expirationDate = "expirationDate";

	private final CollectionMap<String, String> values = new CollectionMap<String, String>() {

		@Override
		protected Collection<String> createNewCollection() {
			return newArrayList();
		}
	};

	private final JspodToken token;

	public UserForm() {
		token = new JspodToken();
	}

	@SuppressWarnings("unchecked")
	public UserForm(HttpServletRequest request) {
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

	public static UserForm findInstance() {
		return (UserForm) JspodManager.getJspod();
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

	public void override(UserForm another) {
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

	public void setMailAddress(String value) {
		setMailAddress(0, value);
	}

	public void setMailAddress(int index, String value) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(mailAddress));
			while (list.size() <= index) {
				list.add(null);
			}
			list.set(index, value);
		}
	}

	public String getMailAddress() {
		return getMailAddress(0);
	}

	public String getMailAddress(int index) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(mailAddress));
			if (list.size() <= index) return null;
			return list.get(index);
		}
	}

	public String[] getMailAddressArray() {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(mailAddress));
			return list.toArray(new String[list.size()]);
		}
	}

	public String getMailAddressSafely() {
		return care(Sanitizer.sanitize(getMailAddress()));
	}

	public String getMailAddressSafely(int index) {
		return care(Sanitizer.sanitize(getMailAddress(index)));
	}

	public String[] getMailAddressArraySafely() {
		String[] array = getMailAddressArray();
		String[] result = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = care(Sanitizer.sanitize(array[i]));
		}
		return result;
	}

	public void setNewMailAddress(String value) {
		setNewMailAddress(0, value);
	}

	public void setNewMailAddress(int index, String value) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(newMailAddress));
			while (list.size() <= index) {
				list.add(null);
			}
			list.set(index, value);
		}
	}

	public String getNewMailAddress() {
		return getNewMailAddress(0);
	}

	public String getNewMailAddress(int index) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(newMailAddress));
			if (list.size() <= index) return null;
			return list.get(index);
		}
	}

	public String[] getNewMailAddressArray() {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(newMailAddress));
			return list.toArray(new String[list.size()]);
		}
	}

	public String getNewMailAddressSafely() {
		return care(Sanitizer.sanitize(getNewMailAddress()));
	}

	public String getNewMailAddressSafely(int index) {
		return care(Sanitizer.sanitize(getNewMailAddress(index)));
	}

	public String[] getNewMailAddressArraySafely() {
		String[] array = getNewMailAddressArray();
		String[] result = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = care(Sanitizer.sanitize(array[i]));
		}
		return result;
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

	public void setDepartment(String value) {
		setDepartment(0, value);
	}

	public void setDepartment(int index, String value) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(department));
			while (list.size() <= index) {
				list.add(null);
			}
			list.set(index, value);
		}
	}

	public String getDepartment() {
		return getDepartment(0);
	}

	public String getDepartment(int index) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(department));
			if (list.size() <= index) return null;
			return list.get(index);
		}
	}

	public String[] getDepartmentArray() {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(department));
			return list.toArray(new String[list.size()]);
		}
	}

	public String getDepartmentSafely() {
		return care(Sanitizer.sanitize(getDepartment()));
	}

	public String getDepartmentSafely(int index) {
		return care(Sanitizer.sanitize(getDepartment(index)));
	}

	public String[] getDepartmentArraySafely() {
		String[] array = getDepartmentArray();
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

	public void setRole(String value) {
		setRole(0, value);
	}

	public void setRole(int index, String value) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(role));
			while (list.size() <= index) {
				list.add(null);
			}
			list.set(index, value);
		}
	}

	public String getRole() {
		return getRole(0);
	}

	public String getRole(int index) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(role));
			if (list.size() <= index) return null;
			return list.get(index);
		}
	}

	public String[] getRoleArray() {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(role));
			return list.toArray(new String[list.size()]);
		}
	}

	public String getRoleSafely() {
		return care(Sanitizer.sanitize(getRole()));
	}

	public String getRoleSafely(int index) {
		return care(Sanitizer.sanitize(getRole(index)));
	}

	public String[] getRoleArraySafely() {
		String[] array = getRoleArray();
		String[] result = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = care(Sanitizer.sanitize(array[i]));
		}
		return result;
	}

	public void setExpirationDate(String value) {
		setExpirationDate(0, value);
	}

	public void setExpirationDate(int index, String value) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(expirationDate));
			while (list.size() <= index) {
				list.add(null);
			}
			list.set(index, value);
		}
	}

	public String getExpirationDate() {
		return getExpirationDate(0);
	}

	public String getExpirationDate(int index) {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(expirationDate));
			if (list.size() <= index) return null;
			return list.get(index);
		}
	}

	public String[] getExpirationDateArray() {
		synchronized (values) {
			ArrayList<String> list = ((ArrayList<String>) values.get(expirationDate));
			return list.toArray(new String[list.size()]);
		}
	}

	public String getExpirationDateSafely() {
		return care(Sanitizer.sanitize(getExpirationDate()));
	}

	public String getExpirationDateSafely(int index) {
		return care(Sanitizer.sanitize(getExpirationDate(index)));
	}

	public String[] getExpirationDateArraySafely() {
		String[] array = getExpirationDateArray();
		String[] result = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = care(Sanitizer.sanitize(array[i]));
		}
		return result;
	}

}
