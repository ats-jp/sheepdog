<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="jp.ats.sheepdog.*" %>
<%@ page import="jp.ats.sheepdog.admin.*" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<%
String mode = request.getParameter("mode");
String modeCaption = Utilities.getModeCaption(mode);
%>

<title>ユーザー<%=modeCaption%>の完了</title>

<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="../css/reset.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/basic.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/sheepdog.css" media="screen" />
</head>
<body>
<%
UserAction.store(request);
%>
<%=modeCaption%>が完了しました。

<br /><br />
<a class="functionalLink" href="users.jsp">ユーザー一覧へ戻る</a>
</body>
</html>
