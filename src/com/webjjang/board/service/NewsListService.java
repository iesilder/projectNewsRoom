package com.webjjang.board.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.board.controller.ProcessInterface;
import com.webjjang.board.dao.NewsDAO;
import com.webjjang.board.dto.NewsDTO;

public class NewsListService implements ProcessInterface {
	
	public List<NewsDTO> process() throws SQLException {
//		System.out.println("NewsListService.process()");
		List<NewsDTO> list = new ArrayList<>();
		NewsDAO newsDAO= new NewsDAO();
		list = newsDAO.list();
		return list;
		
	}

	@Override
	public Object process(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
