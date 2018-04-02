package com.webjjang.board.service;

import java.util.List;

import com.webjjang.board.controller.ProcessInterface;
import com.webjjang.board.dao.MemDAO;
import com.webjjang.board.dto.MemberDTO;

public class MemListService implements ProcessInterface {

	public List<MemberDTO> process() throws Exception {
		// TODO Auto-generated method stub
		List<MemberDTO> list = null;
		MemDAO memDAO = new MemDAO();
		list = memDAO.list();
		return list;
	}

	@Override
	public Object process(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
