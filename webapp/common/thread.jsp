<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="jp.ats.sheepdog.common.Thread"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="jp.ats.substrate.*" %>
<%@ page import="jp.ats.sheepdog.*" %>
<%@ page import="jp.ats.sheepdog.common.*" %>
<%@ page import="jp.ats.sheepdog.dataobjects.*" %>
<%@ page import="jp.ats.sheepdog.dataobjects.t_content_attachDAO.*" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<% /* jspod{jp.ats.sheepdog.form.ThreadForm} */ jp.ats.sheepdog.form.ThreadForm jspod = (jp.ats.sheepdog.form.ThreadForm) jp.ats.webkit.jspod.JspodManager.prepareJspod(jp.ats.sheepdog.form.ThreadForm.class); %>

<%
String mode = request.getParameter("mode");
String modeCaption = Utilities.getModeCaption(mode);

int contentID = -1;
String date;
String groupID;
String id = request.getParameter("id");
boolean isPrivateThread = true;
if (Utilities.isUpdate(mode)) {
	Thread thread = ThreadAction.prepareForEdit(jspod, id);
	contentID = thread.getContentID();
	isPrivateThread = thread.getPrivateFlag() == 1;
	date = jspod.getDate();
	groupID = jspod.getGroupID();
} else {
	date = request.getParameter("date");
	groupID = request.getParameter("groupID");
}

boolean isEvent = U.isAvailable(date);

String threadType;

String title = "新規";
if (isEvent) {
	threadType = Sheepdog.EVENT_NAME;
	title += threadType + " (" + Utilities.convertMMdd(date) + ")";
} else {
	threadType = Sheepdog.TOPIC_NAME;
	title += threadType;
}
%>

<title><%=title%></title>

<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="../css/reset.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/basic.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/sheepdog.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/dexter.css" media="screen" />

<script src="../js/prototype.js" type="text/javascript"></script>
<script src="../js/dexter.js" type="text/javascript"></script>
<script src="../js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
jQuery.noConflict();
</script>
<script src="../js/jquery.MultiFile.js" type="text/javascript"></script>
<script src="../js/multifilestring.js" type="text/javascript"></script>
</head>
<body>

<form method="post" name="ThreadForm" id="ThreadForm" action="threadComplete.jsp" enctype="multipart/form-data">

<input type="hidden" name="mode" value="<%=mode%>" />
<input type="hidden" id="jspodToken" name="<%=jspod.tokenName()%>" value="<%=jspod.tokenValue()%>" />

<input type="hidden" name="<%= /* jspod.name{groupID} */ "groupID" %>" value="<%=groupID%>" />
<input type="hidden" name="<%= /* jspod.name{date} */ "date" %>" value="<%=U.care(date)%>" />

<input type="hidden" name="id" value="<%=U.care(id)%>" />

<table class="sheepdogTable">
<tr>
<th>タイトル</th>
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
<th>本文</th>
<td>
<textarea
	id="<%= /* jspod.name{body} */ "body" %>"
	name="<%= /* jspod.name{body} */ "body" %>"><%= /* jspod.value{body} */ jspod.getBodySafely() %></textarea>
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
int applyingLevelValue;
if (!U.isAvailable(applyingLevel)) {
	if (U.isAvailable(groupID)) {
		//新規登録の場合はデフォルトがグループの公開レベル
		applyingLevelValue = GroupAction.getApplyingLevel(groupID);
	} else {
		applyingLevelValue = Integer.MAX_VALUE;
	}
} else {
	applyingLevelValue = Integer.parseInt(applyingLevel);
}
%>
<option
	value="<%=ApplyingLevel.グループ外全てに公開.ordinal()%>"<%=Utilities.getSelectedString(applyingLevelValue, ApplyingLevel.グループ外全てに公開.ordinal())%>>
<%=ApplyingLevel.グループ外全てに公開.name()%>
</option>
<%
if (U.isAvailable(groupID) && (isPrivateThread || ApplyingLevel.values()[applyingLevelValue].canChangeTo(ApplyingLevel.グループメンバーのみ公開))) {
%>
<option
	value="<%=ApplyingLevel.グループメンバーのみ公開.ordinal()%>"<%=Utilities.getSelectedString(applyingLevelValue, ApplyingLevel.グループメンバーのみ公開.ordinal())%>>
	<%=ApplyingLevel.グループメンバーのみ公開.name()%>
</option>
<%
}

if (isPrivateThread || ApplyingLevel.values()[applyingLevelValue].canChangeTo(ApplyingLevel.非公開)) {
%>
<option
	value="<%=ApplyingLevel.非公開.ordinal()%>"<%=Utilities.getSelectedString(applyingLevelValue, ApplyingLevel.非公開.ordinal())%>>
<%=ApplyingLevel.非公開.name()%>
</option>
<%
}
%>
</select>
<div>
<%=!isPrivateThread ? "他のユーザーが使用しています。公開レベルを下げることはできません。" : ""%>
</div>
</td>
</tr>
<%
if (Utilities.isUpdate(mode)) {
%>
<tr>
<th>添付の解除</th>
<td>
<%
	t_content_attachIterator iterator = AttachedFileAction.selectAttachedFilesByContentID(contentID);
	for (t_content_attachDTO contentAttach : iterator) {
		t_attachDTO file = contentAttach.gett_attachByt_content_attach_attach_id_fkey();
%>
<input
	type="checkbox"
	name="<%= /* jspod.name{eject} */ "eject" %>"
	id="<%= /* jspod.name{eject} */ "eject" %>"
	value="<%=contentAttach.getid()%>" />
<a href="<%=request.getContextPath()%>/download.act?type=content&id=<%=contentAttach.getattach_id()%>">
<%=file.getname()%>
</a>
(<%=Utilities.addComma(file.getsize())%> byte)
<br />
<%
	}
%>
</td>
</tr>
<%
}
%>
<tr>
<th>新規添付ファイル</th>
<td>
<input name="file" type="file" class="multi" />
</td>
</tr>
</table>

<input type="button" id="submitButton" class="submitButton" value="<%=modeCaption%>" onclick="submitForm();" />

<script type="text/javascript">
function submitForm() {
	if (window.confirm("この内容で<%=modeCaption%>します。よろしいですか？")) {
		document.ThreadForm.submit();
	}
}

Event.observe(window, "load", function() {
	new Dexter("<%=request.getContextPath()%>" + "/dexter", $("ThreadForm"), $("submitButton")).start().changeMessagePositionToRight();
}, false);
</script>

</form>

</body>
</html>
