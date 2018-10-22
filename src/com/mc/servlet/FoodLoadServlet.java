package com.mc.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mc.bean.StoreBean;
import com.mc.dao.FoodDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/FoodLoadServlet")
public class FoodLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodLoadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("FoodLoadServlet으로 들어옴(main/foodlist.jsp)");
		String page = request.getParameter("page");
		int totalPage = FoodDAO.getStoreCnt();
		System.out.println(totalPage);
		int currPage = 1;
		if (page != null) {
			currPage = Integer.parseInt(page);
		}
//		StoreBean bean = FoodDAO.getStore();  // 임시 코드
		ArrayList<StoreBean> beanList = FoodDAO.getStoreList(currPage);
		
		/*
		ArrayList<Integer> tList = new ArrayList<>();
		ArrayList<String> sList = new ArrayList<>();
		sList.add("문자열1");
		sList.add("문자열2");
		sList.add("문자열3");
		tList.add(1);
		tList.add(2);
		tList.add(3);
		for (int i : tList) {
			System.out.println(i);
		}
		for (int i =0; i<sList.size(); i++) {
			System.out.println(sList.get(i));
		}
		*/
		
		// 요즘 쓰는 스타일
//		for (StoreBean bean : beanList) {
//			System.out.println(bean.getIdx());
//			System.out.println(bean.getName());
//			System.out.println(bean.getTel());
//			System.out.println(bean.getMenu());
//		}
		// classic 코딩 스타일
//		int size = beanList.size();
//		for (int i = 0; i<size; i++) {
//			System.out.println(beanList.get(i));
//			StoreBean bean = beanList.get(i);
//			System.out.println(bean.getIdx());
//			System.out.println(bean.getName());
//			System.out.println(bean.getTel());
//			System.out.println(bean.getMenu());
//		}
//		
//		System.out.println(beanList.get(0));
//		System.out.println(beanList.get(1));
//		System.out.println(beanList.get(2));
//		System.out.println(bean.getIdx());
//		System.out.println(bean.getName());
//		System.out.println(bean.getTel());
//		System.out.println(bean.getMenu());
		
		// DB에서 받아온 데이터 리스트를 jsp 로 전달 시키기 위해
		request.setAttribute("storelist", beanList);
		request.setAttribute("cPage", currPage);
		request.setAttribute("tPage", totalPage);
		
		RequestDispatcher dis = 
				request.getRequestDispatcher("main/foodlist.jsp");
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
