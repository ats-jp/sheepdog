package build;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Properties;

import jp.ats.substrate.util.EncodablePropertiesFactory;
import jp.ats.substrate.util.PropertiesReplacer;

public class MyPropertiesReplacer {

	public static void main(String[] args) throws Exception {
		Reader reader = new InputStreamReader(
			new FileInputStream(args[0]),
			"UTF-8");
		Writer writer = new OutputStreamWriter(
			new FileOutputStream(args[1]),
			"UTF-8");
		Properties prop = EncodablePropertiesFactory.getInstance(new File(
			args[2]).toURI().toURL());

		PropertiesReplacer.replace(reader, writer, prop);
	}
}
