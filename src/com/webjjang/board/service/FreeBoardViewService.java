package com.webjjang.board.service;



import com.webjjang.board.dao.FreeBoardDAO;
import com.webjjang.board.dto.FreeBoardDTO;

//controller -=> service -> dao

public class FreeBoardViewService {
	// 1. 글번호에 맞는 글의 조회수를 1 증가 시킨다.
	// 2. 글번호를 받아서 알맞는 데이터를 가져온다
		public FreeBoardDTO process(int no)throws Exception {
			
			FreeBoardDTO boardDTO=null;
			//글번호에 맞는 보드디티오에 데이터를 가져와서 채우는 프로그램 작성
			//객체 생성하고 호출
			FreeBoardDAO boardDAO = new FreeBoardDAO();
			//1. 1증가시킨다
			boardDAO.increase(no);
			//2. 글을 가져온다.
			boardDTO = boardDAO.view(no);
			
			return boardDTO;
			
		}

	
}
	


