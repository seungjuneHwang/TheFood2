<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String path = request.getContextPath();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 실패</title>
</head>
<body>
<script>
	alert("로그인 실패!!\nID/PW를 확인 하세요.");
	location.href="<%=path%>/login.food";
</script>
</body>
</html>