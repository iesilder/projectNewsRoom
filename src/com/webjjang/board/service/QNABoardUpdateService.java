package com.webjjang.board.service;

import com.webjjang.board.dao.QNABoardDAO;


public class QNABoardUpdateService {
	public void update(String title, String content, String writer, int no) throws Exception {
//		System.out.println("QNABoardUpdateService.process()");
		
		// list에 데이터를 가져와서 채우는 프로그램을 작성, 채운것을 return
		// 객체 생성 및 호출
		QNABoardDAO qnaBoardDAO = new QNABoardDAO();
		qnaBoardDAO.update(title, content, writer, no);
		
	}
		
}