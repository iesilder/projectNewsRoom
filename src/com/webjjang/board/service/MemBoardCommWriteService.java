package com.webjjang.board.service;

import com.webjjang.board.controller.ProcessInterface;
import com.webjjang.board.dao.MemBoardCommDAO;

public class MemBoardCommWriteService implements ProcessInterface {

	
	@Override
	public Object process(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void process(String id, String title, int no) throws Exception{
		// TODO Auto-generated method stub
		MemBoardCommDAO boardCommDAO = new MemBoardCommDAO();
		boardCommDAO.write(id, title, no);
		
	}

}
