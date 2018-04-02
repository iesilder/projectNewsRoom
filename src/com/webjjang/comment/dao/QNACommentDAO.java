package com.webjjang.comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.comment.dto.QNACommentDTO;
import com.webjjang.util.DBUtil;

public class QNACommentDAO {
	
	// 댓글을 보여주는 리스트를 작성한다.
	public List<QNACommentDTO> list(Object qno) throws Exception {
		System.out.println(qno);
		List<QNACommentDTO> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		con = DBUtil.getConnection();
		
		String sql = "select no, qno, content, writer, writedate"
				+ " from QNAComment where qno = ? order by no asc ";
		
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, (Integer)qno);
		
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			if (list == null)
				list = new ArrayList<>();
			QNACommentDTO qnaCommentDTO = new QNACommentDTO
					(rs.getInt("no"), rs.getString("content"), rs.getString("writer"), rs.getString("writedate"));
			list.add(qnaCommentDTO);
		}
//		System.out.println(list);
		return list;
	}

	// 댓글을 보여주는 메서드를 작성한다.
	public QNACommentDTO view(int qno, int no) throws Exception {
//		System.out.println("QNACommentDAO.view()");
		
		QNACommentDTO qnaCommentDTO = null;
		// RDBMS에서 데이터를 가져오는 프로그램을 작성한다.
		Connection con;
		PreparedStatement pstmt;
		ResultSet rs;
				
		// 댓글을 보여주는 메서드를 작성한다.
		// 드라이버 확인 및 연결
		con = DBUtil.getConnection();
		// 처리할 명령과 관련된 sql을 작성한다.
		String sql = "select no, content, writer, writedate"
				+ "  from QNAComment where qno = ? and no = ? ";
		// 처리문 객체를 작성한다.
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, qno);
		pstmt.setInt(2, no);
		// 실행한다.
		rs = pstmt.executeQuery();
		// 결과 출력 (rs에서 꺼내어 DTO에 담는다)
		if(rs.next()) {
			qnaCommentDTO = new QNACommentDTO
					(rs.getInt("no"),
					rs.getString("content"),
					rs.getString("writer"),
					rs.getString("writedate"));
					
		}
	return qnaCommentDTO;	
	}
	
	// 댓글을 작성하는 메서드를 작성한다.
	public void write(int qno, String content, String writer) throws Exception {
//		System.out.println("QNACommentDAO.write()");
		Connection con = null;
		PreparedStatement pstmt = null;
		
		// 연결 하기
		con = DBUtil.getConnection();
		// SQL 작성하기
		String sql = "insert into QNAComment(no, qno, content, writer)"
				+ "  values(QNAComment_seq.nextval, ? , ? , ? )";
		// 처리문 객체를 작성한다.
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, qno);
		pstmt.setString(2, content);
		pstmt.setString(3, writer);
		// 처리 수행
		pstmt.executeUpdate();

		DBUtil.close(con, pstmt);
		
	}
	
	
	public void delete(int no, int qno) throws Exception {
//		System.out.println("QNAComment.delete()");
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtil.getConnection();
		// sql 작성
		String sql = "delete from QNAComment where no = ? and qno = ? ";
		// 처리문 작성
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, no);
		pstmt.setInt(2, qno);
		// 처리 완료
		pstmt.executeUpdate();

		DBUtil.close(con, pstmt);

	}
	
}
