<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="jp.ats.sheepdog.*" %>

<div id="menuPane">
<ul class="sf-menu">
	<li class="current">
		<a href="<%=request.getContextPath()%>/">ホーム</a>
	</li>
	<li>
		<a href="javascript:void(0);">グループ</a>
		<ul>
			<li class="current"><a class="fwindow" href="groups.jsp?type=member">自分がメンバーの<br />グループ</a></li>
			<li><a class="fwindow" href="groups.jsp?type=owner">自分がオーナーの<br />グループ</a></li>
			<li><a class="fwindow" href="groups.jsp?type=all">全てのグループ</a></li>
			<li><a class="fwindow" href="common/groups.jsp">グループ管理</a></li>
		</ul>
	</li>
	<li>
		<a href="javascript:void(0);">登録履歴</a>
		<ul>
			<li class="current"><a class="fwindow" href="common/threads.jsp?event=true">今までに登録した<br /><%=Sheepdog.EVENT_NAME%>一覧</a></li>
			<li class="current"><a class="fwindow" href="common/threads.jsp?event=false">今までに登録した<br /><%=Sheepdog.TOPIC_NAME%>一覧</a></li>
			<li><a class="fwindow" href="common/comments.jsp">今までに登録した<br />コメント一覧</a></li>
		</ul>
	</li>
	<li>
		<a href="javascript:void(0);">新着通知</a>
		<ul>
			<li class="current"><a class="fwindow" href="common/subscribeGroups.jsp">新着<%=Sheepdog.EVENT_NAME%>、<br /><%=Sheepdog.TOPIC_NAME%>通知<br />グループ一覧</a></li>
			<li><a class="fwindow" href="common/subscribeThreads.jsp">新着コメント通知<br /><%=Sheepdog.EVENT_NAME%>、<%=Sheepdog.TOPIC_NAME%><br />一覧</a></li>
		</ul>
	</li>
	<li>
		<a href="javascript:void(0);">アカウント</a>
		<ul>
			<li class="current"><a class="fwindow" href="common/password.jsp">パスワードの変更</a></li>
		</ul>
	</li>
<%
if (UserManager.isUserInRole(Role.SYSTEM, Role.ADMIN)) {
%>
	<li>
		<a href="javascript:void(0);">管理者用</a>
		<ul>
			<li class="current"><a class="fwindow" href="admin/users.jsp">ユーザー管理</a></li>
		</ul>
	</li>
<%
}

if (UserManager.isUserInRole(Role.SYSTEM)) {
%>
	<li>
		<a href="javascript:void(0);">システム管理者用</a>
	</li>
<%
}
%>
	<li>
		<a href="logout.jsp">ログアウト</a>
	</li>
</ul>
<div class="clear"></div>
</div>
