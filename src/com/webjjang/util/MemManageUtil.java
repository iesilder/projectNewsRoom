package com.webjjang.util;

import java.util.regex.Pattern;

import com.webjjang.board.dto.MemBoardCommDTO;
import com.webjjang.board.dto.MemBoardDTO;
import com.webjjang.board.dto.MemberDTO;

public class MemManageUtil {

	public String update(String menu, String field, MemberDTO userDTO) {
		String str = InUtil.getMenu(menu);

		switch (field) {
		case "pw":
			if (str.trim().equals(""))
				str = userDTO.getPw();
			break;
		case "name":
			if (str.trim().equals(""))
				str = userDTO.getName();
			break;
		case "nick":
			if (str.trim().equals(""))
				str = userDTO.getNick();
			break;
		case "tel":
			if (str.trim().equals(""))
				str = userDTO.getTel();
			break;
		case "email":
			if (str.trim().equals(""))
				str = userDTO.getEmail();
			break;
		}

		return str;
	}

	public String updateBoard(String menu, String field) {
		String str = InUtil.getMenu(menu);
		MemBoardDTO memBoardDTO = new MemBoardDTO();

		switch (field) {
		case "title":
			if (str.trim().equals(""))
				str = memBoardDTO.getTitle();
			break;
		case "content":
			if (str.trim().equals(""))
				str = memBoardDTO.getContent();
			break;

		}

		return str;
	}

	// public String updatePw(String menu, MemberDTO userDTO) {
	// String updatePw = InUtil.getMenu(menu);
	// if (updatePw.trim().equals(""))
	// updatePw = userDTO.getPw();
	// return updatePw;
	// }
	//
	// public String updateName(String menu, MemberDTO userDTO) {
	// String updateName = InUtil.getMenu("이름 ");
	// if (updateName.trim().equals(""))
	// updateName = userDTO.getName();
	// return updateName;
	// }
	//
	// public String updateNick(String menu, MemberDTO userDTO) {
	// String updateNick = InUtil.getMenu("닉네임 ");
	// if (updateNick.trim().equals(""))
	// updateNick = userDTO.getNick();
	// return updateNick;
	// }
	//
	// public String updateTel(String menu, MemberDTO userDTO) {
	// String updateTel = InUtil.getMenu("전화번호 ");
	// if (updateTel.trim().equals(""))
	// updateTel = userDTO.getTel();
	// return updateTel;
	// }
	//
	// public String updateEmail(String menu, MemberDTO userDTO) {
	// String updateEmail = InUtil.getMenu("이메일 ");
	// if (updateEmail.trim().equals(""))
	// updateEmail = userDTO.getEmail();
	// return updateEmail;
	// }

	public String patternCheck(String pattern, String str, String err) {
		String info = InUtil.getMenu(str);
		// id를 검사하기 위한 프로그램
		// id에 영소문자와 숫자만 입력하도록 하기 위한 처리
		String p = pattern;
		boolean check = Pattern.matches(p, info);
		if (check == true) {

		} else {
			info = null;
			System.out.println(err);
		}
		return info;
	}

	public String rePatternCheck(String pattern, String str, String err) {
		String info = InUtil.getMenu(str);
		// id를 검사하기 위한 프로그램
		// id에 영소문자와 숫자만 입력하도록 하기 위한 처리
		String p = pattern;
		boolean check = Pattern.matches(p, info);
		if (check != true) {

		} else {
			info = null;
			System.out.println(err);
		}
		return info;
	}

	// public boolean checkId(String str) {
	// boolean check = true;
	// if (str == null)
	// check = false;
	// if (str.length() < 6) {
	// System.out.println("아이디는 6자리 이상이어야 합니다. ");
	// check = false;
	// }
	// if (str.length() > 11) {
	// System.out.println("아이디는 10자리를 넘어서는 안됩니다. ");
	// check = false;
	// }
	// return check;
	// }
	//
	// public boolean checkPw(String str) {
	// boolean check = true;
	// if (str == null)
	// check = false;
	// if (str.length() < 6) {
	// System.out.println("비밀번호는 비밀번호는 최소 6자리가 넘어야 합니다.");
	// check = false;
	// }
	// if (str.length() > 17) {
	// System.out.println("비밀번호는 16자리를 넘을 수 없습니다.");
	// check = false;
	// }
	// return check;
	// }

	public boolean nullCheck(String s) {
		boolean check = true;
		if (s == null)
			check = false;
		return check;
	}

	public void printUserInfo(MemberDTO userDTO) {
		OutUtil.repeatChar("*", 30);
		System.out.println("ID:\t" + userDTO.getId() + "\n" + "이름:\t" + userDTO.getName() + "\n" + "닉네임:\t"
				+ userDTO.getNick() + "\n" + "전화번호:\t" + userDTO.getTel() + "\n" + "이메일:\t" + userDTO.getEmail() + "\n"
				+ "회원등급:\t" + userDTO.getGrade());
		OutUtil.repeatChar("*", 30);
	}

	public void printBoardInfo(MemBoardDTO memBoardDTO) {
		OutUtil.repeatChar("*", 30);
		System.out.println("글번호:\t" + memBoardDTO.getNo() + "\n" + "제목:\t" + memBoardDTO.getTitle() + "\n" + "글내용:\t"
				+ memBoardDTO.getContent() + "\n" + "ID:\t" + memBoardDTO.getId() + "\n" + "작성일:\t"
				+ memBoardDTO.getWritedate() + "\n");
		OutUtil.repeatChar("*", 30);
	}

	public void printBoardSimpleInfo(MemBoardDTO memBoardDTO) {
		OutUtil.repeatChar("*", 30);
		System.out.println("글번호:\t" + memBoardDTO.getNo() + "\n" + "제목:\t" + memBoardDTO.getTitle() + "\n" + "ID:\t"
				+ memBoardDTO.getId() + "\n" + "작성일:\t" + memBoardDTO.getWritedate() + "\n");
		OutUtil.repeatChar("*", 30);
	}

	public void printBoardInfo(MemBoardCommDTO memBoardDTO) {
		OutUtil.repeatChar("*", 30);
		System.out.println("댓글번호:\t" + memBoardDTO.getCommno() + "\n" + "글내용:\t" + memBoardDTO.getContent() + "\n"
				+ "ID:\t" + memBoardDTO.getId() + "\n" + "작성일:\t" + memBoardDTO.getWritedate() + "\n");
		OutUtil.repeatChar("*", 30);
	}

}// end of class MemManageUtil
