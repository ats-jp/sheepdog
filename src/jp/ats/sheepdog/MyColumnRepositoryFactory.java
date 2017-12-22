package jp.ats.sheepdog;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

import jp.ats.liverwort.extension.SimpleColumnRepositoryFactory;
import jp.ats.liverwort.selector.AnchorOptimizer;

public class MyColumnRepositoryFactory extends SimpleColumnRepositoryFactory {

	static {
		URL url = MyColumnRepositoryFactory.class.getResource("/ColumnRepository");
		File repository;
		try {
			repository = new File(URLDecoder.decode(url.toString()
				.replaceFirst("^file:/", "")
				.replaceAll("\\\\", "/")
				.replaceFirst("/webapp/WEB-INF/classes/", "/src/"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new Error(e);
		}

		SimpleColumnRepositoryFactory.setRepositoryFile(repository);
		AnchorOptimizer.setAddsNewColumns(true);
	}
}
