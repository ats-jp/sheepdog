<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" %>
<html>
<head>
<title>login</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="css/reset.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/basic.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/sheepdog.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/sheepdogmain.css" media="screen" />
</head>
<body>

<form method="post" name="LoginForm" action="j_security_check">
<table class="nowrapTable">
<tr>
<td>ユーザーID</td><td>: <input type="text" name="j_username" style="width: 150px;" /></td>
</tr>
<tr>
<td>パスワード</td><td>: <input type="password" name="j_password" style="width: 150px;" /></td>
</tr>
</table>

<input type="submit" value="login" />
</form>

<script type="text/javascript">
document.LoginForm.j_username.focus();
</script>
</body>
</html>
