<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>환영합니다.</title>
</head>
<body>
<script>
	alert("회원가입이 완료 되었습니다. \n로그인 페이지로 이동 합니다.");
	location.href="<%=path%>/login.food";
</script>
</body>
</html>