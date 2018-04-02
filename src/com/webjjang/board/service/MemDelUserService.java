package com.webjjang.board.service;

import com.webjjang.board.controller.ProcessInterface;
import com.webjjang.board.dao.MemDAO;

public class MemDelUserService implements ProcessInterface{

	public void process(String delId, String delPw) throws Exception{
		// TODO Auto-generated method stub
		MemDAO memDAO = new MemDAO();
		memDAO.delete(delId, delPw);
	}

	@Override
	public Object process(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
