package com.webjjang.board.service;

import com.webjjang.board.controller.ProcessInterface;
import com.webjjang.board.dao.MemDAO;
import com.webjjang.board.dto.MemberDTO;

public class MemLoginService implements ProcessInterface {

	public MemberDTO process(String id, String pw) throws Exception {
		// TODO Auto-generated method stub
		MemberDTO memberDTO = null;
		MemDAO memDAO = new MemDAO();
		memberDTO = memDAO.login(id, pw);
		return memberDTO;
	}

	@Override
	public Object process(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
