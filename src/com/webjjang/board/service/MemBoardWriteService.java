package com.webjjang.board.service;

import com.webjjang.board.controller.ProcessInterface;
import com.webjjang.board.dao.MemBoardDAO;

public class MemBoardWriteService implements ProcessInterface {

	@Override
	public Object process(Object obj) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	public void process(String id, String title, String content) throws Exception{
		// TODO Auto-generated method stub
		MemBoardDAO memBoardDAO = new MemBoardDAO();
		memBoardDAO.write(id, title, content);

	}

}
