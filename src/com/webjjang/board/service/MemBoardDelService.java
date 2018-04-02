package com.webjjang.board.service;

import com.webjjang.board.controller.ProcessInterface;
import com.webjjang.board.dao.MemBoardDAO;

public class MemBoardDelService implements ProcessInterface {

	public void prcess(int delNo) throws Exception {
		// TODO Auto-generated method stub
		MemBoardDAO boardDAO = new MemBoardDAO();
		boardDAO.delete(delNo);
	}

	@Override
	public Object process(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
