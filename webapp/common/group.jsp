<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="jp.ats.sheepdog.*" %>
<%@ page import="jp.ats.sheepdog.common.*" %>
<%@ page import="jp.ats.sheepdog.dataobjects.*" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<%
String mode = request.getParameter("mode");
String modeCaption = Utilities.getModeCaption(mode);
%>

<title>グループ<%=modeCaption%></title>

<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="../css/reset.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/basic.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/sheepdog.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/dexter.css" media="screen" />

<script src="../js/prototype.js" type="text/javascript"></script>
<script src="../js/dexter.js" type="text/javascript"></script>
</head>
<body>

<form method="post" name="GroupForm" id="GroupForm" action="groupComplete.jsp">

<% /* jspod{jp.ats.sheepdog.form.GroupForm} */ jp.ats.sheepdog.form.GroupForm jspod = (jp.ats.sheepdog.form.GroupForm) jp.ats.webkit.jspod.JspodManager.prepareJspod(jp.ats.sheepdog.form.GroupForm.class); %>

<%
boolean isPrivateGroup = true;
if (Utilities.isUpdate(mode)) {
	t_groupDTO dto = GroupAction.prepareForEdit(jspod, request.getParameter("id"));
	isPrivateGroup = dto.getprivate_flag() == 1;
}
%>

<input type="hidden" name="mode" value="<%=mode%>" />
<input type="hidden" id="jspodToken" name="<%=jspod.tokenName()%>" value="<%=jspod.tokenValue()%>" />

<table class="sheepdogTable">
<tr>
<th>グループ名</th>
<td>
<input
	type="text"
	id="<%= /* jspod.name{name} */ "name" %>"
	name="<%= /* jspod.name{name} */ "name" %>"
	value="<%= /* jspod.value{name} */ jspod.getNameSafely() %>"
	autocomplete="off" />
</td>
</tr>
<tr>
<th>説明</th>
<td>
<textarea
	id="<%= /* jspod.name{description} */ "description" %>"
	name="<%= /* jspod.name{description} */ "description" %>"><%= /* jspod.value{description} */ jspod.getDescriptionSafely() %></textarea>
</td>
</tr>
<tr>
<th>公開レベル</th>
<td>
<select
	id="<%= /* jspod.name{applyingLevel} */ "applyingLevel" %>"
	name="<%= /* jspod.name{applyingLevel} */ "applyingLevel" %>">
<option value="">以下から選択してください</option>
<%
	String applyingLevel = jspod.getApplyingLevel();
	for (ApplyingLevel level : ApplyingLevel.values()) {
		if (!isPrivateGroup && !ApplyingLevel.values()[Integer.parseInt(applyingLevel)].canChangeTo(level)) continue;

		String selected = applyingLevel != null && Integer.parseInt(applyingLevel) == level.ordinal() ? " selected=\"selected\"" : "";
%>
<option value="<%=level.ordinal()%>"<%=selected%>><%=level.name()%></option>
<%
	}
%>
</select>
<div>
<%=!isPrivateGroup ? "他のユーザーが使用しています。公開レベルを下げることはできません。" : ""%>
</div>
</td>
</tr>
<%
if (Utilities.isUpdate(mode) && !UserManager.isUserInRole(Role.USER)) {
%>
<tr>
<th>オーナー</th>
<td><%= /* jspod.value{owner} */ jspod.getOwnerSafely() %></td>
</tr>
<%
}
%>
</table>

<input type="button" id="submitButton" class="submitButton" value="<%=modeCaption%>" onclick="submitForm();" />

<script type="text/javascript">
function submitForm() {
	if (window.confirm("この内容で<%=modeCaption%>します。よろしいですか？")) {
		document.GroupForm.submit();
	}
}

Event.observe(window, "load", function() {
	new Dexter("<%=request.getContextPath()%>" + "/dexter", $("GroupForm"), $("submitButton")).start().changeMessagePositionToRight();
}, false);
</script>

</form>

</body>
</html>
