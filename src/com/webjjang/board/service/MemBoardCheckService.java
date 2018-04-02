package com.webjjang.board.service;

import com.webjjang.board.controller.ProcessInterface;
import com.webjjang.board.dao.MemBoardDAO;
import com.webjjang.board.dto.MemBoardDTO;

public class MemBoardCheckService implements ProcessInterface {

	public MemBoardDTO process(String id, int no) throws Exception {
		// TODO Auto-generated method stub
		MemBoardDTO memBoardDTO = null;
		MemBoardDAO memBoardDAO = new MemBoardDAO();
		memBoardDTO = memBoardDAO.check(id, no);
		return memBoardDTO;
	}

	@Override
	public Object process(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
