<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" %>
<html>
<head>
<title>エラー</title>
</head>
<body>

<pre>
<%=jp.ats.sheepdog.ApplicationException.getExceptionMessage()%>
</pre>

</body>
</html>