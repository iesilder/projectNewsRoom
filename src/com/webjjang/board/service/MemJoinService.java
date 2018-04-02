package com.webjjang.board.service;

import com.webjjang.board.controller.ProcessInterface;
import com.webjjang.board.dao.MemDAO;

public class MemJoinService implements ProcessInterface {

	public void process(String joinId, String joinPw, String joinName, String joinNick, String joinTel,
			String joinEmail) throws Exception{
		// TODO Auto-generated method stub
		MemDAO memDAO = new MemDAO();
		memDAO.join(joinId, joinPw, joinName, joinNick, joinTel, joinEmail);
	}

	@Override
	public Object process(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
