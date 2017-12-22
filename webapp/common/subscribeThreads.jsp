<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="jp.ats.sheepdog.*" %>
<%@ page import="jp.ats.sheepdog.common.*" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<%
SearchResult<Subscribe> result = SubscribeAction.searchComment(request);
%>
<title>新着コメント通知対象一覧</title>

<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="../css/reset.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/basic.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/sheepdog.css" media="screen" />
</head>
<body>

<table class="sheepdogTable">
<tr>
<th nowrap="nowrap">ID</th>
<th nowrap="nowrap">タイトル</th>
<th nowrap="nowrap">オーナー</th>
<th nowrap="nowrap">公開レベル</th>
<th nowrap="nowrap">グループ</th>
<th nowrap="nowrap">操作</th>
<th nowrap="nowrap">登録時刻</th>
<th nowrap="nowrap">修正時刻</th>
</tr>
<%
for (Subscribe subscribe : result) {
	RawThread thread = subscribe.getThreadForComment();
	int id = thread.getID();
%>
<tr>
<td nowrap="nowrap"><%=id%></td>
<td nowrap="nowrap"><a href="view.jsp?id=<%=id%>"><%=thread.getTitle()%></a></td>
<td nowrap="nowrap"><%=thread.getOwner().getName()%></td>
<td nowrap="nowrap"><%=thread.getApplyingLevel().name()%></td>
<%
	if (thread.getGroupID() != null) {
		Group group = thread.getGroup();
		if (PermissionChecker.canAccess(group.getDTO())) {
			
%>
<td nowrap="nowrap">
<a href="../group.jsp?id=<%=group.getID()%>&date=<%=thread.getCalendar()%>" target="_top"><%=group.getName()%></a>
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
<button onclick="deleteSubscribe('<%=id%>');">通知受け取りを中止</button>
</td>
<td nowrap="nowrap"><%=thread.getCreateTime()%></td>
<td nowrap="nowrap"><%=thread.getUpdateTime()%></td>
</tr>
<%
}
%>
</table>

<% /* jspod{jp.ats.sheepdog.form.BlankForm} */ jp.ats.sheepdog.form.BlankForm jspod = (jp.ats.sheepdog.form.BlankForm) jp.ats.webkit.jspod.JspodManager.prepareJspod(jp.ats.sheepdog.form.BlankForm.class); %>
<form name="DeleteForm" action="common.SubscribeThread.do">
<input type="hidden" name="id" />
<input type="hidden" name="action" value="off" />
<input type="hidden" name="<%=jspod.tokenName()%>" value="<%=jspod.tokenValue()%>" />
</form>

<script>
function deleteSubscribe(id) {
	if (window.confirm("通知受け取りを中止します。よろしいですか？")) {
		document.DeleteForm.id.value = id;
		document.DeleteForm.submit();
	}
}
</script>

<%
result.getPager().setPager(request, "subscribeThreads.jsp");
%>
<jsp:include page="../pager.jsp" flush="false" />
</body>
</html>
