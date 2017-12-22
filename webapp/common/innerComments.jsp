<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="jp.ats.substrate.*" %>
<%@ page import="jp.ats.sheepdog.*" %>
<%@ page import="jp.ats.sheepdog.form.*" %>
<%@ page import="jp.ats.sheepdog.common.*" %>
<%@ page import="jp.ats.sheepdog.dataobjects.*" %>
<%@ page import="jp.ats.sheepdog.dataobjects.t_comment_attachDAO.*" %>

<%
BlankForm blankForm = (BlankForm) request.getAttribute("jspod");
boolean viewMode = request.getAttribute("id") == null;
%>

<form name="CommentDeleteForm" method="post" action="common.CommentDelete.do">
<input type="hidden" name="<%=blankForm.tokenName()%>" value="<%=blankForm.tokenValue()%>" />
<input type="hidden" id="deleteTargetComment" name="id" />
</form>

<script type="text/javascript">
function submitForm() {
	if (window.confirm("コメントを登録します。よろしいですか？")) {
		document.CommentForm.submit();
	}
}

function deleteComment(id) {
	if (window.confirm("このコメントを削除します。よろしいですか？")) {
		jQuery("#deleteTargetComment").val(id);
		document.CommentDeleteForm.submit();
	}
}
</script>

<%
List<Integer> readedComment = U.newLinkedList();
SearchResult<Comment> result = Utilities.castResult(request.getAttribute("result"));
for (Comment comment : result) {
	if (comment.isDeleted()) {
%>
<div class="deletedComment">
<%
		if (!viewMode) {
%>
<%=comment.getNumber()%> :
このコメントは <%=comment.getUpdateTime()%> に削除されました
<%
		} else {
			request.setAttribute("currentComment", comment);
%>
<jsp:include page="viewModeCommentTitle.jsp" flush="false" /> は <%=comment.getUpdateTime()%> に削除されました
<%
		}
%>
</div>
<hr />
<%
		continue;
	}

	int commentID = comment.getID();
	readedComment.add(commentID);
	String sealedComment = SealManager.isSealedComment(commentID) ? " class=\"sealedComment\"" : "";
%>
<div<%=sealedComment%>>
<div>
<%
	if (!viewMode) {
%>
<%=comment.getNumber()%> :
<a class="fwindow" href="<%=request.getContextPath()%>/common/user.jsp?id=<%=comment.getOwner().getID()%>"><%=comment.getOwner().getName()%></a>
<%
	} else {
		request.setAttribute("currentComment", comment);
%>
<jsp:include page="viewModeCommentTitle.jsp" flush="false" /> :
<%
	}
%>

<span class="commentTimestamp"><%=comment.getCreateTime()%></span>

<%
	if (comment.canDelete()) {
%>
<button class="commentDeleteButton" onclick="deleteComment(<%=comment.getID()%>);">削除</button>
<%
	}
%>
</div>
<%
	if (comment.hasParent()) {
%>
<div class="replyTo">
<a
	class="parentPopup"
	id="commentParent_<%=comment.getNumber()%>_<%=comment.getParentID()%>"
	href="javascript:void(0);"><span class="replyNumber"><%=comment.getParentNumber()%></span> への返信
</a>
</div>
<%
	}
%>
<div>
<%=Utilities.restore(comment.getBody())%>
</div>
<div>
<%
	if (comment.getAttachCount() > 0) {
		t_comment_attachIterator commentAttachIterator = AttachedFileAction.selectAttachedFilesByCommentID(comment.getID());
		for (t_comment_attachDTO commentAttach : commentAttachIterator) {
			t_attachDTO file = commentAttach.gett_attachByt_comment_attach_attach_id_fkey();
%>
<a href="<%=request.getContextPath()%>/download.act?type=comment&id=<%=commentAttach.getattach_id()%>">
<%=file.getname()%>
</a>
(<%=Utilities.addComma(file.getsize())%> byte)
<br />
<%
		}
	}
%>
</div>
<%
	if (!viewMode) {
%>
<div class="replyLink">
<a
	class="functionalLink"
	href="javascript:void(0);"
	onclick="response(<%=comment.getNumber()%>, <%=comment.getID()%>);">このコメントに返信</a>
</div>
<%
	}
%>
</div>

<hr />
<%
}

SubscribeAction.commentOpened(readedComment.toArray(new Integer[readedComment.size()]));
%>
<script>
function response(number, id) {
	location.href = "#comment";
	jQuery("#parentParameter").val(id);
	jQuery("#parentInfomation").text(number + " に返信");
	jQuery("#parentNavigator").show();
}

function closeParentNavigator() {
	jQuery("#parentParameter").val("");
	jQuery("#parentInfomation").text("");
	jQuery("#parentNavigator").hide();
}

jQuery(document).ready(function() {
	jQuery("#parentNavigator").hide();

	jQuery(".parentPopup").minipopup(
		function(event) {
			var target = jQuery(event.target);
			if (!target.attr("id")) {
				target = target.parent();
			}
			target.attr("id").match(/_(\d+)$/);
			return "<%=request.getContextPath()%>/parent.act?id=" + RegExp.$1;
		},
		"<img src=\"<%=request.getContextPath()%>/img/indicator.gif\" />");
});
</script>
