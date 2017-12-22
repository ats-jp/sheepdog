<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="jp.ats.sheepdog.*" %>
<%@ page import="jp.ats.sheepdog.common.*" %>
<%
Comment currentComment = (Comment) request.getAttribute("currentComment");
%>
<%=Sheepdog.EVENT_NAME%> 「<a href="<%=request.getContextPath()%>/common/view.jsp?id=<%=currentComment.getThreadID()%>"><%=currentComment.getThreadTitle()%></a>」
のコメント No. <%=currentComment.getNumber()%>
