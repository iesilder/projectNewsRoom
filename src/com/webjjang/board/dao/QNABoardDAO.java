package com.webjjang.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.board.dto.QNABoardDTO;
import com.webjjang.util.DBUtil;



public class QNABoardDAO {

	// 오라클 DB와 연결과 관련된 정보는 이미 DBUtil에서 선언을 한 상태.
	
	// 글 리스트를 가져오는 메서드를 작성한다.
	public List<QNABoardDTO> list() throws Exception {
//		System.out.println("QNABoardDAO.list()");
		
		List<QNABoardDTO> list = null;
		
		// RDBMS에서 데이터를 가져오는 프로그램을 작성한다.
		// 필요한 객체를 선언
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 드라이버 확인 및 연결
			con = DBUtil.getConnection();					
			// SQL 작성(작성한 글 목록)
			String sql = "select no, title, writer,"
					+ " writedate, hit from QNABoard"
					+ " order by no desc";
			// 처리문 객체
			pstmt = con.prepareStatement(sql);
			// 결과 출력
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// 데이터가 있는데 list가 null이라면 생성한다.
				if(list==null) list = new ArrayList<>();
				// 데이터 하나를 담을 수 있는 BoardDTO 객체 생성
				QNABoardDTO qnaBoardDTO = new QNABoardDTO
						// 데이터를 rs에서 꺼내서 BoardDTO에다 담는다.
						(rs.getInt("no"),
						rs.getString("title"),
						rs.getString("writer"),
						rs.getString("writedate"),
						rs.getInt("hit"));
//				System.out.println(qnaBoardDTO);
					// list에 dto를 담아낸다.
					list.add(qnaBoardDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 객체 닫기 (글 리스트 부분은 결과까지 닫아야 한다.)
				DBUtil.close(con, pstmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		System.out.println(list);
		return list;
	}
	
	// 글 번호에 맞는 글보기 데이터를 가져오는 메서드
	public QNABoardDTO view(int no) throws Exception {
//		System.out.println("QNABoardDAO.view()");
		QNABoardDTO qnaBoardDTO = null;
		// 오라클에서 데이터를 가져와서 채우는 프로그램 작성
		// 사용할 객체를 선언 
		Connection con = null; // 연결 객체
		PreparedStatement pstmt = null; // 처리문 객체
		ResultSet rs = null; // 결과 객체
		con = DBUtil.getConnection();
		// 3. sql 작성
		String sql = "select no, title, content,"
				+ " writer, writedate, hit"
				+ " from QNABoard where no = ? ";	// 변하는 데이터 대신 ? 사용
		// 4. 처리문 객체 작성
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, no); // 첫 번째 ?에 no를 int로 셋팅 
		// 5. 실행
		rs = pstmt.executeQuery();
		// 6. 표시 (rs에서 꺼내서 BoardDTO에 담는다.
		if(rs.next()) {
			// 생성자가 만들어져 있어야 한다.
			qnaBoardDTO = new QNABoardDTO
					(rs.getInt("no"),
					rs.getString("title"),
					rs.getString("content"),
					rs.getString("writer"),
					rs.getString("writedate"),
					rs.getInt("hit"));

			DBUtil.close(con, pstmt, rs);

				// TODO: handle exception
		}
		
	return qnaBoardDTO;
	}
	
	 
	
	// 조회수를 1 증가 시키는 메서드 -> 글 번호를 받아서 글 번호에 맞는 조회수 증가
	public void increase(int no) throws Exception {
//		System.out.println("QNABoardDAO.increase()");	
		// 사용할 객체를 선언 
		Connection con = null; // 연결 객체
		PreparedStatement pstmt = null; // 처리문 객체		
		con = DBUtil.getConnection();
		// 3. sql 작성
		String sql = "update QNABoard set hit = hit + 1"
				+ " where no = ? ";	// 변하는 데이터 대신 ? 사용
		// 4. 처리문 객체 작성
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, no); // 첫 번째 ?에 no를 int로 셋팅 
		// 5. 실행 -> select : executeQuery, 
		// insert, update, delete : excecuteUpdate 
		pstmt.executeUpdate();
		// 6. 표시 (rs에서 꺼내서 BoardDTO에 담는다.
				DBUtil.close(con, pstmt);

		
			
	}

	// 글쓰기 처리
	public void write(QNABoardDTO qnaBoardDTO) throws SQLException {
		// TODO Auto-generated method stub
//		System.out.println("QNABoardDAO.write()");
		Connection con = null;
		PreparedStatement pstmt = null;
		
		con = DBUtil.getConnection();
		// sql 작성하기
		String sql = "insert into QNABoard"
				+ "  (no, title, content, writer)"
				+ "  values(QNABoard_seq.nextval, ? , ? , ?) ";
		// 처리문 객체를 작성한다!!
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, qnaBoardDTO.getTitle());
		pstmt.setString(2, qnaBoardDTO.getContent());
		pstmt.setString(3, qnaBoardDTO.getWriter());
		// 처리 실행
		pstmt.executeUpdate();

		DBUtil.close(con, pstmt);
			} 

	
	// 게시판 글 수정하는 메서드
	public void update(String title, String content, String writer, int no) throws Exception {
//		System.out.println("QNABoardDAO.update()");
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtil.getConnection();
		// sql 작성하기
		String sql = "update QNABoard "
				+ "  set title= ? , content = ? , writer = ? "
				+ "  where no = ? ";
		// 처리문 객체를 작성하기
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, writer);
		pstmt.setInt(4, no);
		// 처리 및 실행
		pstmt.executeUpdate();

		DBUtil.close(con, pstmt);

		
	}
	
	// 게시판의 글을 삭제하는 메서드
	public void delete(int no) throws Exception {
//		System.out.println("QNABoardDAO.delete()");
		Connection con = null;
		PreparedStatement pstmt = null;
			
		// 연결 작업		
		// 연결하기
		con = DBUtil.getConnection();
		// sql 작성하기 (삭제문)
		String sql = "delete from QNABoard where no = ? ";
		// 처리문 객체 작성하기
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, no);
		// 처리 및 실행			
		pstmt.executeUpdate();

		
		// 객체 닫기
		DBUtil.close(con, pstmt);

		
	}



}
