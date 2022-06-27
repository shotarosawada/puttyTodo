<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Collections" %>
<%
	User registerUser = (User) session.getAttribute("registerUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク登録</title>
</head>
<body>
<h1>
<%= registerUser.getName() %>さんのTodo
</h1>

<form action="/puttyTodo/Controller_todo" method="post">
内容<input type="text" name="content">
期日<input type="date" name="deadline">
<input type="submit" value="作成">
</form>

<p><a href="http://localhost:8080/puttyTodo/index.jsp">戻る</a></p>

</body>
</html>