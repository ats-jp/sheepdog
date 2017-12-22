<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="jp.ats.sheepdog.*" %>
<%@ page import="jp.ats.sheepdog.common.*" %>
<%@ page import="jp.ats.sheepdog.common.Thread" %>

<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />

<%
String groupID = request.getParameter("id");
Group group = GroupAction.getGroup(groupID);

GroupHistoryAction.updateHistory(group.getID());

NoticeServlet.deleteNotice(request.getParameter("deleteNotice"));
%>

<title><%=group.getName()%><%=jp.ats.sheepdog.Sheepdog.SYSTEM_NAME%></title>

<link rel="stylesheet" type="text/css" href="css/reset.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/basic.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/infinical.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/sheepdog.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/sheepdogmain.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/fwindow.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/minipopup.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/superfish.css" media="screen" />

<script src="js/prototype.js" type="text/javascript"></script>
<script src="js/scrotable.js" type="text/javascript"></script>
<script src="js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
jQuery.noConflict();
</script>
<script src="js/jquery.dragscroll.js" type="text/javascript"></script>
<script src="js/jquery.infinical.js" type="text/javascript"></script>
<script src="js/jquery.blockUI.js" type="text/javascript"></script>
<script src="js/jquery.fwindow.js" type="text/javascript"></script>
<script src="js/superfish.js" type="text/javascript"></script>
<script src="js/jquery.minipopup.js" type="text/javascript"></script>
<script src="js/sheepdog.js" type="text/javascript"></script>
<script>
var currentPage = 1;
jQuery(document).ready(function() {
	jQuery("ul.sf-menu").superfish();

	var width = jQuery(window).width() - getScrollbarWidth();
	jQuery(".sheepdogPane").width(width - 140);

	jQuery.infinical.start(new Date(<%=Utilities.convertJSDateParameter(request.getParameter("date"))%>), {
		contextPath: "<%=request.getContextPath()%>" + "/group",
		memberCondition: "&id=" + <%=groupID%>,
		blockMessage: "<img src='img/indicator.gif' /> 読み込み中...",
		maxHeight: 500,
		afterParseCallback: function() {
			jQuery(".fwindow").fwindow();
		}
	});

	jQuery.fwindow.registCloseCallback(function() {
		jQuery.infinical.reflesh();
		loadThreads(<%=groupID%>, currentPage);
	});

	loadThreads(<%=groupID%>, currentPage);

	startNewNoticeCheck();
});
</script>
</head>
<body>

<div id="headerPane">
<div id="titlePane">
<div id="atsLogo">ats;</div>
<div id="systemDescription">グループウェア<br />基盤システム</div>
<div class="clear"></div>
</div>
</div>
<div id="leftPane">
<jsp:include page="leftPane.jsp" flush="false" />
</div>

<jsp:include page="menu.jsp" flush="false" />

<div class="sheepdogPane" id="mainPane">

<div id="newNoticeArea"></div>

<h1 class="title"><%=group.getName()%></h1>
<div><%=group.getDescription()%></div>

<% /* jspod{jp.ats.sheepdog.form.BlankForm} */ jp.ats.sheepdog.form.BlankForm jspod = (jp.ats.sheepdog.form.BlankForm) jp.ats.webkit.jspod.JspodManager.prepareJspod(jp.ats.sheepdog.form.BlankForm.class); %>
<form name="SubscribeThreadForm" action="common.SubscribeGroup.do">
<input type="hidden" name="id" />
<input type="hidden" name="action" />
<input type="hidden" name="<%=jspod.tokenName()%>" value="<%=jspod.tokenValue()%>" />
</form>

<form name="SubscribeCommentForm" action="common.SubscribeGroup.do">
<input type="hidden" name="id" />
<input type="hidden" name="action" />
<input type="hidden" name="<%=jspod.tokenName()%>" value="<%=jspod.tokenValue()%>" />
</form>

<script>
function subscribeThread(id, action, message) {
	if (window.confirm(message + "。よろしいですか？")) {
		document.SubscribeThreadForm.id.value = id;
		document.SubscribeThreadForm.action.value = action;
		document.SubscribeThreadForm.submit();
	}
}

function subscribeComment(id, action, message) {
	if (window.confirm(message + "。よろしいですか？")) {
		document.SubscribeCommentForm.id.value = id;
		document.SubscribeCommentForm.action.value = action;
		document.SubscribeCommentForm.submit();
	}
}
</script>

<jsp:include page="infinical.jsp" flush="false" />
<%
int groupIDForSubscribe = Integer.parseInt(groupID);

if (SubscribeAction.hasSubscribeOnGroup(groupIDForSubscribe)) {
%>
<button disabled="disabled">新着<%=Sheepdog.EVENT_NAME%>、新着<%=Sheepdog.TOPIC_NAME%>通知を受け取る</button>
<button onclick="subscribeThread('<%=groupID%>', 'off', '新着<%=Sheepdog.EVENT_NAME%>通知受け取りを中止します');">新着<%=Sheepdog.EVENT_NAME%>、新着<%=Sheepdog.TOPIC_NAME%>通知受け取りを中止</button>
<%
} else {
%>
<button onclick="subscribeThread('<%=groupID%>', 'on', '新着<%=Sheepdog.EVENT_NAME%>通知を受け取ります');">新着<%=Sheepdog.EVENT_NAME%>、新着<%=Sheepdog.TOPIC_NAME%>通知を受け取る</button>
<button disabled="disabled">新着<%=Sheepdog.EVENT_NAME%>、新着<%=Sheepdog.TOPIC_NAME%>通知受け取りを中止</button>
<%
}
%>

<p></p>

<h1>このグループの<%=Sheepdog.TOPIC_NAME%></h1>

<a href="common/thread.jsp?mode=insert&groupID=<%=groupID%>" class="functionalLink fwindow"><%=Sheepdog.TOPIC_NAME %>の登録</a>

<p></p>

<div id="groupThreadsArea"></div>

<script>
function loadThreads(id, page) {
	currentPage = page;
	jQuery.ajax({
		type: "GET",
		url: "groupThreads.act?id=" + id + "&page=" + page,
		dataType: "script"
	});
}

function groupThreadCallback(html) {
	jQuery("#groupThreadsArea").html(decodeURIForJavaEncode(html));
	jQuery(".fwindow").fwindow();
}
</script>

<%@ include file="footer.jsp" %>

</div>

<%@ include file="fwindow.jsp" %>
</body>
</html>
