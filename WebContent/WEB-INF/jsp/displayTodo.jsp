<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<%@ page import="model.DTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%
	User registerUser = (User) session.getAttribute("registerUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク登録</title>
<script type="text/javascript"> 

function alert(){

	if(window.confirm('レコードを削除します。よろしいですか？')){ 

		return true;

	}
	else{

		window.alert('削除処理はキャンセルされました');
		return false; // 送信を中止

	}

}
</script>
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

<%
	ArrayList<DTO> registerTaskList = (ArrayList<DTO>) session.getAttribute("registerTask");
	for(DTO registerTask : registerTaskList) {
		String content = registerTask.getContent();
		Date deadline = registerTask.getDeadline();
		Boolean status = registerTask.getStatus();
		int taskId = registerTask.getTaskId();
%>


		<ul>
		<li>
<%		if (status == false) {
			out.println(content + " ");
			out.println(deadline);
			out.println("<form action=\"/puttyTodo/Controller_update\" style=\"display:inline\" method=\"post\">");
			out.println("<input type=\"hidden\" name=\"taskId\" value=" + taskId + ">");
			out.println("<input type=\"submit\" value=\"完了\">");
			out.println("</form>");
		} else {
			out.println("<s>" + content + "</s>");
			out.println(deadline);
		}
%>

		<form action="/puttyTodo/Controller_delete"  style="display:inline" method="post" onSubmit="return alert()">
		<input type="hidden" name="taskId" value="<%= registerTask.getTaskId() %>">
		<input type="submit" value="削除">
		</form>
		</li>
		</ul>
		<br>

<%
	}
%>

<p><a href="http://localhost:8080/puttyTodo/index.jsp">戻る</a></p>

</body>
</html>