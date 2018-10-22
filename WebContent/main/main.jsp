<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main.jsp</title>
</head>
<body>
Food 사이트의 메인 페이지<br>
<a href="<%=path %>/login.food">로그인</a><br>
<a href="<%=path %>/reg.food">회원가입</a><br>
<a href="<%=path %>/foodlist.food">맛집리스트</a><br>
</body>
</html>