package com.webjjang.board.service;

import com.webjjang.board.dao.NadoNewsBoardDAO;

//update 메서드를 호출한다.
public class NadoNewsUpdateService {
	public void update(String title, String content, String writer, int no) throws Exception {
		NadoNewsBoardDAO nadoNewsBoardDAO = new NadoNewsBoardDAO();
		nadoNewsBoardDAO.update(title, content, writer, no);
	}
}
