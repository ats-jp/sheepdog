<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="jp.ats.sheepdog.*" %>
<%@ page import="jp.ats.sheepdog.admin.*" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<title>パスワードの更新</title>

<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="../css/reset.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/basic.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/sheepdog.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/dexter.css" media="screen" />

<script src="../js/prototype.js" type="text/javascript"></script>
<script src="../js/dexter.js" type="text/javascript"></script>
</head>
<body>

<form method="post" name="PasswordForm" id="PasswordForm" action="passwordComplete.jsp">

<% /* jspod{jp.ats.sheepdog.form.PasswordForm} */ jp.ats.sheepdog.form.PasswordForm jspod = (jp.ats.sheepdog.form.PasswordForm) jp.ats.webkit.jspod.JspodManager.prepareJspod(jp.ats.sheepdog.form.PasswordForm.class); %>

<%
UserAction.prepareForPasswordChange();
%>

<input type="hidden" id="jspodToken" name="<%=jspod.tokenName()%>" value="<%=jspod.tokenValue()%>" />

<table class="sheepdogTable">
<tr>
<th>パスワード</th>
<td>
<div class="validationMessageBottomCell">
<input
	type="password"
	id="<%= /* jspod.name{password} */ "password" %>"
	name="<%= /* jspod.name{password} */ "password" %>"
	class="validationMessageBottomElement"
	autocomplete="off" />
</div>
</td>
</tr>
<tr>
<th>確認用パスワード</th>
<td>
<input type="password" id="repassword" name="repassword" autocomplete="off" />
</td>
</tr>
</table>

<input type="button" id="submitButton" class="submitButton" value="更新" onclick="submitForm();" />

<script type="text/javascript">
function submitForm() {
	if ($("password") && $("password").value != $("repassword").value) {
		alert("パスワードと確認用パスワードが違います。");
		return;
	}

	if (window.confirm("この内容で更新します。よろしいですか？")) {
		document.PasswordForm.submit();
	}
}

Event.observe(window, "load", function() {
	new Dexter("<%=request.getContextPath()%>" + "/dexter", $("PasswordForm"), $("submitButton")).start();
}, false);
</script>

</form>

</body>
</html>
