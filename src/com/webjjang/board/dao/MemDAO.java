package com.webjjang.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.board.dto.MemberDTO;
import com.webjjang.util.DBUtil;
import com.webjjang.util.OutUtil;

public class MemDAO {

	// 로그인
	// 로그인 객체인 memberDTO가 null이 아닐 경우 접속한 것으로 간주. null은 로그인 실패.
	public MemberDTO login(String id, String pw) throws Exception {
		// 객체 생성.
		MemberDTO memberDTO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 1. 드라이브 확인 2. 연결
		con = DBUtil.getConnection();
		// 3. sql 입력
		String sql = "select id, pw, name, nick, tel, email, grade from mem_manage where id=? and pw=?";
		// 4. sql 객체 생성
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id); // 받은 id를 입력
		pstmt.setString(2, pw); // 받은 pw를 입력
		// 5. sql 객체 실행
		rs = pstmt.executeQuery();
		// 6. 담기.
		if (rs.next()) {
			memberDTO = new MemberDTO(rs.getString("id"), rs.getString("pw"), rs.getString("name"),
					rs.getString("nick"), rs.getString("tel"), rs.getString("email"), rs.getInt("grade"));
		} else
			// memberDTO가 null값이라는 말이므로 다시 로그인하라는 안내문 출력.
			System.out.println("ID와 PW를 다시 확인하세요.");

		// 7. 실행 객체 닫기
		DBUtil.close(con, pstmt);

		// 로그인 정보를 controller에 보내주기 위해서 memberDTO를 리턴.
		return memberDTO;

	}// end of login()

	// 회원 명단 출력
	public List<MemberDTO> list() throws Exception {
		// 객체 생성
		List<MemberDTO> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 1. 드라이버 확인 2. 연결
		con = DBUtil.getConnection();
		// 3. sql 입력
		String sql = "select id, name, nick, tel, email, grade from mem_manage order by grade desc";
		// 4. sql 객체 생성.
		pstmt = con.prepareStatement(sql);
		// 5. sql 객체 실행
		rs = pstmt.executeQuery();
		// 6. ArrayList에 모든 회원 정보 담기.
		while (rs.next()) {
			if (list == null)
				list = new ArrayList<>();
			MemberDTO memberDTO = new MemberDTO(rs.getString("id"), rs.getString("name"), rs.getString("nick"),
					rs.getString("tel"), rs.getString("email"), rs.getInt("grade"));
			list.add(memberDTO);

		}
		// 7. 닫기
		DBUtil.close(con, pstmt, rs);
		return list;
	}// end of list()

	// 회원 찾기
	// 회원 아이디를 입력하면 관련 내용을 찾아주는 프로그램
	public MemberDTO view(String inputId) throws Exception {
		// 객체 생성
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDTO memberDTO = null;

		// 1. 드라이버 확인 2. 드라이버 연결
		con = DBUtil.getConnection();
		// 3. sql 입력
		String sql = "select id, name, nick, tel, email, grade from mem_manage where id =?";
		// 4. sql 객체 생성.
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, inputId); // 전달받은 id를 쿼리에 넣어준다.
		// 5. sql 객체 실행
		rs = pstmt.executeQuery();
		// 6. memberDTO에 결과물 담기
		if (rs.next()) {
			memberDTO = new MemberDTO(rs.getString("id"), rs.getString("name"), rs.getString("nick"),
					rs.getString("tel"), rs.getString("email"), rs.getInt("grade"));

			
		}
		// 7. 객체들 닫기
					DBUtil.close(con, pstmt, rs);
		return memberDTO;
	}// end of view()

	// 회원가입
	// 오라클의 모든 컬럼은 not null이므로 입력공간이 비어있을시 오류가 난다.
	// 단 ' '는 괜찮은듯?
	public void join(String joinId, String joinPw, String joinName, String joinNick, String joinTel, String joinEmail)
			throws Exception {
		// 객체 생성
		Connection con = null;
		PreparedStatement pstmt = null;

		// 1. 드라이버 확인 2. 드라이버 연결
		con = DBUtil.getConnection();
		// 3. sql
		String sql = "insert into mem_manage(id, pw, name, nick, tel, email, grade) values(?, ?, ?, ?, ?, ?, 1)";
		// 4. sql 객체 생성
		// 입력받은 각 값을 정확한 위치에 넣어준다.
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, joinId);
		pstmt.setString(2, joinPw);
		pstmt.setString(3, joinName);
		pstmt.setString(4, joinNick);
		pstmt.setString(5, joinTel);
		pstmt.setString(6, joinEmail);
		// 5. sql 객체 실행
		pstmt.executeUpdate();
		OutUtil.printTitle("가입이 완료되었습니다!!"); // 오류가 없을 시 가입완료 문구 출력

		// 7. 객체들 닫기
		DBUtil.close(con, pstmt);

	}// end of join()

	// update를 위한 메서드
	// 각각을 다 받아서 정확한 위치에 입력
	public MemberDTO update(String Id, String updatePw, String updateName, String updateNick, String updateTel,
			String updateEmail) throws Exception {
		// TODO Auto-generated method stub
		MemberDTO mmc = null;
		Connection con = null;
		PreparedStatement pstmt = null;

		// 1. 드라이버 확인 2. 드라이버 연결
		con = DBUtil.getConnection();
		// 3. sql
		String sql = "update mem_manage set pw=?, name=?, nick=?, tel=?, email=? where id=?";
		// 4. sql 객체 생성
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, updatePw);
		pstmt.setString(2, updateName);
		pstmt.setString(3, updateNick);
		pstmt.setString(4, updateTel);
		pstmt.setString(5, updateEmail);
		pstmt.setString(6, Id);
		// 5. sql 객체 실행
		pstmt.executeUpdate();
		
		mmc = view(Id);

		DBUtil.close(con, pstmt);

		return mmc;
	}// end of update()

	// 삭제를 위해 Id와 Pw를 가져와 쿼리에 넣는다.
	public void delete(String delId, String delPw) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;

		// 1. 드라이버 확인 2. 드라이버 실행
		con = DBUtil.getConnection();
		// 3. sql
		String sql = "delete from mem_manage where id=? and pw=?";
		// 4. sql 객체 생성
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, delId);
		pstmt.setString(2, delPw);
		// 5. sql 객체 실행
		pstmt.executeUpdate();

		// 6. 객체들 닫기
		DBUtil.close(con, pstmt);

	}// end of delete()

}// end of class MemDAO
