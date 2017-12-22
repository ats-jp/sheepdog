package jp.ats.sheepdog.form;

import static jp.ats.substrate.U.newArrayList;

import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import jp.ats.substrate.util.CollectionMap;
import jp.ats.webkit.jspod.Jspod;
import jp.ats.webkit.jspod.JspodManager;
import jp.ats.webkit.jspod.JspodToken;

public class BlankForm extends java.lang.Object implements Jspod {

	private final CollectionMap<String, String> values = new CollectionMap<String, String>() {

		@Override
		protected Collection<String> createNewCollection() {
			return newArrayList();
		}
	};

	private final JspodToken token;

	public BlankForm() {
		token = new JspodToken();
	}

	@SuppressWarnings("unchecked")
	public BlankForm(HttpServletRequest request) {
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

	public static BlankForm findInstance() {
		return (BlankForm) JspodManager.getJspod();
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

	public void override(BlankForm another) {
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

}
