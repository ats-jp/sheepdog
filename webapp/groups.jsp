<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="jp.ats.sheepdog.*" %>
<%@ page import="jp.ats.sheepdog.common.*" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<%
String type = request.getParameter("type");
String typeString;

SearchResult<Group> result;
if (type.equals("all")) {
	result = GroupAction.searchAll(request);
	typeString = "全ての";
} else if (type.equals("member")) {
	result = GroupAction.searchMemberGroups(request);
	typeString = "自分がメンバーの";
} else {
	result = GroupAction.searchMyGroups(request);
	typeString = "自分がオーナーの";
}
%>
<title><%=typeString%>グループ一覧</title>

<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="css/reset.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/basic.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/sheepdog.css" media="screen" />
</head>
<body>

<dl>
<%
for (Group group : result) {
	int id = group.getID();
%>
<dt><a class="blockLink" href="group.jsp?id=<%=id%>" target="_top"><%=group.getName()%></a></dt>
<dd><%=Utilities.restore(group.getDescription())%></dd>
<%
}
%>
</dl>

<%
result.getPager().setPager(request, "groups.jsp", "type=" + type);
%>
<jsp:include page="pager.jsp" flush="false" />
</body>
</html>
