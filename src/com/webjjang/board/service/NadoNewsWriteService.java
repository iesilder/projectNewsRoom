package com.webjjang.board.service;

import com.webjjang.board.dao.NadoNewsBoardDAO;


//write메서드를 호출하고 매개변수를 넘겨준다.
public class NadoNewsWriteService {
	NadoNewsBoardDAO boardDAO = new NadoNewsBoardDAO();
	
	public void write(String title, String content, String writer) throws Exception{
		boardDAO.write(title, content, writer);
	}
}
