package com.webjjang.board.service;

import java.util.List;

import com.webjjang.board.controller.ProcessInterface;
import com.webjjang.board.dao.MemBoardCommDAO;
import com.webjjang.board.dto.MemBoardCommDTO;

public class MemBoardCommListService implements ProcessInterface {

	public List<MemBoardCommDTO> process(int no) throws Exception {
		// TODO Auto-generated method stub
		List<MemBoardCommDTO> list = null;
		MemBoardCommDAO memBoardCommDAO = new MemBoardCommDAO();
		list = memBoardCommDAO.list(no);
		return list;
	}

	@Override
	public Object process(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
