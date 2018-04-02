package com.webjjang.board.service;

import java.util.ArrayList;
import java.util.List;

import com.webjjang.board.dao.NadoNewsBoardDAO;
import com.webjjang.board.dto.NadoNewsBoardDTO;

//list 메서드를 호출한다.
public class NadoNewsListService  {
	List<NadoNewsBoardDTO> list = new ArrayList<NadoNewsBoardDTO>();

	public List<NadoNewsBoardDTO> process() throws Exception {
		NadoNewsBoardDAO boardDAO = new NadoNewsBoardDAO();
		list = boardDAO.list();
		return list;
	}
}
