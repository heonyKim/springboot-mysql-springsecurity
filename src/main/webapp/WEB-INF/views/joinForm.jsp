<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
</head>
<body>

	<form action="/user/create" method="POST">
		<input type="text" name="username" placeholder="Username"><br>
		<input type="password" name="password" placeholder="password"><br>
		<input type="email" name="email" placeholder="email"><br>
		<input type="submit" value="회원가입">
	</form>
</body>
</html>