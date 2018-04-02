package com.webjjang.board.service;

import com.webjjang.board.controller.ProcessInterface;
import com.webjjang.board.dao.MemBoardDAO;

public class MemBoardUpdateService implements ProcessInterface{

	public void process(String title, String content, int checkNo) throws Exception {
		// TODO Auto-generated method stub
		MemBoardDAO memBoardDAO = new MemBoardDAO();
		memBoardDAO.update(title, content, checkNo);
	}

	@Override
	public Object process(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
