<%@page import="com.mc.bean.ReviewIdBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mc.bean.StoreBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//path에는 지금 프로젝트 명(/Food2) 가 들어감
	String path = request.getContextPath();
	// 세션값 받아 와서 추가 버튼 보이기 / 안 보이기
	String loginID = (String)session.getAttribute("id");
	// 서블릿에서 던져준 가방(데이터)
	StoreBean bean = (StoreBean)request.getAttribute("store");
	String name = bean.getName();  // 리뷰 페이지에 출력 하기 위해
	String s_idx = bean.getIdx();  // 리뷰를 저장 할때 가게 코드를 넣기 위해
	
	ArrayList<ReviewIdBean> beanList = (ArrayList<ReviewIdBean>)request.getAttribute("reviewList");
	
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
<title>review.jsp</title>
</head>
<body>
<div class="container">
  <h2><b><%=name %></b> 리뷰 페이지</h2>       
  <% if(loginID != null) { %>
  	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">글쓰기</button>
  	<button type="button" class="btn btn-danger float-right" onclick="location.href='<%=path%>/logout.food'">로그아웃</button><br>
  <% } %>     
  <table class="table">
    <thead>
    	<tr>
        <th style="width:20%">작성자</th>
        <th>리뷰</th>
      </tr>
    </thead>
    <tbody>
<% for (ReviewIdBean rib : beanList) { %>
	  <tr>
        <td style="width:20%"><%=rib.getId() %></td>
        <td><%=rib.getReview() %></td>
      </tr>
<% 	} %>		
	
    </tbody>
  </table>

    <!-- The Modal Start -->
  <div class="modal" id="myModal">
    <div class="modal-dialog">
      <div class="modal-content">
<%--       path에는 지금 프로젝트 명(/Food2) 가 들어감 --%>
       <form action="<%=path %>/reviewplus.food" method="post">
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">리뷰 쓰기</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
		   
		    <div class="form-group">
		      <label for="id">작성자</label>
		      	<%=loginID %>
		      	<input type="hidden" name="id" value="<%=loginID %>">
		      	<input type="hidden" name="s_idx" value="<%=s_idx %>">
		    </div>
		    <div class="form-group">
		      <input type="text" maxlength="300" class="form-control" id="review" placeholder="리뷰 입력" name="review">
		    </div>
        </div>	
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="submit" class="btn btn-primary">저장</button>
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