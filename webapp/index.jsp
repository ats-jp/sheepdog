<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="jp.ats.sheepdog.*" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<title>ホーム<%=jp.ats.sheepdog.Sheepdog.SYSTEM_NAME%></title>

<meta http-equiv="content-type" content="text/html;charset=UTF-8" />

<link rel="stylesheet" type="text/css" href="css/reset.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/basic.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/infinical.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/sheepdog.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/sheepdogmain.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/fwindow.css" media="screen" />
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
<script src="js/sheepdog.js" type="text/javascript"></script>

<script>
jQuery(document).ready(function() {
	jQuery("ul.sf-menu").superfish();

	var width = jQuery(window).width() - getScrollbarWidth();
	jQuery(".sheepdogPane").width(width - 140);

	jQuery.infinical.start(new Date(), {
		contextPath: "<%=request.getContextPath()%>" + "/user",
		disableMemberMove: true,
		blockMessage: "<img src='img/indicator.gif' /> 読み込み中...",
		afterParseCallback: function() {
			jQuery(".fwindow").fwindow();
		}
	});

	jQuery.fwindow.registCloseCallback(jQuery.infinical.reflesh);

	loadNotices("event");
	loadNotices("topic");
	loadNotices("comment");

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

<h1 class="title"><%=UserManager.getUser().getName()%> さんのホーム</h1>

<jsp:include page="infinical.jsp" flush="false" />

<div id="noticeArea">
<h1>新着通知</h1>

<h2 onclick="loadNotices('event');" class="functionalLink" title="クリックで最新の状態に更新">新着<%=Sheepdog.EVENT_NAME%></h2>
<div id="eventArea"></div>

<h2 onclick="loadNotices('topic');" class="functionalLink" title="クリックで最新の状態に更新">新着<%=Sheepdog.TOPIC_NAME%></h2>
<div id="topicArea"></div>

<h2 onclick="loadNotices('comment');" class="functionalLink" title="クリックで最新の状態に更新">新着コメント</h2>
<div id="commentArea"></div>
</div>

<%@ include file="footer.jsp" %>

</div>

<%@ include file="fwindow.jsp" %>
</body>
</html>
