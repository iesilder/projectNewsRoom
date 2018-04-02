package com.webjjang.board.service;

import com.webjjang.board.dao.NadoNewsBoardDAO;
import com.webjjang.board.dto.NadoNewsBoardDTO;

//increase와 view 메서드를 호출한다.
public class NadoNewsViewService {
	public NadoNewsBoardDTO process(int no) throws Exception{
		NadoNewsBoardDAO boardDAO = new NadoNewsBoardDAO();
		boardDAO.increase(no);
		return boardDAO.view(no);
	}
}
