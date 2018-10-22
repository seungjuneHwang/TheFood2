<%@page import="com.mc.magic.MagicNum"%>
<%@page import="com.mc.bean.StoreBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//path에는 지금 프로젝트 명(/Food2) 가 들어감
	String path = request.getContextPath();
	// 세션값 받아 와서 추가 버튼 보이기 / 안 보이기
	String loginID = (String)session.getAttribute("id");
	
	// 페이지
	int totalPage = (int)request.getAttribute("tPage");
	int currPage = (int)request.getAttribute("cPage");
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<title>맛집 리스트</title>
</head>
<body>
<div class="container">
  <h2>맛집 리스트</h2>
  <p>안녕하세요. 맛집 사이트 입니다. 
  맛집 리스트 를 보여 줘요! (+ 리뷰 갯수)
  </p>       
  <% if(loginID != null) { %>
  	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">맛집추가</button>
  	<button type="button" class="btn btn-danger float-right" onclick="location.href='<%=path%>/logout.food'">로그아웃</button><br>
  <% } %>     
  <table class="table table-hover">
    <thead>
    	<tr>
        <th>번호</th>
        <th>가게명</th>
        <th>전화번호</th>
        <th>주메뉴</th>
      </tr>
    </thead>
    <tbody>
<%
	ArrayList<StoreBean> beanList = (ArrayList<StoreBean>)request.getAttribute("storelist");
	int i = (currPage * MagicNum.PAGE_SET - MagicNum.PAGE_SET) + 1;  // 임시로 증가 시키기 위해
	for (StoreBean bean : beanList) {
%>
	
      <tr onclick="window.location='<%=path%>/review.food?idx=<%=bean.getIdx() %>&name=<%=bean.getName() %>';" style="cursor:pointer"  >
        <td ><%=i++ %></td>
        <td><%=bean.getName() %> &nbsp;&nbsp;&nbsp;<font color="red">+<%=bean.getReview() %></font></td>
        <td><%=bean.getTel()%></td>
        <td><%=bean.getMenu() %></td>
      </tr>
      
<% 	} %>		
	
    </tbody>
  </table>
  <p align="center">
  <% for (int p = 1; p<=totalPage; p++) { %>
  	<a href="<%=path%>/foodlist.food?page=<%=p %>"><%=p %></a> &nbsp;
  <% } %>
  </p>
    <!-- The Modal Start -->
  <div class="modal" id="myModal">
    <div class="modal-dialog">
      <div class="modal-content">
<%--       path에는 지금 프로젝트 명(/Food2) 가 들어감 --%>
       <form action="<%=path %>/foodplus.food">
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">맛집 추가</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
		   
		    <div class="form-group">
		      <label for="store">맛집명</label>
		      <input type="text" class="form-control" id="store" placeholder="맛집을 입력" name="name">
		    </div>
		    <div class="form-group">
		      <label for="tel">전화번호</label>
		      <input type="text" class="form-control" id="tel" placeholder="전화번호 입력" name="tel">
		    </div>
		    <div class="form-group">
		      <label for="menu">주메뉴</label>
		      <input type="text" class="form-control" id="menu" placeholder="메뉴 입력" name="menu">
		    </div>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="submit" class="btn btn-primary">추가</button>
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        </div>
        </form>
      </div>
    </div>
  </div>
  <!-- The Modal End-->
  
</div>
</body>
</html>