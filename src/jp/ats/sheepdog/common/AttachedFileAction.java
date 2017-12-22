package jp.ats.sheepdog.common;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import jp.ats.liverwort.selector.AnchorOptimizer;
import jp.ats.liverwort.sql.Condition;
import jp.ats.liverwort.sql.ConditionFactory;
import jp.ats.liverwort.sql.OrderByClause;
import jp.ats.liverwort.sql.binder.IntBinder;
import jp.ats.sheepdog.NewIDInserter;
import jp.ats.sheepdog.dataobjects.t_attach;
import jp.ats.sheepdog.dataobjects.t_attachDTO;
import jp.ats.sheepdog.dataobjects.t_comment_attach;
import jp.ats.sheepdog.dataobjects.t_comment_attachDAO;
import jp.ats.sheepdog.dataobjects.t_comment_attachDAO.t_comment_attachIterator;
import jp.ats.sheepdog.dataobjects.t_comment_attachDTO;
import jp.ats.sheepdog.dataobjects.t_content_attach;
import jp.ats.sheepdog.dataobjects.t_content_attachDAO;
import jp.ats.sheepdog.dataobjects.t_content_attachDAO.t_content_attachIterator;
import jp.ats.sheepdog.dataobjects.t_content_attachDTO;
import jp.ats.substrate.U;
import jp.ats.webkit.util.FileUploadFilter;

import org.apache.commons.fileupload.FileItem;

public class AttachedFileAction {

	private static final Pattern fileNamePattern = Pattern.compile("[^\\\\/]+$");

	public static t_content_attachIterator selectAttachedFilesByContentID(
		String contentID) {
		return selectAttachedFilesByContentID(Integer.parseInt(contentID));
	}

	private static final AnchorOptimizer contentOptimizer = AnchorOptimizer.getInstance();

	private static final AnchorOptimizer commentOptimizer = AnchorOptimizer.getInstance();

	public static t_content_attachIterator selectAttachedFilesByContentID(
		int contentID) {
		Condition condition = ConditionFactory.createCondition(
			t_content_attach.content_id,
			new IntBinder(contentID));

		OrderByClause order = new OrderByClause();
		order.asc(t_content_attach.id);

		return new t_content_attachDAO().select(
			contentOptimizer,
			condition,
			order);
	}

	public static t_comment_attachIterator selectAttachedFilesByCommentID(
		int commentID) {
		Condition condition = ConditionFactory.createCondition(
			t_comment_attach.comment_id,
			new IntBinder(commentID));

		OrderByClause order = new OrderByClause();
		order.asc(t_comment_attach.attach_id);

		return new t_comment_attachDAO().select(
			commentOptimizer,
			condition,
			order);
	}

	static void insertContentAttachedFiles(
		HttpServletRequest request,
		int contentID) {
		FileItem[] files = FileUploadFilter.getFileItems("file");

		t_content_attachDAO dao = new t_content_attachDAO();
		for (FileItem file : files) {
			if (file.getSize() == 0) continue;

			t_content_attachDTO dto = new t_content_attachDTO();
			dto.setattach_id(insertAttach(request, file));
			dto.setcontent_id(contentID);

			dao.insert(dto);
		}
	}

	static void insertCommentAttachedFiles(
		HttpServletRequest request,
		int commentID) {
		FileItem[] files = FileUploadFilter.getFileItems("file");

		t_comment_attachDAO dao = new t_comment_attachDAO();
		for (FileItem file : files) {
			if (file.getSize() == 0) continue;

			t_comment_attachDTO dto = new t_comment_attachDTO();
			dto.setattach_id(insertAttach(request, file));
			dto.setcomment_id(commentID);

			dao.insert(dto);
		}
	}

	static void changeEvent(int oldContentID, int newContentID, String[] ejects) {
		Condition condition = ConditionFactory.createCondition(
			t_content_attach.content_id,
			new IntBinder(oldContentID));

		OrderByClause order = new OrderByClause();
		order.asc(t_content_attach.id);

		t_content_attachDAO dao = new t_content_attachDAO();

		t_content_attachIterator iterator = new t_content_attachDAO().select(
			condition,
			order);

		Set<String> ejectsSet = U.newHashSet();
		ejectsSet.addAll(Arrays.asList(ejects));

		for (t_content_attachDTO dto : iterator) {
			if (ejectsSet.contains(dto.getid().toString())) continue;

			dto.setcontent_id(newContentID);
			dto.setcreate_time(new Timestamp(System.currentTimeMillis()));
			dto.getDataObject().setAllValuesForcibly();

			dao.insert(dto);
		}
	}

	private static int insertAttach(HttpServletRequest request, FileItem file) {
		t_attachDTO dto = new t_attachDTO();

		String name = getFileName(file.getName());

		dto.setname(name);

		AttachedFileManager manager = new AttachedFileManager(new File(
			request.getSession()
				.getServletContext()
				.getInitParameter(AttachedFileManager.FILE_REPOSITORY_KEY)));

		try {
			dto.setpath(manager.regist(name, file.getInputStream()));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

		dto.setsize((int) file.getSize());

		return (int) NewIDInserter.insert(t_attach.RESOURCE_LOCATOR, dto);
	}

	//OSが違ってもファイル名を取得するためのメソッド
	private static String getFileName(String base) {
		Matcher matcher = fileNamePattern.matcher(base);
		matcher.find();
		return matcher.group();
	}
}
