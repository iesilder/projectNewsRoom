package com.webjjang.board.service;

import com.webjjang.board.controller.ProcessInterface;
import com.webjjang.board.dao.MemBoardDAO;
import com.webjjang.board.dto.MemBoardDTO;

public class MemBoardViewService implements ProcessInterface {

	public MemBoardDTO process(int no) throws Exception {
		MemBoardDTO memBoardDTO = null;
		MemBoardDAO memBoardDAO = new MemBoardDAO();
		memBoardDTO = memBoardDAO.view(no);
		return memBoardDTO;
	}

	@Override
	public Object process(Object obj) throws Exception {
		// TODO Auto-generated method stub
		MemBoardDTO memBoardDTO = null;
		MemBoardDAO memBoardDAO = new MemBoardDAO();
		memBoardDTO = memBoardDAO.view((int) obj);
		return memBoardDTO;
	}

}
