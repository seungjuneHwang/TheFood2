package com.mc.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mc.bean.ReviewIdBean;
import com.mc.bean.StoreBean;
import com.mc.dao.UserDAO;

/**
 * Servlet implementation class ReViewServlet
 */
@WebServlet("/ReViewServlet")
public class ReViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("review at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");  // 한글을 위해	
		String name = request.getParameter("name");
		String idx = request.getParameter("idx");
		System.out.println("선택하신 가게는? " + name);
		System.out.println("선택하신 가게코드? " + idx);
		StoreBean bean = new StoreBean();
		bean.setIdx(idx);
		bean.setName(name);
		
		ArrayList<ReviewIdBean> reviewList = UserDAO.getReviewData(idx);
		
		request.setAttribute("store", bean);  // 가게이름을 넘기기 위해
		request.setAttribute("reviewList", reviewList);
		
		RequestDispatcher dis = 
				request.getRequestDispatcher("main/review.jsp");
		dis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
