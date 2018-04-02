package com.webjjang.board.service;

import java.util.List;

import com.webjjang.board.dao.QNABoardDAO;
import com.webjjang.board.dto.QNABoardDTO;

public class QNABoardListService {

	public List<QNABoardDTO> process() throws Exception {
		// controller -> service -> DAO
//		System.out.println("QNABoardListService.process()");
		
		// 전달할 객체를 선언한다.
		List<QNABoardDTO> list = null;
		
		// 데이터를 DB에서 가져와서 채우기 위해 객체를 생성한다. 그리고 메서드를 호출하여 사용한다.
		QNABoardDAO qnaBoardDAO = new QNABoardDAO();
		list = qnaBoardDAO.list();		
		return list;
		
	}
}
