<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="jp.ats.sheepdog.*" %>
<%@ page import="jp.ats.sheepdog.admin.*" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<title>ユーザー一覧</title>

<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="../css/reset.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/basic.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/sheepdog.css" media="screen" />
</head>
<body>

<% /* jspod{jp.ats.sheepdog.form.BlankForm} */ jp.ats.sheepdog.form.BlankForm jspod = (jp.ats.sheepdog.form.BlankForm) jp.ats.webkit.jspod.JspodManager.prepareJspod(jp.ats.sheepdog.form.BlankForm.class); %>
<form name="DeleteForm" action="userDeleteComplete.jsp">
<input type="hidden" name="id" />
<input type="hidden" id="jspodToken" name="<%=jspod.tokenName()%>" value="<%=jspod.tokenValue()%>" />
</form>

<a class="functionalLink" href="user.jsp?mode=insert">新規ユーザーの登録</a>

<table class="sheepdogTable">
<tr>
<th nowrap="nowrap">ID</th>
<th nowrap="nowrap">名前</th>
<th nowrap="nowrap">メールアドレス</th>
<th nowrap="nowrap">部署</th>
<th nowrap="nowrap">役職</th>
<th nowrap="nowrap">権限</th>
<th nowrap="nowrap">パスワード<br />有効期限</th>
<th nowrap="nowrap">操作</th>
<th nowrap="nowrap">登録時刻</th>
<th nowrap="nowrap">更新時刻</th>
</tr>
<%
	SearchResult<User> result = UserAction.search(request);
for (User user : result) {
	int id = user.getID();
%>
<tr>
<td nowrap="nowrap"><%=id%></td>
<td nowrap="nowrap"><%=user.getName()%></td>
<td nowrap="nowrap"><%=user.getMailAddress()%></td>
<td nowrap="nowrap"><%=user.getDepartment()%></td>
<td nowrap="nowrap"><%=user.getTitle()%></td>
<td nowrap="nowrap"><%=user.getRole().getName()%></td>
<td nowrap="nowrap"><%=user.getExpirationDate()%></td>
<td nowrap="nowrap">
<a class="functionalLink" href="user.jsp?mode=update&id=<%=id%>">修正</a>
|
<a class="functionalLink" href="javascript:void(0);" onclick="deleteUser(<%=id%>, '<%=user.getName()%>');">削除</a>
</td>
<td nowrap="nowrap"><%=user.getCreateTime()%></td>
<td nowrap="nowrap"><%=user.getUpdateTime()%></td>
</tr>
<%
}
%>
</table>

<%
result.getPager().setPager(request, "users.jsp");
%>
<jsp:include page="../pager.jsp" flush="false" />

<script>
function deleteUser(id, name) {
	if (window.confirm("ユーザー " + name + " を削除します。よろしいですか？")) {
		document.DeleteForm.id.value = id;
		document.DeleteForm.submit();
	}
}
</script>
</body>
</html>
