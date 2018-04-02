package com.webjjang.view.board;

import java.util.List;

import com.webjjang.board.dto.MemberDTO;
import com.webjjang.util.OutUtil;

public class MemPrint {

	public void list(List<MemberDTO> list) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardPrint.list()");
		OutUtil.printMenu("아이디\t 이름\t 닉네임\t 전화\t\t 이메일\t\t 회원등급", "*", 90);

		for (MemberDTO memberDTO : list) {
			System.out.println("" + "ID: " + memberDTO.getId() + "\n" + "이름: " + memberDTO.getName() + "\n" + "닉네임: "
					+ memberDTO.getNick() + "\n" + "전화번호: " + memberDTO.getTel() + "\n" + "Email: "
					+ memberDTO.getEmail() + "\n" + "회원등급: " + memberDTO.getGrade());
			OutUtil.repeatChar("-", 30);
		}
	}

}
