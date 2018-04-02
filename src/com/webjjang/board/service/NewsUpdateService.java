package com.webjjang.board.service;

import com.webjjang.board.controller.ProcessInterface;
import com.webjjang.board.dao.NewsDAO;

public class NewsUpdateService implements ProcessInterface {
	
	public void process(int no, String title, String url, String content) throws Exception {
		// TODO Auto-generated method stub
//		System.out.println("NewsUpdateService.update()");
		NewsDAO newsDAO = new NewsDAO();
		newsDAO.update(no,title,url,content);
	}

	@Override
	public Object process(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
