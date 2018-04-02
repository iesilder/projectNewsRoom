package com.webjjang.board.dao;

import java.sql.Connection;		
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.webjjang.board.dto.NewsDTO;
import com.webjjang.util.DBUtil;
import com.webjjang.util.KeyWordUtil;

public class NewsDAO {
	//담을 객체 선언
	List<NewsDTO> list = null;

	// 연결 객체 선언: 연결, 처리, 결과
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql;
	String keyWord;
	int num;
	
	//글 리스트를 가져오는 메서드
	public List<NewsDTO> list() throws SQLException{
//	System.out.println("NewsDAO.list()");

	
		//1. 드라이버 확인 //2. 연결
		con = DBUtil.getConnection();
		
		//3. sql문 작성
		sql = " select no, title, pubDate, hit "
				+ " from news_board "
				+ " where keyword = ?"
				+ " order by no asc ";
		
		//4. 처리문 객체
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1,KeyWordUtil.getKeyword());
		
		//5. 실행
		rs = pstmt.executeQuery();
		
		//6. 표시
		while(rs.next()) {
			if(list==null) list = new ArrayList<>();	
			NewsDTO newsDTO = new NewsDTO();	
			newsDTO.setNo(rs.getInt("no"));
			newsDTO.setTitle(rs.getString("title"));
			newsDTO.setPubDate(rs.getString("pubDate"));
			newsDTO.setHit(rs.getInt("hit"));
			list.add(newsDTO);
		}
			//7. 닫기
			DBUtil.close(con, pstmt, rs);
			return list;
	}
//	for(int i=0;i<list.size();i++)
//		System.out.println(list.get(i).getTitle());
	
	
	
	//글 보기 하는 메서드 
	
	public NewsDTO view(int no) throws SQLException{
//		System.out.println("NewsDAO.view()");
		NewsDTO newsDTO = new NewsDTO();

		
			//1. 드라이버 확인 //2. 연결
			con = DBUtil.getConnection();
			
			//3. sql문 작성
			sql = " select no, title, url,"
					+ " content, pubDate, hit "
					+ " from news_board "
					+ " where keyword = ?"
					+ " and no = ? ";
			
			//4. 처리문 객체
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,KeyWordUtil.getKeyword());
			pstmt.setInt(2,KeyWordUtil.getNum());
			
			//5. 실행
			rs = pstmt.executeQuery();
			

			//6. 표시
			while(rs.next()) {
				newsDTO.setNo(rs.getInt("no"));
				newsDTO.setTitle(rs.getString("title"));
				newsDTO.setUrl(rs.getString("url"));
				newsDTO.setContent(rs.getString("content"));
				newsDTO.setPubDate(rs.getString("pubDate"));
				newsDTO.setHit(rs.getInt("hit"));
			}
		
				//7. 닫기
				DBUtil.close(con, pstmt, rs);
				return newsDTO;
	}
	// 조회수 1증가 시키는 메서드
	public void increase(int no) {
//		System.out.println("NewDAO.increase()");
		try {
			//1. 드라이버 확인 // 2. 연결
			con = DBUtil.getConnection();
			
			//3. sql문 작성
			sql= " update news_board set hit = hit + 1 "
					+ " where keyword = ? "
					+ " and no = ? ";
			//4. 처리문 객체
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,KeyWordUtil.getKeyword());
			pstmt.setInt(2,KeyWordUtil.getNum());
			
			//5.실행 -> select: executeQuery()
			//insert, update, delete : executeUpdate()
			pstmt.executeUpdate();
			
			//6. 표시 -> 오류가 없으면 정상처리
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try { 
				//7. 닫기
				DBUtil.close(con, pstmt);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	// 글쓰는 메서드
	public void write(NewsDTO newsDTO) throws SQLException {
		// TODO Auto-generated method stub
//		System.out.println("NewsDAO.write()");
		System.out.println(newsDTO);
		
			//1. 드라이버 확인  //2. 연결
			con = DBUtil.getConnection();
			
			//3. sql문 작성
			sql = " insert into news_board(no, title, "
					+ " url, content, keyword) "
					+ " values(news_seq.nextval, ?, ?, ?, ? )";
			//4. 처리문 객체
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newsDTO.getTitle());
			pstmt.setString(2, newsDTO.getUrl());
			pstmt.setString(3, newsDTO.getContent());
			pstmt.setString(4, KeyWordUtil.getKeyword());
			
			//5. 실행 
			pstmt.executeUpdate();
		
			//7. 닫기
			DBUtil.close(con, pstmt);	
	}
	// 업데이트 메서드
	public void update(int no, String title, String url, String content) throws SQLException {
		// TODO Auto-generated method stub
//		System.out.println("NewsDAO.update()");
		// 1.드라이버 확인 및 연결
		con = DBUtil.getConnection();
		
		// 3. sql문 작성
		sql = 	" update news_board set "
				+ "title = ?, url = ?, content = ?"
				+ "where no = ? and keyword = ?";
		// 4. 처리문 객체
		pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, title);
		pstmt.setString(2, url);
		pstmt.setString(3, content);
		pstmt.setInt(4, no);
		pstmt.setString(5, KeyWordUtil.getKeyword());
		
		// 5. 쿼리 업데이트
		pstmt.executeUpdate();
		
		// 6. 객체를 닫는다.
		DBUtil.close(con, pstmt);
	}
	
	// 글삭제 하는 메서드
	public void delete(int no) throws SQLException {
		// TODO Auto-generated method stub
//		System.out.println("NewsDAO.delete()");
		//1. 드라이버 확인  //2. 연결
		con = DBUtil.getConnection();
		
		//3. sql문 작성
		sql = "delete from news_board "
				+ "where no = ? and keyword = ? ";
		
		//4. 처리문 객체
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, no);
		pstmt.setString(2, KeyWordUtil.getKeyword());
		
		//5. 실행
		pstmt.executeUpdate();
		
		//7. 닫기
		DBUtil.close(con, pstmt);
	}
	
	
}
