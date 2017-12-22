<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="jp.ats.substrate.*" %>
<%@ page import="jp.ats.sheepdog.*" %>
<%@ page import="jp.ats.sheepdog.common.*" %>
<%@ page import="jp.ats.sheepdog.dataobjects.*" %>
<%@ page import="jp.ats.sheepdog.dataobjects.t_content_attachDAO.*" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>

<title>コメント一覧</title>

<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="../css/reset.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/basic.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/sheepdog.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/fwindow.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/minipopup.css" media="screen" />

<script src="../js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
jQuery.noConflict();
</script>
<script src="../js/jquery.fwindow.js" type="text/javascript"></script>
<script src="../js/jquery.minipopup.js" type="text/javascript"></script>
</head>
<body>
<% /* jspod{jp.ats.sheepdog.form.BlankForm} */ jp.ats.sheepdog.form.BlankForm jspod = (jp.ats.sheepdog.form.BlankForm) jp.ats.webkit.jspod.JspodManager.prepareJspod(jp.ats.sheepdog.form.BlankForm.class); %>

<script>
jQuery(document).ready(function() {
	jQuery(".fwindow").fwindow();
});
</script>

<%
request.setAttribute("jspod", jspod);
SearchResult<Comment> result = CommentAction.searchForHistory(request);
request.setAttribute("result", result);
%>
<jsp:include page="innerComments.jsp" flush="false" />

<%
result.getPager().setPager(request, "comments.jsp", "");
%>
<jsp:include page="../pager.jsp" flush="false" />

<%@ include file="../fwindow.jsp" %>
</body>
</html>
