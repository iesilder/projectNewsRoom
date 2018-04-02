package com.webjjang.board.service;

import com.webjjang.board.dao.FreeBoardDAO;



public class FreeBoardUpdateService {
	public void update(String title, String content, String writer, String menu) throws Exception {
		FreeBoardDAO freeBoardDAO = new FreeBoardDAO();
		freeBoardDAO.Update(title, content, writer, menu);
	}
}
