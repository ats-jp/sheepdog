<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="jp.ats.sheepdog.*" %>
<%
Pager pager = Pager.getPager(request);
String uri = Pager.getURI(request);
String parameter = Pager.getParameter(request);
%>
<div class="pagerArea">
<span>
<%
if (pager.hasPrev()) {
%>
<a class="functionalLink" href="<%=uri%>?<%=parameter%>page=0">≪</a>
<%
} else {
%>
≪
<%
}
%>
</span>
<span>
<%
if (pager.hasPrev()) {
%>
<a class="functionalLink" href="<%=uri%>?<%=parameter%>page=<%=pager.getPrevPageCount()%>">&lt;</a>
<%
} else {
%>
&lt;
<%
}
%>
</span>
<span><%=pager.getPage()%> / <%=pager.getLastPageCount()%></span>
<span>
<%
if (pager.hasNext()) {
%>
<a class="functionalLink" href="<%=uri%>?<%=parameter%>page=<%=pager.getNextPageCount()%>">&gt;</a>
<%
} else {
%>
&gt;
<%
}
%>
</span>
<span>
<%
if (pager.hasLastPage()) {
%>
<a class="functionalLink" href="<%=uri%>?<%=parameter%>page=<%=pager.getLastPageCount()%>">≫</a>
<%
} else {
%>
≫
<%
}
%>
</span>
</div>
