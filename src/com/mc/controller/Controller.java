package com.mc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet("*.food")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		System.out.println("자르기 전(uri): " + uri);   // 주소 
		String [] uriArr = uri.split("/");  // 문자열 / 로 나누기
		uri = uriArr[uriArr.length-1];   // 나누어진 문자열 마지막 부분
		System.out.println("자르기 후(uri): " + uri);   // 주소
		
		String site = null;  // 이동 시킬 서블릿
		switch(uri) {
		case "main.food": 
			site =  "main/main.jsp";
			System.out.println("메인 페이지로");
			break;
		case "login.food":   // 로그인 페이지
			site =  "user/login.jsp";
			System.out.println("로그인 페이지로");
			break;
		case "login_proc.food":   // 로그인 처리
			site =  "LoginServlet";
			System.out.println("로그인 처리");
			break;
		case "logout.food":   // 로그아웃
			site =  "user/logout.jsp";
			System.out.println("로그아웃 처리");
			break;
		case "reg.food":
			site =  "user/reg.jsp";
			System.out.println("회원 가입 페이지로");
			break;
		case "reg_proc.food":
			site =  "RegServlet";
			System.out.println("회원 가입 처리");
			break;
		case "foodlist.food":
			site =  "FoodLoadServlet";
			System.out.println("맛집 보기 페이지로");
			break;
		case "foodplus.food":
			site =  "FoodPlusServlet";
			System.out.println("맛집 추가");
			break;
		case "review.food":
			site =  "ReViewServlet";
			System.out.println("리뷰 보기");
			break; 
		case "reviewplus.food":
			site =  "ReViewPlusServlet";
			System.out.println("리뷰 추가");
			break; 
		default:
			System.out.println("알수 없는 페이지");
//			response.sendRedirect("err/notfound.html");
		}
		
		if (site == null) {
			response.sendRedirect("err/notfound.html");
		} else {
			RequestDispatcher dis = request.getRequestDispatcher(site);  // () 안에는 이동시킬 주소
			dis.forward(request, response);
		}
//		if (uri.equals("/Food/login.food")) {
//			System.out.println("로그인 페이지로");
//		} else {
//			System.out.println("404 not found!!");
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
