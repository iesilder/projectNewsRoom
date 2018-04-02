package com.webjjang.board.service;

import com.webjjang.board.dao.FreeBoardDAO;
import com.webjjang.board.dto.FreeBoardDTO;



public class FreeBoardWriteService {
		public void process  (FreeBoardDTO boardDTO)throws Exception {
			
			//객체를 생성하고 호출
			FreeBoardDAO boardDAO = new FreeBoardDAO();
			boardDAO.write(boardDTO);
			
		
	}
}
