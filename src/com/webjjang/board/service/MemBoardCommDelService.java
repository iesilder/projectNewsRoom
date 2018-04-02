package com.webjjang.board.service;

import com.webjjang.board.controller.ProcessInterface;
import com.webjjang.board.dao.MemBoardCommDAO;

public class MemBoardCommDelService implements ProcessInterface{

	public void process(int delNo) throws Exception{
		// TODO Auto-generated method stub
		MemBoardCommDAO boardCommDAO = new MemBoardCommDAO();
		boardCommDAO.delete(delNo);
	}

	@Override
	public Object process(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
