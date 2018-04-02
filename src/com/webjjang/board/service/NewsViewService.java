package com.webjjang.board.service;

import java.sql.SQLException;

import com.webjjang.board.controller.ProcessInterface;
import com.webjjang.board.dao.NewsDAO;
import com.webjjang.board.dto.NewsDTO;

public class NewsViewService implements ProcessInterface{
	
	public NewsDTO process(int no) throws SQLException{
//		System.out.println("NewsViewService.process()");
		NewsDTO newsDTO = null;
		NewsDAO newsDAO = new NewsDAO();
		newsDAO.increase(no);
		newsDTO = newsDAO.view(no);
		return newsDTO;
	}

	@Override
	public Object process(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
