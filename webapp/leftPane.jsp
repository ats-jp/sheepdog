<%@ page pageEncoding="UTF-8" %>
<%@ page language="java" %>
<%@ page import="jp.ats.sheepdog.*" %>
<%@ page import="jp.ats.sheepdog.common.*" %>
<%@ page import="jp.ats.sheepdog.dataobjects.*" %>
<%@ page import="jp.ats.sheepdog.dataobjects.t_group_historyDAO.*" %>

<div id="userInfo">
<div>ユーザー：</div>
<a class="functionalLink fwindow" href="common/user.jsp"><%=UserManager.getUser().getName()%></a>
</div>

<div id="groupHistory">
<div>よく利用する<br />グループ：</div>
<%
t_group_historyIterator iterator = GroupHistoryAction.select();
for (t_group_historyDTO dto : iterator) {
%>
<div><a href="group.jsp?id=<%=dto.getgroup_id()%>"><%=dto.gett_groupByt_group_history_group_id_fkey().getname()%></a></div>
<%
}
%>
</div>
