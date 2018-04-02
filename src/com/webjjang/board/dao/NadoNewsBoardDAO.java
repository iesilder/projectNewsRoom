package com.webjjang.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.board.dto.NadoNewsBoardDTO;
import com.webjjang.util.DBUtil;

public class NadoNewsBoardDAO {
	//클래스 멤버로 필요한 객체들을 선언해서 재사용해서 사용한다.
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private List<NadoNewsBoardDTO> list;
	private String sql;
	private NadoNewsBoardDTO boardDTO;

	//SQL을 입력받는 Getter와 Setter 메서드를 작성한다.
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getSql() {
		return sql;
	}

	//예외처리를 위해 Throws 선언
	public List<NadoNewsBoardDTO> list() throws Exception {
		
		//sql문 입력
		setSql("SELECT no, title, writer, to_char(writedate, 'yyyy-mm-dd') writedate, hit "
				+ " FROM nadoboard ORDER BY no DESC");

		//필요한 객체들 선언하기
		conn = DBUtil.getConnection();
		pstmt = conn.prepareStatement(getSql());
		rs = pstmt.executeQuery();

		//rs로 부터 데이터 가져와서 ArrayList에 입력
		while (rs.next()) {
			if (list == null) {
				list = new ArrayList<>();
			}
			boardDTO = new NadoNewsBoardDTO(rs.getInt("no"), rs.getString("title"), rs.getString("writer"),
					rs.getString("writedate"), rs.getInt("hit"));

			list.add(boardDTO);
		}
		
		DBUtil.close(conn, pstmt, rs);
		//데이터 리턴
		return list;
	}

	public NadoNewsBoardDTO view(int no) throws Exception {

		//SQL을 입력받고 필요한 객체들을 연결한다.
		conn = DBUtil.getConnection();
		setSql("SELECT no, title, content, writer, to_char(writedate, 'yyyy-mm-dd') writedate, hit FROM nadoboard WHERE no = ?");
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		rs = pstmt.executeQuery();

		//데이터 가져오기
		while (rs.next()) {
			boardDTO = new NadoNewsBoardDTO(rs.getInt("no"), rs.getString("title"), rs.getString("content"),
					rs.getString("writer"), rs.getString("writedate"), rs.getInt("hit"));
		}
		//객체 닫기
		DBUtil.close(conn, pstmt);
		// 데이터 리턴
		return boardDTO;
	}

	// 조회수를 1 증가하는 메서드
	public void increase(int no) throws Exception {
		
		//객체들을 연결하고 SQL 문을 입력 받는다.
		conn = DBUtil.getConnection();
		setSql("UPDATE nadoboard set hit = hit + 1 where no = ?");
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		//쿼리 업데이트
		pstmt.executeUpdate();
		//객체를 닫는다.
		DBUtil.close(conn, pstmt);
	}

	//새로운 기사를 작성하는 메서드
	public void write(String title, String content, String writer) throws Exception {

		//객체를 연결하고 SQL을 작성한다.
		conn = DBUtil.getConnection();
		setSql("INSERT INTO nadoboard(no, title, content, writer, writedate) "
				+ " VALUES(nadoboard_seq.nextval, ?, ?, ?, SYSDATE)");
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, writer);
		//쿼리 업데이트
		pstmt.executeUpdate();
		//객체를 닫는다.
		DBUtil.close(conn, pstmt);

	}

	// 기존의 기사를 업데이트 하는 메서드
	public void update(String title, String content, String writer, int no) throws Exception {

		//객체들을 연결한고 SQL을 입력 받는다.
		conn = DBUtil.getConnection();
		setSql("UPDATE nadoboard SET title = ?, content = ?, writer = ? WHERE no = ?");
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, writer);
		pstmt.setInt(4, no);
		//쿼리 업데이트
		pstmt.executeUpdate();

		//객체를 닫는다.
		DBUtil.close(conn, pstmt);

	}

	// 기존의 기사를 제거하는 메서드
	public void delete(int no) throws Exception {

		//객체들을 연결하고 SQL문을 작성한다.
		conn = DBUtil.getConnection();
		setSql("DELETE FROM nadoboard WHERE no = ?");
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		//쿼리 업데이트
		pstmt.executeUpdate();
		//객체들을 닫는다.
		DBUtil.close(conn, pstmt);

	}

}
