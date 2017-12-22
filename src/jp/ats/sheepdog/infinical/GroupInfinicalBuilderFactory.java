package jp.ats.sheepdog.infinical;

import javax.servlet.http.HttpServletRequest;

import jp.ats.webkit.infinical.InfinicalBuilder;
import jp.ats.webkit.infinical.InfinicalBuilderFactory;

public class GroupInfinicalBuilderFactory implements InfinicalBuilderFactory {

	@Override
	public InfinicalBuilder createBuilder(HttpServletRequest request) {
		return new GroupInfinicalBuilder(request);
	}
}
