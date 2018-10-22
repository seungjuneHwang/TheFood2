package com.mc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mc.dao.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Login : ").append(request.getContextPath());
		System.out.println("LoginServlet으로 들어옴");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		System.out.println("id: " + id);
		System.out.println("pw: " + pw);
		
		// 데이터 베이스 연결 해서 id가 있는지?
		// 패스워드가 맞는지? 체크를 해야함!!
		if (UserDAO.loginCheck(id, pw)) { 
			System.out.println("id/pw 가 맞습니다."); 
//			response.sendRedirect("foodlist.food");
			// 세션 처리 하고 로그인 된 사용자만 추가 버튼 접근
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			RequestDispatcher dis = 
					request.getRequestDispatcher("foodlist.food");
			dis.forward(request, response);
			
		} else {
			System.out.println("틀렸습니다.");
			response.sendRedirect("user/login_fail.jsp");
		}
		
		
//		RequestDispatcher dis = 
//				request.getRequestDispatcher("user/login.jsp");
//		dis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
