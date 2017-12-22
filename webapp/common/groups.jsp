<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="jp.ats.sheepdog.*" %>
<%@ page import="jp.ats.sheepdog.common.*" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<title>グループ一覧</title>

<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="../css/reset.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/basic.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/sheepdog.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/fwindow.css" media="screen" />
</head>
<body>

<% /* jspod{jp.ats.sheepdog.form.BlankForm} */ jp.ats.sheepdog.form.BlankForm jspod = (jp.ats.sheepdog.form.BlankForm) jp.ats.webkit.jspod.JspodManager.prepareJspod(jp.ats.sheepdog.form.BlankForm.class); %>
<form name="DeleteForm" action="groupDeleteComplete.jsp">
<input type="hidden" name="id" />
<input type="hidden" id="jspodToken" name="<%=jspod.tokenName()%>" value="<%=jspod.tokenValue()%>" />
</form>

<a class="functionalLink" href="group.jsp?mode=insert">新規グループの登録</a>

<table class="sheepdogTable">
<tr>
<th nowrap="nowrap">ID</th>
<th nowrap="nowrap">グループ名</th>
<th nowrap="nowrap">概要</th>
<th nowrap="nowrap">オーナー</th>
<th nowrap="nowrap">公開レベル</th>
<th nowrap="nowrap">操作</th>
<th nowrap="nowrap">登録時刻</th>
<th nowrap="nowrap">更新時刻</th>
</tr>
<%
SearchResult<Group> result = GroupAction.searchAll(request);
for (Group group : result) {
	int id = group.getID();
%>
<tr>
<td><%=id%></td>
<td nowrap="nowrap">
<a href="../group.jsp?id=<%=group.getID()%>" target="_top"><%=group.getName()%></a>
</td>
<td nowrap="nowrap"><%=group.getOmitDescription(30)%></td>
<td nowrap="nowrap"><%=group.getOwner().getName()%></td>
<td nowrap="nowrap"><%=group.getApplyingLevel().name()%></td>
<td nowrap="nowrap">
<%
if (group.canChange()) {
%>
<a class="functionalLink" href="members.jsp?id=<%=id%>">グループメンバーの変更</a>
|
<a class="functionalLink" href="group.jsp?mode=update&id=<%=id%>">修正</a>
|
<button onclick="deleteGroup(<%=id%>, '<%=group.getName()%>');">削除</button>
<%
}
%>
</td>
<td nowrap="nowrap"><%=group.getCreateTime()%></td>
<td nowrap="nowrap"><%=group.getUpdateTime()%></td>
</tr>
<%
}
%>
</table>

<%
result.getPager().setPager(request, "groups.jsp");
%>
<jsp:include page="../pager.jsp" flush="false" />

<script>
function deleteGroup(id, name) {
	if (window.confirm("グループ " + name + " を削除します。よろしいですか？")) {
		document.DeleteForm.id.value = id;
		document.DeleteForm.submit();
	}
}
</script>
</body>
</html>
