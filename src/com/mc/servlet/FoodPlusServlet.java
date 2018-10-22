package com.mc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mc.bean.StoreBean;
import com.mc.dao.FoodDAO;

/**
 * Servlet implementation class FoodPlusServlet
 */
@WebServlet("/FoodPlusServlet")
public class FoodPlusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodPlusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String menu = request.getParameter("menu");
		
		// 사용자가 입력한 맛집 리스트를 읽어와서 
		// DB에 저장!(db저장 하는것을 만들어 봐요!)
		StoreBean bean = new StoreBean();
		bean.setName(name);
		bean.setTel(tel);
		bean.setMenu(menu);
		
		FoodDAO.insertData(bean);
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
