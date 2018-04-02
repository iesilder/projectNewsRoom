package com.webjjang.board.service;

import com.webjjang.board.dao.FreeBoardDAO;;

public class FreeBoardDeleteService  {
	public void delete(String menu) throws Exception{
		FreeBoardDAO freeBoardDAO = new FreeBoardDAO();
		freeBoardDAO.delete(menu);
	}
}
