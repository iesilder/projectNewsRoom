package com.webjjang.board.service;

import com.webjjang.board.dao.QNABoardDAO;

public class QNABoardDeleteService {
	
	public void delete(int no) throws Exception {
//		System.out.println("QNABoardDeleteService.service()");
		QNABoardDAO qnaBoardDAO = new QNABoardDAO();
		qnaBoardDAO.delete(no);
		
	}
}
