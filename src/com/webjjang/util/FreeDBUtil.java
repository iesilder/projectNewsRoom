package com.webjjang.util;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FreeDBUtil {
	// 오라클에 접속할 때 필요한 정보들
		private static String driver 
		  = "oracle.jdbc.driver.OracleDriver"; //오라클사에서 제공
		  // server 정보
		  private static String server 
		  = "jdbc:oracle:thin:@401PC-B5:1521:orcl";
		  private static String uid="newsroom"; 
		  private static String upw = "newsroom";
		  static { //1. 드라이버 확인 --> static으로 자동 초기화 블록실행
			  try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		// connection 객체를 전달해주는 프로그램 작성
		  public static Connection getConnection()
		  	throws SQLException {
			  return DriverManager
					  .getConnection(server, uid, upw);
}
		// DB관련 객체를 닫는 메서드 -select
		  public static void close
		  (Connection con, PreparedStatement pstmt, ResultSet rs) 
			  throws SQLException{
			  close(con,pstmt);
		  if(rs != null) rs.close();
		  }
		// DB관련 객체를 닫는 메서드 -insert 
		  public static void close
		  (Connection con, PreparedStatement pstmt)
				  throws SQLException{
			  if (con != null) con.close();
			  if (pstmt != null) pstmt.close(); 
			  
		  }
}

