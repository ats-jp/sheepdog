package jp.ats.sheepdog.common.subscribesql;

public interface SubscribeSQL {

	int insertNoticeForThread(int threadID, int groupID, int owner);

	int insertNoticeForComment(int commentID, int eventID, int owner);

	int insertSealForThread(int threadID, int groupID, int owner);

	int insertSealForComment(
		int commentID,
		int threadIDForCommentCondition,
		int ownerForComment,
		int threadIDForThread,
		int threadIDForThreadCondition,
		int ownerForEvent);
}
