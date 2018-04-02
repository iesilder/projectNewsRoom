package com.webjjang.board.service;

import java.sql.SQLException;

import com.webjjang.board.controller.ProcessInterface;
import com.webjjang.board.dao.NewsDAO;
import com.webjjang.board.dto.NewsDTO;

public class NewsWriteService implements ProcessInterface {
	
	public void process(NewsDTO newsDTO) throws SQLException {
		// TODO Auto-generated method stub
//		System.out.println("NewsWriteService.process()");
		NewsDAO newsDAO = new NewsDAO();
		newsDAO.write(newsDTO);
	}

	@Override
	public Object process(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
