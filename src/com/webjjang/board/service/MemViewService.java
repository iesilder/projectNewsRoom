package com.webjjang.board.service;

import com.webjjang.board.controller.ProcessInterface;
import com.webjjang.board.dao.MemDAO;
import com.webjjang.board.dto.MemberDTO;

public class MemViewService implements ProcessInterface{

	public MemberDTO process(String inputId) throws Exception{
		MemberDTO memberDTO = null;
		MemDAO memDAO = new MemDAO();
		memberDTO = memDAO.view(inputId);
		return memberDTO;
	}

	@Override
	public Object process(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
