package com.webjjang.board.service;

import java.util.List;

import com.webjjang.board.controller.ProcessInterface;
import com.webjjang.board.dao.MemBoardDAO;
import com.webjjang.board.dto.MemBoardDTO;

public class MemBoardListService implements ProcessInterface{

	public List<MemBoardDTO> process() throws Exception{
		List<MemBoardDTO> list = null;
		MemBoardDAO memBoardDAO = new MemBoardDAO();
		list = memBoardDAO.list();
		return list;
	}
	
	@Override
	public Object process(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
