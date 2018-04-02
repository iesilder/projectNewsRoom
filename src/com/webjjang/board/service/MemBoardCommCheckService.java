package com.webjjang.board.service;

import com.webjjang.board.controller.ProcessInterface;
import com.webjjang.board.dao.MemBoardCommDAO;
import com.webjjang.board.dto.MemBoardCommDTO;

public class MemBoardCommCheckService implements ProcessInterface {

	public MemBoardCommDTO process(int commno, String id, int no) throws Exception {
		// TODO Auto-generated method stub
		MemBoardCommDTO boardCommDTO = new MemBoardCommDTO();
		MemBoardCommDAO boardCommDAO = new MemBoardCommDAO();
		boardCommDTO = boardCommDAO.check(commno, id, no);
		return boardCommDTO;
	}

	@Override
	public Object process(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
