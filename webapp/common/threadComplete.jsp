<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="jp.ats.substrate.*" %>
<%@ page import="jp.ats.sheepdog.*" %>
<%@ page import="jp.ats.sheepdog.common.*" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<%
String mode = request.getParameter("mode");
String modeCaption = Utilities.getModeCaption(mode);

String name = U.isAvailable(request.getParameter("date")) ? Sheepdog.EVENT_NAME : Sheepdog.TOPIC_NAME;
%>

<title><%=name%><%=modeCaption%>の完了</title>

<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="../css/reset.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/basic.css" media="screen" />
</head>
<body>
<%
ThreadAction.store(request);
%>
<%=modeCaption%>が完了しました。

<%
String id = request.getParameter("id");
if (U.isAvailable(id)) {
%>
<br /><br />
<a href="view.jsp?id=<%=id%>"><%=name%>に戻る</a>
<%
}
%>
</body>
</html>
