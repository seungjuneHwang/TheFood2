package com.mc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mc.dao.UserDAO;

/**
 * Servlet implementation class ReViewPlusServlet
 */
@WebServlet("/ReViewPlusServlet")
public class ReViewPlusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReViewPlusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("ReViewPlusServlet으로 들어옴!!");
		
		request.setCharacterEncoding("utf-8");  //한글 처리
		String id  = request.getParameter("id");   // 로그인 ID
		String s_idx  = request.getParameter("s_idx");   // 가게 코드
		String review = request.getParameter("review");  // 작성된 내용
		System.out.println("작성자: " + id);
		System.out.println("가게코드: " + s_idx);
		System.out.println("리뷰내용: " + review);
		
		// id, s_idx, review 정보를 가지고 DB에 저장 하는 메소드 만들기(UserDAO)
		UserDAO.insertReview(id, s_idx, review);
		// 리뷰 푸드 페이지로 이동 시켜야함
		response.sendRedirect("foodlist.food");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
