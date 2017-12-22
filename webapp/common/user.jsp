<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="jp.ats.sheepdog.*" %>
<%@ page import="jp.ats.sheepdog.admin.*" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<title>ユーザー情報</title>

<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="../css/reset.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/basic.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/sheepdog.css" media="screen" />
</head>
<body>

<%
UserBean user = UserManager.getUser();
%>

<table class="sheepdogTable">
<tr>
<th nowrap="nowrap">ID</th>
<td nowrap="nowrap"><%=user.getID()%></td>
</tr>
<tr>
<th nowrap="nowrap">名前</th>
<td nowrap="nowrap"><%=user.getName()%></td>
</tr>
<tr>
<th nowrap="nowrap">メールアドレス</th>
<td nowrap="nowrap"><%=user.getMailAddress()%></td>
</tr>
<tr>
<th nowrap="nowrap">部署</th>
<td nowrap="nowrap"><%=user.getDepartment()%></td>
</tr>
<tr>
<th nowrap="nowrap">役職</th>
<td nowrap="nowrap"><%=user.getTitle()%></td>
</tr>
<tr>
<th nowrap="nowrap">権限</th>
<td nowrap="nowrap"><%=user.getRole().getName()%></td>
</tr>
<tr>
<th nowrap="nowrap">パスワード<br />有効期限</th>
<td nowrap="nowrap"><%=user.getExpirationDateString()%></td>
</tr>
<tr>
<th nowrap="nowrap">登録時刻</th>
<td nowrap="nowrap"><%=user.getCreateTimeString()%></td>
</tr>
<tr>
<th nowrap="nowrap">更新時刻</th>
<td nowrap="nowrap"><%=user.getUpdateTimeString()%></td>
</tr>
</table>
</body>
</html>
