<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
회원가입 페이지 만들기
<form action="<%=path %>/reg_proc.food" method="post">
	id: <input type="text" name="id"><br>
	pw: <input type="password" name="pw"><br>
	name: <input type="text" name="name"><br>
	<input type="submit" value="회원가입"><br>
</form>
</body>
</html>