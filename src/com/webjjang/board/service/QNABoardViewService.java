package com.webjjang.board.service;

import com.webjjang.board.dao.QNABoardDAO;
import com.webjjang.board.dto.QNABoardDTO;

// Controller -> service -> DAO로 왔다

public class QNABoardViewService {
	// 1. 글 번호에 맞는 글의 조회수를 1 증가 시킨다.
	// 2. 글 번호를 받아서 알맞는 데이터를 가져온다.
	public QNABoardDTO process(int no) throws Exception {
//		System.out.println("QNABoardViewService.process()");
		QNABoardDTO qnaBoardDTO = null;
		// list에 데이터를 가져와서 채우는 프로그램을 작성. 그리고 채운것을 return에서 넘긴다.
		// 객체를 생성하고 호출한다.
		QNABoardDAO qnaBoardDAO = new QNABoardDAO();
		// 1. 1 증가 시킨다.
		qnaBoardDAO.increase(no);
		// 2. 글을 가져온다.		
		qnaBoardDTO = qnaBoardDAO.view(no);
		return qnaBoardDTO;
}
	
}
