package com.webjjang.board.service;

import com.webjjang.board.dao.QNABoardDAO;
import com.webjjang.board.dto.QNABoardDTO;

public class QNABoardWriteService {
	public void process(QNABoardDTO qnaBoardDTO) throws Exception {
//		System.out.println("QNABoardWriteService.process()");
		// list에 데이터를 가져와서 채우는 프로그램을 작성. 그리고 채운것을 return에서 넘긴다.
		// 객체를 생성하고 호출한다.
		QNABoardDAO qnaBoardDAO = new QNABoardDAO();
		qnaBoardDAO.write(qnaBoardDTO);
	}
}

