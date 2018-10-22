<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    String path = request.getContextPath();
	// 세션 삭제
	session.removeAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>logout.jsp</title>
</head>
<body>
<script>
	alert("로그아웃 되었습니다.");
	location.href="<%=path%>/main.food";
</script>
</body>
</html>