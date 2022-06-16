<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<h1>ようこそ</h1>
<p>名前を入力してください</p><br>
<form action="/todoApp/Controller" method="post">
<input type="text" name="name">
<input type="submit" value="確認">
</form>
</body>
</html>