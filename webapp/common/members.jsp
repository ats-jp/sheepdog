<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="jp.ats.liverwort.extension.*" %>
<%@ page import="jp.ats.sheepdog.*" %>
<%@ page import="jp.ats.sheepdog.common.*" %>
<%@ page import="jp.ats.sheepdog.common.MemberAction.MemberFinder" %>
<%@ page import="jp.ats.sheepdog.admin.*" %>
<%@ page import="jp.ats.sheepdog.dataobjects.*" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<title>グループメンバーの変更</title>

<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="../css/reset.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/basic.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/sheepdog.css" media="screen" />

<script src="../js/jquery.js" type="text/javascript"></script>
</head>
<body>

<%
String groupID = request.getParameter("id");
%>
<table class="sheepdogTable">
<tr>
<th>ID</th>
<th>名前</th>
<th>部署</th>
<th>役職</th>
<th>操作</th>
</tr>
<%
	String disabledString = " disabled=\"disabled\"";
	SearchResult<User> result = UserAction.search(request);
	MemberFinder finder = new MemberFinder(MemberAction.search(null, groupID));
for (User user : result) {
	int id = user.getID();
	String className = "";
	boolean isMember = finder.find(id);
	if (isMember) {
		className = "groupMember";
	} else {
		//自身がUSER権限の場合、メンバーではない上位権限のものは表示させず、追加できないようにする
		if (UserManager.isUserInRole(Role.USER) && !user.getRole().equals(Role.USER)) continue;
	}

	String userName = user.getName();
%>
<tr id="user<%=id%>" class="<%=className%>">
<td><%=id%></td>
<td><%=userName%></td>
<td><%=user.getDepartment()%></td>
<td><%=user.getTitle()%></td>
<td>
<button id="addButton<%=id%>" onclick="addMember(<%=id%>, <%=groupID%>, '<%=userName%>');"<%=isMember ? disabledString : ""%>>追加</button>
<button id="removeButton<%=id%>" onclick="removeMember(<%=id%>, <%=groupID%>, '<%=userName%>');"<%=isMember ? "" : disabledString%>>削除</button>
</td>
</tr>
<%
}
%>
</table>

<%
result.getPager().setPager(request, "members.jsp", "id=" + groupID);
%>
<jsp:include page="../pager.jsp" flush="false" />

<script>
function addMember(user, group, name) {
	if (!window.confirm("ユーザー " + name + " をメンバーに追加します。よろしいですか？")) return;
	$.ajax({
		type: "GET",
		url: "<%=request.getContextPath()%>" + "/member.act?mode=add&user=" + user + "&group=" + group,
		dataType: "script"
	});
}

function removeMember(user, group, name) {
	if (!window.confirm("ユーザー " + name + " をメンバーから削除します。よろしいですか？")) return;
	$.ajax({
		type: "GET",
		url: "<%=request.getContextPath()%>" + "/member.act?mode=remove&user=" + user + "&group=" + group,
		dataType: "script"
	});
}

function updateStatus(mode, user) {
	if (mode == "add") {
		$("#user" + user).addClass("groupMember");
		//IEではattrでdisabledを操作するとエンボスが治らないので直接DOMで操作する
		$("#addButton" + user).get(0).disabled = true;
		$("#removeButton" + user).get(0).disabled = false;
	} else {
		$("#user" + user).removeClass("groupMember");
		//IEではattrでdisabledを操作するとエンボスが治らないので直接DOMで操作する
		$("#addButton" + user).get(0).disabled = false;
		$("#removeButton" + user).get(0).disabled = true;
	}
}
</script>
</body>
</html>
