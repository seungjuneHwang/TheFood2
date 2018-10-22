package com.mc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mc.bean.ReviewIdBean;
import com.mc.bean.UserBean;
import com.mc.db.DBConn;

public class UserDAO {
	// 회원가입
	public static void insertData(UserBean bean) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// 데이터 베이스에서 정상적으로 데이터 삽입이 끝나면
			conn = DBConn.DBConnect();
			String sql = "insert into user1 (id, pw, name) values (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getPw());
			pstmt.setString(3, bean.getName());
			pstmt.executeUpdate();

		} catch(Exception e) {
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
	
	// 로그인 체크
	public static boolean loginCheck(String id, String pw) {
		boolean isLogin = false;   // 로그인 실패 여부
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 데이터 베이스에서 정상적으로 데이터 삽입이 끝나면
			conn = DBConn.DBConnect();
			String sql = "select * from user1 where id = ? and pw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 맞다.
				System.out.println("id/ pw 맞다");
				isLogin = true;
			} else {
				System.out.println("id/ pw 틀리다");
			}

		} catch(Exception e) {
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
		return isLogin;
	}  // 로그인 매소드
	
	// 리뷰 저장
	public static void insertReview(String id, String s_idx, String review) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// 데이터 베이스에서 정상적으로 데이터 삽입이 끝나면
			conn = DBConn.DBConnect();
			String sql = "insert into review(u_idx, s_idx, review) values " + 
					"((select u_idx from user1 where id = ?), ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, s_idx);
			pstmt.setString(3, review);
			pstmt.executeUpdate();

		} catch(Exception e) {
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
	
	
	// 리뷰 데이터 불러 오기
	public static ArrayList<ReviewIdBean> getReviewData(String s_idx) {
		ArrayList<ReviewIdBean> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 데이터 베이스에서 정상적으로 데이터 삽입이 끝나면
			conn = DBConn.DBConnect();
			String sql = "select u.id, r.review from review r, user1 u " + 
					"where r.u_idx = u.u_idx and r.s_idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_idx);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReviewIdBean bean = new ReviewIdBean();
				bean.setId(rs.getString("id"));
				bean.setReview(rs.getString("review"));
				list.add(bean);
			}

		} catch(Exception e) {
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
		return list;
	} 
	
	// 리뷰의 갯수 불러 오기
	public static int getReviewCount(String s_idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int cnt = 0;  // 리뷰 갯수를 받아서 리턴 시켜주는 변수
		
		try {
			// 데이터 베이스에서 정상적으로 데이터 삽입이 끝나면
			conn = DBConn.DBConnect();
			String sql = "select count(*) as cnt from review where s_idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_idx);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}

		} catch(Exception e) {
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
		return cnt;
	} 
	
}   // 클래스 괄호
