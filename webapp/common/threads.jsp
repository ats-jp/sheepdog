<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" %>
<%@page import="java.util.List"%>
<%@ page import="jp.ats.substrate.*" %>
<%@ page import="jp.ats.sheepdog.*" %>
<%@ page import="jp.ats.sheepdog.common.*" %>
<%@ page import="jp.ats.sheepdog.common.Thread" %>

<%
boolean isEventType = ThreadAction.isEventType(request);
boolean isDateAvailable = ThreadAction.isDateAvailable(request);
%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<title><%=ThreadAction.createPageTitle(request)%></title>

<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="../css/reset.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/basic.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/sheepdog.css" media="screen" />
</head>
<body>

<% /* jspod{jp.ats.sheepdog.form.BlankForm} */ jp.ats.sheepdog.form.BlankForm jspod = (jp.ats.sheepdog.form.BlankForm) jp.ats.webkit.jspod.JspodManager.prepareJspod(jp.ats.sheepdog.form.BlankForm.class); %>
<form name="DeleteForm" action="threadDeleteComplete.jsp">
<input type="hidden" name="id" />
<input type="hidden" id="jspodToken" name="<%=jspod.tokenName()%>" value="<%=jspod.tokenValue()%>" />
</form>

<table class="sheepdogTable">
<tr>
<th nowrap="nowrap">ID</th>
<th nowrap="nowrap">タイトル</th>
<%
if (!isDateAvailable && isEventType) {
%>
<th nowrap="nowrap">日付</th>
<%
}
%>
<%
String userID = request.getParameter("userID");
//userIDがない場合、ログインユーザーの一覧になるので、オーナーは非表示
if (U.isAvailable(userID)) {
%>
<th nowrap="nowrap">オーナー</th>
<%
}
%>
<th nowrap="nowrap">公開レベル</th>
<th nowrap="nowrap">コメント数</th>
<th nowrap="nowrap">グループ</th>
<th nowrap="nowrap">操作</th>
<th nowrap="nowrap">登録時刻</th>
<th nowrap="nowrap">修正時刻</th>
</tr>
<%
SearchResult<Thread> result = ThreadAction.search(request);
for (Thread thread : result) {
	int id = thread.getID();
	String sealed = "";
	if (SealManager.isSealedThread(id)) sealed = " class=\"sealedThread\"";
%>
<tr<%=sealed%>>
<td nowrap="nowrap"><%=id%></td>
<td nowrap="nowrap"><a href="view.jsp?id=<%=id%>"><%=thread.getTitle()%></a></td>
<%
if (!isDateAvailable && isEventType) {
%>
<td nowrap="nowrap"><%=Utilities.convertDate(U.parseDate("yyyyMMdd", thread.getCalendar()))%></td>
<%
}
%>
<%
if (U.isAvailable(userID)) {
%>
<td nowrap="nowrap"><%=thread.getOwner().getName()%></td>
<%
}
%>
<td nowrap="nowrap"><%=thread.getApplyingLevel().name()%></td>
<td align="right" nowrap="nowrap"><%=thread.getCommentCount()%> 件</td>
<%
	if (thread.getGroupID() != null) {
		Group group = thread.getGroup();
		if (PermissionChecker.canAccess(group.getDTO())) {
			String dateParameter = thread.getEventFlag() ? "&date=" + thread.getCalendar() : "";
			
%>
<td nowrap="nowrap">
<a href="../group.jsp?id=<%=group.getID()%><%=dateParameter%>" target="_top"><%=group.getName()%></a>
</td>
<%
		} else {
%>
<td nowrap="nowrap"><%=thread.getGroup().getName()%></td>
<%
		}
%>
<%
	} else {
%>
<td></td>
<%
	}
%>
<td nowrap="nowrap">
<%
if (thread.canCange()) {
%>
<a class="functionalLink" href="thread.jsp?mode=update&id=<%=id%>">修正</a>
|
<button onclick="deleteThread('<%=id%>', '<%=thread.getTitle()%>');">削除</button>
<%
}
%>
</td>
<td nowrap="nowrap"><%=thread.getCreateTime()%></td>
<td nowrap="nowrap"><%=thread.getUpdateTime()%></td>
</tr>
<%
}
%>
</table>

<%
List<String> conditions = U.newLinkedList();

String userIDString = request.getParameter("userID");
if (U.isAvailable(userIDString)) {
	conditions.add("userID=" + userIDString);
}

String dateString = request.getParameter("date");
if (U.isAvailable(dateString)) {
	conditions.add("date=" + dateString);
}

String groupIDString = request.getParameter("groupID");
if (U.isAvailable(groupIDString)) {
	conditions.add("groupID=" + groupIDString);
}

result.getPager().setPager(
	request,
	"threads.jsp",
	U.join(conditions, "&"));
%>
<jsp:include page="../pager.jsp" flush="false" />

<script>
function deleteThread(id, name) {
	if (window.confirm("<%=Sheepdog.EVENT_NAME%> " + name + " を削除します。よろしいですか？")) {
		document.DeleteForm.id.value = id;
		document.DeleteForm.submit();
	}
}
</script>
</body>
</html>
