<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="jp.ats.sheepdog.common.Thread"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="jp.ats.substrate.*" %>
<%@ page import="jp.ats.sheepdog.*" %>
<%@ page import="jp.ats.sheepdog.common.*" %>
<%@ page import="jp.ats.sheepdog.form.*" %>
<%@ page import="jp.ats.sheepdog.dataobjects.*" %>
<%@ page import="jp.ats.sheepdog.dataobjects.t_content_attachDAO.*" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<%
Thread thread = ThreadAction.getThread(request.getParameter("id"));

boolean isEventType = thread.getEventFlag();

String title = isEventType ? thread.getTitle() + " (" + Utilities.convertMMdd(thread.getCalendar()) + ")" : thread.getTitle();
%>

<title><%=title%></title>

<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="../css/reset.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/basic.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/sheepdog.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/fwindow.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/minipopup.css" media="screen" />

<script src="../js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
jQuery.noConflict();
</script>
<script src="../js/jquery.MultiFile.js" type="text/javascript"></script>
<script src="../js/multifilestring.js" type="text/javascript"></script>
<script src="../js/jquery.fwindow.js" type="text/javascript"></script>
<script src="../js/jquery.minipopup.js" type="text/javascript"></script>
</head>
<body>
<% /* jspod{jp.ats.sheepdog.form.BlankForm} */ jp.ats.sheepdog.form.BlankForm jspod = (jp.ats.sheepdog.form.BlankForm) jp.ats.webkit.jspod.JspodManager.prepareJspod(jp.ats.sheepdog.form.BlankForm.class); %>
<form name="DeleteForm" action="threadDeleteComplete.jsp">
<input type="hidden" name="id" />
<input type="hidden" name="<%=jspod.tokenName()%>" value="<%=jspod.tokenValue()%>" />
</form>

<form name="SubscribeForm" action="common.SubscribeThread.do">
<input type="hidden" name="id" />
<input type="hidden" name="action" />
<input type="hidden" name="<%=jspod.tokenName()%>" value="<%=jspod.tokenValue()%>" />
</form>

<%
int threadID = thread.getID();

//自スレッドにコメントが入った場合は、スレッドを未読表示しないようにする
String sealedThread = SealManager.isSealedThread(threadID) && thread.getOwnerID() != UserManager.getUserID() ? " class=\"sealedThread\"" : "";

SubscribeAction.threadOpened(threadID);
%>

<div<%=sealedThread%>>
<h1><%=thread.getTitle()%></h1>
<%=Utilities.restore(thread.getBody())%>
</div>

<br />

<%
if (UserManager.getUserID() == thread.getOwner().getID()) {
%>
<a class="functionalLink" href="thread.jsp?mode=update&id=<%=threadID%>">この<%=isEventType ? Sheepdog.EVENT_NAME : Sheepdog.TOPIC_NAME%>を修正</a>
<%
}

if (thread.getContentCount() > 1) {
%>
<a class="functionalLink" href="contents.jsp?id=<%=thread.getID()%>">修正履歴を表示</a>
<%
}
%>
<table class="sheepdogTable">
<tr>
<th>公開レベル</th>
<td>
<%=thread.getApplyingLevel().name()%>
</td>
</tr>
<tr>
<th>添付ファイル</th>
<td>
<%
t_content_attachIterator eventAttachIterator = AttachedFileAction.selectAttachedFilesByContentID(thread.getContentID());
for (t_content_attachDTO contentAttach : eventAttachIterator) {
	t_attachDTO file = contentAttach.gett_attachByt_content_attach_attach_id_fkey();
%>
<a href="<%=request.getContextPath()%>/download.act?type=content&id=<%=contentAttach.getattach_id()%>">
<%=file.getname()%>
</a>
(<%=Utilities.addComma(file.getsize())%> byte)
<br />
<%
}
%>
</td>
</tr>
<%
	if (thread.getGroupID() != null) {
%>
<tr><th>グループ</th>
<%
		Group group = thread.getGroup();
		if (PermissionChecker.canAccess(group.getDTO())) {
			String dateParameter = thread.getEventFlag() ? "&date=" + thread.getCalendar() : "";
%>
<td>
<a href="../group.jsp?id=<%=group.getID()%><%=dateParameter%>" target="_top"><%=group.getName()%></a>
</td>
<%
		} else {
%>
<td><%=thread.getGroup().getName()%></td>
<%
		}
%>
</tr>
<%
	}
%>
<tr>
<th>登録時刻</th>
<td>
<%=thread.getCreateTime()%>
</td>
</tr>
<tr>
<th>修正時刻</th>
<td>
<%
if (thread.getContentCount() > 1) {
%>
<%=thread.getContentCreateTime()%>
<%
}
%>
</td>
</tr>
</table>
<%
if (UserManager.getUserID() == thread.getOwner().getID()) {
%>
<button onclick="deleteEvent('<%=threadID%>');">この<%=isEventType ? Sheepdog.EVENT_NAME : Sheepdog.TOPIC_NAME%>を削除</button>
<%
}

if (SubscribeAction.hasSubscribeOnThread(threadID)) {
%>
<button disabled="disabled">新着コメント通知を受け取る</button>
<button onclick="subscribe('<%=threadID%>', 'off', '新着コメント通知受け取りを中止します');">新着コメント通知受け取りを中止</button>
<%
} else {
%>
<button onclick="subscribe('<%=threadID%>', 'on', '新着コメント通知を受け取ります');">新着コメント通知を受け取る</button>
<button disabled="disabled">新着コメント通知受け取りを中止</button>
<%
}
%>

<script>
jQuery(document).ready(function() {
	jQuery(".fwindow").fwindow();
});

function deleteEvent(id) {
	if (window.confirm("この<%=isEventType ? Sheepdog.EVENT_NAME : Sheepdog.TOPIC_NAME%>を削除します。よろしいですか？")) {
		document.DeleteForm.id.value = id;
		document.DeleteForm.submit();
	}
}

function subscribe(id, action, message) {
	if (window.confirm(message + "。よろしいですか？")) {
		document.SubscribeForm.id.value = id;
		document.SubscribeForm.action.value = action;
		document.SubscribeForm.submit();
	}
}
</script>

<hr />

<form name="CommentForm" method="post" action="common.CommentRegist.do" enctype="multipart/form-data">
<input type="hidden" name="<%=jspod.tokenName()%>" value="<%=jspod.tokenValue()%>" />
<input type="hidden" name="id" value="<%=threadID%>" />
<input type="hidden" id="parentParameter" name="parent" value="" />
<a name="comment"></a>
<h2>コメント</h2>
<div id="parentNavigator">
<span id="parentInfomation"></span>
<a class="functionalLink" href="javascript:void(0);" id="parentClosure" onclick="closeParentNavigator();">[返信しない]</a>
</div>
<textarea name="comment">
</textarea>
<br />
添付ファイル
<br />
<input name="file" type="file" class="multi" />
<br />
<input type="button" value="コメント登録" onclick="submitForm();" />
</form>

<hr />

<%
SearchResult<Comment> result = CommentAction.search(request);
request.setAttribute("result", result);
request.setAttribute("jspod", jspod);
request.setAttribute("id", String.valueOf(threadID));
%>
<jsp:include page="innerComments.jsp" flush="false" />

<%
result.getPager().setPager(request, "view.jsp", "id=" + threadID);
%>
<jsp:include page="../pager.jsp" flush="false" />

<%@ include file="../fwindow.jsp" %>
</body>
</html>
