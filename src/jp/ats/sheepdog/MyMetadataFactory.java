package jp.ats.sheepdog;

import java.io.IOException;

import jp.ats.liverwort.extension.SingleMetadataFactory;

public class MyMetadataFactory extends SingleMetadataFactory {

	static {
		SingleMetadataFactory.setResource(MyMetadataFactory.class);
	}

	public MyMetadataFactory() throws IOException, ClassNotFoundException {}
}
