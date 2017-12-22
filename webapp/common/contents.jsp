<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="jp.ats.sheepdog.*" %>
<%@ page import="jp.ats.sheepdog.common.*" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<title><%=Sheepdog.EVENT_NAME%>修正履歴</title>

<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="../css/reset.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/basic.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/sheepdog.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/fwindow.css" media="screen" />
</head>
<body>

<table class="sheepdogTable">
<tr>
<th>ID</th>
<th>タイトル</th>
<th>修正者</th>
<th>修正時刻</th>
</tr>
<%
SearchResult<Content> result = ThreadAction.searchContent(request);
for (Content content : result) {
	int id = content.getID();
%>
<tr>
<td><%=id%></td>
<td><a href="content.jsp?id=<%=id%>"><%=content.getTitle()%></a></td>
<td><%=content.getOwner().getName()%></td>
<td><%=content.getCreateTime()%></td>
</tr>
<%
}
%>
</table>

<%
result.getPager().setPager(request, "contents.jsp", "id=" + request.getParameter("id"));
%>
<jsp:include page="../pager.jsp" flush="false" />
</body>
</html>
