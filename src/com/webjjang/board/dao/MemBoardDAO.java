package com.webjjang.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.board.dto.MemBoardDTO;
import com.webjjang.util.DBUtil;

public class MemBoardDAO {

	public List<MemBoardDTO> list() throws Exception {
		// 객체 생성
		List<MemBoardDTO> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 1. 드라이버 확인 2. 드라이버 연결
		con = DBUtil.getConnection();
		// 3. sql 작성
		String sql = "select no, title, id, writedate from mem_board order by no desc";
		// 4. sql 객체 생성
		pstmt = con.prepareStatement(sql);
		// 5. sql 객체 실행
		rs = pstmt.executeQuery();
		// 6. 담기
		while (rs.next()) {
			if (list == null)
				list = new ArrayList<>();
			MemBoardDTO memBoardDTO = new MemBoardDTO(rs.getInt("no"), rs.getString("title"), rs.getString("id"),
					rs.getString("writedate"));
			list.add(memBoardDTO);
		}
		// 7. 닫기
		DBUtil.close(con, pstmt, rs);

		return list;
	}// end of list()

	public MemBoardDTO view(int obj) throws Exception {
		// TODO Auto-generated method stub
		MemBoardDTO memBoardDTO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		con = DBUtil.getConnection();

		String sql = "select no, title, content, id, writedate from mem_board where no = ?";

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, obj);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			memBoardDTO = new MemBoardDTO(rs.getInt("no"), rs.getString("title"), rs.getString("content"),
					rs.getString("id"), rs.getString("writedate"));
		}

		DBUtil.close(con, pstmt, rs);

		return memBoardDTO;
	}// end of view()

	public void write(String id, String title, String content) throws Exception {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtil.getConnection();

		String sql = "insert into mem_board(no,id, title, content) values(mem_board_seq.nextval,?, ?, ?)";

		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, title);
		pstmt.setString(3, content);

		pstmt.executeUpdate();

		DBUtil.close(con, pstmt);
	}// end fo write()

	public MemBoardDTO check(String id, int no) throws Exception {
		// TODO Auto-generated method stub
		MemBoardDTO memBoardDTO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		con = DBUtil.getConnection();

		String sql = "select no, title, content, id, writedate from mem_board where id=? and no=?";

		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setInt(2, no);
		rs = pstmt.executeQuery();
		if (rs.next())
			memBoardDTO = new MemBoardDTO(rs.getInt("no"), rs.getString("title"), rs.getString("content"),
					rs.getString("id"), rs.getString("writedate"));

		DBUtil.close(con, pstmt, rs);

		return memBoardDTO;
	}// end of check()

	public void update(String title, String content, int checkNo) throws Exception {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtil.getConnection();

		String sql = "update mem_board set title=?, content=? where no=?";

		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setInt(3, checkNo);

		pstmt.executeUpdate();

	}// end of update()

	public void delete(int delNo) throws Exception {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtil.getConnection();

		String sql = "delete from mem_board where no = ?";

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, delNo);

		pstmt.executeUpdate();

		DBUtil.close(con, pstmt);

	}// end of delete()

}
