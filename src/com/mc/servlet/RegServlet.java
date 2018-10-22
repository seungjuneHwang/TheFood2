package com.mc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mc.bean.UserBean;
import com.mc.dao.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Reg : ").append(request.getContextPath());
		System.out.println("RegServlet으로 들어옴");
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		
		System.out.println("id: " + id);
		System.out.println("pw: " + pw);
		System.out.println("name: " + name);
		
		UserBean bean = new UserBean();
		bean.setId(id);
		bean.setPw(pw);
		bean.setName(name);
		
		UserDAO.insertData(bean);
		// login 페이지로 이동
		response.sendRedirect("user/reg_succ.jsp");  //성공 메세지 페이지
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
