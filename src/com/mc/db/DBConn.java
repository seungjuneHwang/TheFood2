package com.mc.db;

import java.sql.*;

public class DBConn {


	public static Connection DBConnect() throws Exception {
		Connection conn = null;
		
		String JDBC_DRIVER = "com.mysql.jdbc.Driver"; // 드라이버
		String DB_URL = "jdbc:mysql://192.168.0.252/team0?&useSSL=false"; // 접속할 DB 서버
		String USER_NAME = "team0"; // DB에 접속할 사용자 이름을 상수로 정의
		String PASSWORD = "1234"; // 사용자의 비밀번호를 상수로 정의
		
		Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
		return conn;
	}

}
