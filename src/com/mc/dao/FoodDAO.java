package com.mc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mc.bean.StoreBean;
import com.mc.db.DBConn;
import com.mc.magic.MagicNum;

public class FoodDAO {
	// 맛집 추가
	public static void insertData(StoreBean bean) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// 데이터 베이스 연결
			conn = DBConn.DBConnect();
			String sql = "insert into store (name, tel, menu) values (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getTel());
			pstmt.setString(3, bean.getMenu());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("에러 관리자에게 문의::");
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 맛집 리스트 가지고 오기
	public static ArrayList<StoreBean> getStoreList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// Bean(데이터) 오브젝트를 담아서 전달 할 리스트 변수 선언
		ArrayList<StoreBean> beanList = new ArrayList<>();
		// 임시로 잠시 뺀거(소스가 수정 될꺼 같은데...)

		try {
			conn = DBConn.DBConnect();
			String sql = "SELECT DISTINCT idx, name, tel, menu, " + 
					"(SELECT COUNT(*) FROM review WHERE s_idx = store.idx) AS cnt " + 
					"FROM store";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StoreBean bean = new StoreBean();
				// 데이터가 있을 때 여기에 들어옴.
				String idx = rs.getString("idx"); // pk 를 들고 오고
				String name = rs.getString("name"); // 가게명을 들고 오고
				String tel = rs.getString("tel"); // 전화번호를 들고 오고
				String menu = rs.getString("menu"); // 메뉴를 들고 온다.
				int review = rs.getInt("cnt");

				// DB에서 받아온 데이터를 담는다.
				bean.setIdx(idx);
				bean.setName(name);
				bean.setTel(tel);
				bean.setMenu(menu);
				bean.setReview(review);

				beanList.add(bean); // 데이터들(bean)을 담는다.

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("에러 관리자에게 문의::");
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return beanList;
	} // 매소드

	public static int getPages() {
		int page = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.DBConnect();
			String sql = "select count(*) as cnt from store";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int total = rs.getInt("cnt");
				page = total / 10;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("에러 관리자에게 문의::");
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return page;
	}

	// 맛집 리스트 가지고 오기
	public static ArrayList<StoreBean> getStoreList(int start) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// Bean(데이터) 오브젝트를 담아서 전달 할 리스트 변수 선언
		ArrayList<StoreBean> beanList = new ArrayList<>();
		
		try {
			// start 넘어 오는 데이터는 1, 2, 3 이렇게 들어와서
			// limit 시작은 0, 10, 20 ...
			start = (start-1) * MagicNum.PAGE_SET;
			conn = DBConn.DBConnect();
			String sql = "SELECT DISTINCT idx, name, tel, menu, " + 
					"(SELECT COUNT(*) FROM review WHERE s_idx = store.idx) AS cnt " + 
					"FROM store limit ?, " + MagicNum.PAGE_SET;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StoreBean bean = new StoreBean();
				// 데이터가 있을 때 여기에 들어옴.
				String idx = rs.getString("idx"); // pk 를 들고 오고
				String name = rs.getString("name"); // 가게명을 들고 오고
				String tel = rs.getString("tel"); // 전화번호를 들고 오고
				String menu = rs.getString("menu"); // 메뉴를 들고 온다.
				int review = rs.getInt("cnt");	  // 리뷰 갯수를 들고 온다
				
				// DB에서 받아온 데이터를 담는다.
				bean.setIdx(idx);
				bean.setName(name);
				bean.setTel(tel);
				bean.setMenu(menu);
				bean.setReview(review);

				beanList.add(bean); // 데이터들(bean)을 담는다.

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("에러 관리자에게 문의::");
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return beanList;
	} // 매소드
	
	// 맛집 리스트 가지고 오기 (임시코드)
	public static int getStoreCnt() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int page = 0;
		try {
			conn = DBConn.DBConnect();
			String sql = "select count(*) as cnt from store";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 데이터가 있을 때 여기에 들어옴.
				page = rs.getInt("cnt"); // pk 를 들고 오고
			}
			System.out.println(page);
			System.out.println(MagicNum.PAGE_SET);
			System.out.println(page / MagicNum.PAGE_SET);
			// 나누어 떨어지면 다음페이지 생성 방지
			if ((page % MagicNum.PAGE_SET) == 0) {
				page = page / MagicNum.PAGE_SET;
			} else {
				page = page / MagicNum.PAGE_SET + 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("에러 관리자에게 문의::");
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return page;
	} // 매소드


	// 맛집 리스트 가지고 오기 (임시코드)
	public static StoreBean getStore() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 임시로 잠시 뺀거(소스가 수정 될꺼 같은데...)
		StoreBean bean = new StoreBean();
		try {
			conn = DBConn.DBConnect();
			String sql = "select * from store";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// 데이터가 있을 때 여기에 들어옴.
				String idx = rs.getString("idx"); // pk 를 들고 오고
				String name = rs.getString("name"); // 가게명을 들고 오고
				String tel = rs.getString("tel"); // 전화번호를 들고 오고
				String menu = rs.getString("menu"); // 메뉴를 들고 온다.

				// DB에서 받아온 데이터를 담는다.
				bean.setIdx(idx);
				bean.setName(name);
				bean.setTel(tel);
				bean.setMenu(menu);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("에러 관리자에게 문의::");
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bean;
	} // 매소드

} // 클래스 괄호
