package jp.ats.sheepdog.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.ats.substrate.U;

public class AttachedFileManager {

	public static final String FILE_REPOSITORY_KEY = "file-repository";

	private final File repository;

	private static final AtomicLong counter = new AtomicLong(0);

	private static final Pattern extPattern = Pattern.compile("\\.[^\\.]+$");

	public AttachedFileManager(File repository) {
		this.repository = repository;
	}

	public String regist(String fileName, InputStream input) throws IOException {
		File dir = getDirectory();

		String nameBase = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
			+ "-"
			+ counter.incrementAndGet();

		Matcher matcher = extPattern.matcher(fileName);

		String ext = "";
		if (matcher.find()) {
			ext = matcher.group();
		}

		File file = new File(dir, nameBase + ext);

		BufferedOutputStream output = new BufferedOutputStream(
			new FileOutputStream(file));
		U.sendBytes(input, output);
		output.close();

		return shrinkPath(file);
	}

	private String shrinkPath(File file) {
		return file.getAbsolutePath()
			.substring(repository.getAbsolutePath().length())
			.replace(File.separatorChar, '/');
	}

	private File getDirectory() {
		Date now = new Date();
		File directory = new File(repository.getAbsolutePath()
			+ "/"
			+ new SimpleDateFormat("yyyy").format(now)
			+ "/"
			+ new SimpleDateFormat("MMdd").format(now));

		if (!directory.exists()) directory.mkdirs();

		return directory;
	}
}
