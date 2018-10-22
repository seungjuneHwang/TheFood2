<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login.jsp</title>
</head>
<body>
로그인 페이지를 만들기
<form action="<%=path %>/login_proc.food" method="post">
	id: <input type="text" name="id"><br>
	pw: <input type="password" name="pw"><br>
	<input type="submit" value="로그인"><br>
</form>
</body>
</html>