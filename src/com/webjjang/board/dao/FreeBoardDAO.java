package com.webjjang.board.dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.util.FreeDBUtil;

import com.webjjang.board.dto.FreeBoardDTO;

public class FreeBoardDAO {

	// 오라클에 접속할때 필요한 정보들
	// DBUtil에 다 선언함.

	// 글리스트를 가져오는 메서드
	public List<FreeBoardDTO> list() throws Exception {
		List<FreeBoardDTO> list = null;
		// RDBMS에서 데이터를 가져 오는 프로그램 작성

		// 필요한 객체 선언
		Connection con = null; // 연결 객체
		PreparedStatement pstmt = null; // 처리문 객체
		ResultSet rs = null; // 결과 객체

		// 1. 드라이버 확인 //2. 연결
		con = FreeDBUtil.getConnection();
		// 3. sql
		String sql = "select no, title, writer, " + " to_char(writedate, 'YYYY-MM-DD') writedate, hit from Freeboard "
				+ " order by no desc ";
		// 4. 처리문 객체
		pstmt = con.prepareStatement(sql);
		// 5. 실행 -- select ->rs이 나온다.
		rs = pstmt.executeQuery();
		// 6. 표시 --> 데이터 담기
		while (rs.next()) {
			// 데이터가 있는데 list가 null이면 생성한다.
			if (list == null)
				list = new ArrayList<>();
			// 데이터 하나를 담을 수 있는 BoardDTO 객체를 생성한다.
			FreeBoardDTO boardDTO = new FreeBoardDTO();
			// 데이터를 rs에서 꺼내서 boardDTO에 담는다.
			boardDTO.setNo(rs.getInt("no"));
			boardDTO.setTitle(rs.getString("title"));
			boardDTO.setWriter(rs.getString("writer"));
			boardDTO.setWritedate(rs.getString("writedate"));
			boardDTO.setHit(rs.getInt("hit"));
			// list에 boardDTO를 담는다.
			list.add(boardDTO);
		}
			FreeDBUtil.close(con, pstmt, rs);
		
		return list;
	}

	// 글번호에 맞는 글보기 데이터를 가져오는 메서드
	public FreeBoardDTO view(int no) throws Exception {

		FreeBoardDTO boardDTO = null;
		// 오라클에서 데이터를 가져와서 채우는 프로그램 작성(생략)
		// 사용한 객체 선언
		Connection con = null; // 연결 객체
		PreparedStatement pstmt = null; // 처리문 객체
		ResultSet rs = null; // 결과 객체
		
			// 1. 드라이버 확인 //2. 연결
			con = FreeDBUtil.getConnection();
			// 3. sql문 작성
			String sql = "select no, title, content, " + " writer, writedate, hit " + " from Freeboard "
					+ " where no = ? "; // 변하는 데이터 대신 ? 사용
			// 4. 처리문 객체
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no); // 첫번째 ?에 no를 int로 셋팅
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 rs에서 꺼내서 BoardDTO에 담는다.
			if (rs.next()) {
				// 생성자가 만들어져 있어야 한다.
				boardDTO = new FreeBoardDTO(rs.getInt("no"), rs.getString("title"), rs.getString("content"),
						rs.getString("writer"), rs.getString("writeDate"), rs.getInt("hit"));
			}

		
		
			
				// 7. 닫기
				FreeDBUtil.close(con, pstmt, rs);
			
				
		return boardDTO;
	}

	// 게시판 글쓰기 처리.
	public void write(FreeBoardDTO boardDTO)throws Exception {

		// 사용한 객체 선언
		Connection con = null; // 연결 객체
		PreparedStatement pstmt = null; // 처리문 객체
			// 1. 드라이버 확인 //2. 연결
			con = FreeDBUtil.getConnection();
			// 3. sql문 작성
			String sql = "insert into Freeboard(no,title," + " content, writer) " + " values(freeboard_seq.nextval,"
					+ " ?, ?, ?) "; // 변하는 데이터 대신 ? 사용
			// 4. 처리문 객체
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardDTO.getTitle());
			pstmt.setString(2, boardDTO.getContent());
			pstmt.setString(3, boardDTO.getWriter());
			// 5. 실행 -> select: executeQuery()
			// insert, update, delete:executeUpdate()
			pstmt.executeUpdate();
			// 6. 표시 -> 오류가 없으면 정상처리
				// 7. 닫기
				FreeDBUtil.close(con, pstmt);
				// TODO: handle exception
			}
	
	

	public void Update(String title, String content, String writer, String menu) throws Exception {
		Connection con = null; // 연결 객체
		PreparedStatement pstmt = null; // 처리문 객체

			String sql = "Update FreeBoard set title = ?, content = ?," + "writer = ? where no = ? ";

			con = FreeDBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, writer);
			pstmt.setInt(4, Integer.valueOf(menu));
			pstmt.executeQuery();

			
			// TODO: handle exception
				FreeDBUtil.close(con, pstmt);
				
			
		}
	

	public void delete(String menu) throws Exception{
		Connection con = null; // 연결 객체
		PreparedStatement pstmt = null; // 처리문 객체

			con = FreeDBUtil.getConnection();
			String sql = (" Delete From Freeboard where no = ?");
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.valueOf(menu));
			pstmt.executeQuery();

			
			// TODO: handle exception
				FreeDBUtil.close(con, pstmt);
				// TODO: handle exception
				
			}

	public void increase(int no)throws Exception {

		// 사용한 객체 선언
		Connection con = null; // 연결 객체
		PreparedStatement pstmt = null; // 처리문 객체
			// 1. 드라이버 확인 //2. 연결
			con = FreeDBUtil.getConnection();
			// 3. sql문 작성
			String sql = "update Freeboard set hit = hit + 1 " + " where no = ? "; // 변하는 데이터 대신 ? 사용
			// 4. 처리문 객체
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no); // 첫번째 ?에 no를 int로 셋팅
			// 5. 실행 -> select: executeQuery()
			// insert, update, delete:executeUpdate()
			pstmt.executeUpdate();
			// 6. 표시 -> 오류가 없으면 정상처리
			// TODO: handle exception
				// 7. 닫기
				FreeDBUtil.close(con, pstmt);
			}
	}

