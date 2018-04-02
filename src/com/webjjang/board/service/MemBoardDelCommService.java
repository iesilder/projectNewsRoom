package com.webjjang.board.service;

import com.webjjang.board.controller.ProcessInterface;
import com.webjjang.board.dao.MemBoardCommDAO;

public class MemBoardDelCommService implements ProcessInterface {

	public void process(int commno, int no) throws Exception {
		// TODO Auto-generated method stub
		MemBoardCommDAO boardCommDAO = new MemBoardCommDAO();
		boardCommDAO.delete(commno, no);
	}

	@Override
	public Object process(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
