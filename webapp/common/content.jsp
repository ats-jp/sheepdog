<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="jp.ats.sheepdog.*" %>
<%@ page import="jp.ats.sheepdog.common.*" %>
<%@ page import="jp.ats.sheepdog.dataobjects.*" %>
<%@ page import="jp.ats.sheepdog.dataobjects.t_content_attachDAO.*" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<%
Content content = ThreadAction.searchContent(request.getParameter("id"));
%>

<title><%=content.getCreateTime()%> の修正履歴</title>

<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="../css/reset.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/basic.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/sheepdog.css" media="screen" />
</head>
<body>
<table class="sheepdogTable">
<tr>
<th nowrap="nowrap">ID</th>
<td>
<%=content.getID()%>
</td>
</tr>
<tr>
<th nowrap="nowrap">タイトル</th>
<td>
<%=content.getTitle()%>
</td>
</tr>
<tr>
<th nowrap="nowrap">本文</th>
<td>
<%=Utilities.restore(content.getBody())%>
</td>
</tr>
<tr>
<th nowrap="nowrap">添付ファイル</th>
<td>
<%
t_content_attachIterator eventAttachIterator = AttachedFileAction.selectAttachedFilesByContentID(content.getID());
for (t_content_attachDTO contentAttach : eventAttachIterator) {
	t_attachDTO file = contentAttach.gett_attachByt_content_attach_attach_id_fkey();
%>
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
<tr>
<th nowrap="nowrap">修正者</th>
<td>
<%=content.getOwner().getName()%>
</td>
</tr>
<tr>
<th nowrap="nowrap">修正時刻</th>
<td>
<%=content.getCreateTime()%>
</td>
</tr>
</table>
</body>
</html>
