package com.webjjang.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.board.dto.MemBoardCommDTO;
import com.webjjang.util.DBUtil;

public class MemBoardCommDAO {

	public List<MemBoardCommDTO> list(int no) throws Exception {
		// TODO Auto-generated method stub
		List<MemBoardCommDTO> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		con = DBUtil.getConnection();

		String sql = "select commno, no, content, id, writedate from mem_board_comm where no=? order by commno asc";

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, no);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			if (list == null)
				list = new ArrayList<>();
			MemBoardCommDTO memBoardCommDTO = new MemBoardCommDTO(rs.getInt("commno"), rs.getString("content"),
					rs.getString("id"), rs.getString("writedate"));
			list.add(memBoardCommDTO);
		}

		DBUtil.close(con, pstmt, rs);
		return list;
	}// end of list()

	public void delete(int delNo) throws Exception {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtil.getConnection();

		String sql = "delete from mem_board_comm where no = ?";

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, delNo);

		pstmt.executeUpdate();

		DBUtil.close(con, pstmt);
	}// end of delete()

	public void write(String id, String title, int no) throws Exception {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtil.getConnection();

		String sql = "insert into mem_board_comm(commno,content,no,id) values(mem_board_comm_seq.nextval,?,?,?) ";

		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setInt(2, no);
		pstmt.setString(3, id);
		pstmt.executeUpdate();

		DBUtil.close(con, pstmt);

	}// end of write()

	public MemBoardCommDTO check(int commno, String id, int no) throws Exception {
		// TODO Auto-generated method stub
		MemBoardCommDTO boardCommDTO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		con = DBUtil.getConnection();

		String sql = "select commno, no, content, id, writedate from mem_board_comm where commno = ? and id = ? and no = ?";

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, commno);
		pstmt.setString(2, id);
		pstmt.setInt(3, no);

		rs = pstmt.executeQuery();

		if (rs.next()) {
			boardCommDTO = new MemBoardCommDTO(rs.getInt("commno"), rs.getString("content"), rs.getString("id"),
					rs.getString("writedate"));
		}

		DBUtil.close(con, pstmt, rs);

		return boardCommDTO;
	}// end of check()

	public void delete(int commno, int no) throws Exception {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtil.getConnection();

		String sql = "delete from mem_board_comm where commno = ? and no = ?";

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, commno);
		pstmt.setInt(2, no);

		pstmt.executeUpdate();

		DBUtil.close(con, pstmt);
	}// end of delete()

}// end of class MemBoardCommDAO
