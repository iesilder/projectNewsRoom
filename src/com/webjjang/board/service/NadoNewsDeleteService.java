package com.webjjang.board.service;

import com.webjjang.board.dao.NadoNewsBoardDAO;

//delete 메서드를 호출한다.
public class NadoNewsDeleteService {
	public void delete(int no) throws Exception {
		NadoNewsBoardDAO nadoNewsBoardDAO = new NadoNewsBoardDAO();
		nadoNewsBoardDAO.delete(no);
	}
}
