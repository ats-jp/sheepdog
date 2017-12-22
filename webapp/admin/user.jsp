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

<title>ユーザー<%=modeCaption%></title>

<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="../css/reset.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/basic.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/sheepdog.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/dexter.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/wheecal.css" media="screen" />

<script src="../js/prototype.js" type="text/javascript"></script>
<script src="../js/dexter.js" type="text/javascript"></script>
<script src="../js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
jQuery.noConflict();
</script>
<script src="../js/jquery.mousewheel.js" type="text/javascript"></script>
<script src="../js/jquery.wheecal.js" type="text/javascript"></script>
</head>
<body>

<form method="post" name="UserForm" id="UserForm" action="<%=Utilities.isUpdate(mode) ? "userUpdate.jsp" : "userInsert.jsp"%>">

<% /* jspod{jp.ats.sheepdog.form.UserForm} */ jp.ats.sheepdog.form.UserForm jspod = (jp.ats.sheepdog.form.UserForm) jp.ats.webkit.jspod.JspodManager.prepareJspod(jp.ats.sheepdog.form.UserForm.class); %>

<%
String id = request.getParameter("id");

if (Utilities.isUpdate(mode)) {
	UserAction.prepareForEdit(jspod, id);
}

String[] dateString = Utilities.splitDateString(jspod.getExpirationDate());
%>
<script>
jQuery(document).ready(function() {
	jQuery.wheecal.setHolidayServletPath("<%=request.getContextPath()%>" + "/holiday");
	jQuery("#expirationDateDiv").wheecal(
		jQuery("#expirationDate"),
		"<%=dateString[0]%>",
		"<%=dateString[1]%>",
		"<%=dateString[2]%>");
});
</script>

<input type="hidden" name="mode" value="<%=mode%>" />
<input type="hidden" id="jspodToken" name="<%=jspod.tokenName()%>" value="<%=jspod.tokenValue()%>" />

<table class="sheepdogTable">
<tr>
<th><%=Utilities.isUpdate(mode) ? "現在の" : ""%>メールアドレス</th>
<td>
<%
if (Utilities.isUpdate(mode)) {
%>
<%= /* jspod.value{mailAddress} */ jspod.getMailAddressSafely() %>
<%
} else {
%>
<div class="validationMessageBottomCell">
<input
	type="text"
	id="<%= /* jspod.name{mailAddress} */ "mailAddress" %>"
	name="<%= /* jspod.name{mailAddress} */ "mailAddress" %>"
	value="<%= /* jspod.value{mailAddress} */ jspod.getMailAddressSafely() %>"
	class="validationMessageBottomElement"
	autocomplete="off" />
</div>
<%
}
%>
</td>
</tr>
<%
if (Utilities.isUpdate(mode)) {
%>
<tr>
<th>新メールアドレス</th>
<td>
<div class="validationMessageBottomCell">
<input
	type="text"
	id="<%= /* jspod.name{newMailAddress} */ "newMailAddress" %>"
	name="<%= /* jspod.name{newMailAddress} */ "newMailAddress" %>"
	class="validationMessageBottomElement"
	autocomplete="off" />
</div>
</td>
</tr>
<tr>
<td colspan="2">(未入力の場合、メールアドレスは更新されません)</td>
</tr>
<%
}
%>
<tr>
<th>名前</th>
<td>
<div class="validationMessageBottomCell">
<input
	type="text"
	id="<%= /* jspod.name{name} */ "name" %>"
	name="<%= /* jspod.name{name} */ "name" %>"
	value="<%= /* jspod.value{name} */ jspod.getNameSafely() %>"
	class="validationMessageBottomElement"
	autocomplete="off" />
</div>
</td>
</tr>
<tr>
<th>部署</th>
<td>
<input
	type="text"
	id="<%= /* jspod.name{department} */ "department" %>"
	name="<%= /* jspod.name{department} */ "department" %>"
	value="<%= /* jspod.value{department} */ jspod.getDepartmentSafely() %>"
	autocomplete="off" />
</td>
</tr>
<tr>
<th>役職</th>
<td>
<input
	type="text"
	id="<%= /* jspod.name{title} */ "title" %>"
	name="<%= /* jspod.name{title} */ "title" %>"
	value="<%= /* jspod.value{title} */ jspod.getTitleSafely() %>"
	autocomplete="off" />
</td>
</tr>
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
<%=Utilities.isUpdate(mode) ? "</tr><td colspan=\"2\">(未入力の場合、パスワードは更新されません)</td></tr>" : ""%>
<tr>
<th>確認用パスワード</th>
<td>
<input type="password" id="repassword" name="repassword" autocomplete="off" />
</td>
</tr>
<tr>
<th>パスワードの有効期限</th>
<td>
<div id="expirationDateDiv"></div>
<input
	type="hidden"
	id="<%= /* jspod.name{expirationDate} */ "expirationDate" %>"
	name="<%= /* jspod.name{expirationDate} */ "expirationDate" %>"
	value="<%= /* jspod.value{expirationDate} */ jspod.getExpirationDateSafely() %>" />
</td>
</tr>
<tr>
<th>権限</th>
<td>
<div class="validationMessageBottomCell">
<select
	id="<%= /* jspod.name{role} */ "role" %>"
	name="<%= /* jspod.name{role} */ "role" %>"
	class="validationMessageBottomElement">
<option value="">以下から選択してください</option>
<%
for (Role role : Role.values()) {
	String roleString = jspod.getRole();
	String selected = roleString != null && Integer.parseInt(roleString) == role.ordinal() ? " selected=\"selected\"" : "";
%>
<option value="<%=role.ordinal()%>"<%=selected%>><%=role.getName()%></option>
<%
}
%>
</select>
</div>
</td>
</tr>
</table>

<input type="button" id="submitButton" class="submitButton" value="<%=modeCaption%>" onclick="submitForm();" />

<script type="text/javascript">
function submitForm() {
	if ($("password") && $("password").value != $("repassword").value) {
		alert("パスワードと確認用パスワードが違います。");
		return;
	}

	if (window.confirm("この内容で<%=modeCaption%>します。よろしいですか？")) {
		document.UserForm.submit();
	}
}

Event.observe(window, "load", function() {
	new Dexter("<%=request.getContextPath()%>" + "/dexter", $("UserForm"), $("submitButton")).start();
}, false);
</script>

</form>

</body>
</html>
