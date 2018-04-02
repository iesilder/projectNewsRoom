package com.webjjang.board.service;

import java.util.List;


import com.webjjang.board.dao.FreeBoardDAO;
import com.webjjang.board.dto.FreeBoardDTO;

public class FreeBoardListService {
		public List<FreeBoardDTO> process()throws Exception{
			List<FreeBoardDTO> list = null;
			//List 에 데이터를 가져와서 채우는 프로그램 작성
			//객체를 생성하고 호출
			FreeBoardDAO boardDAO = new FreeBoardDAO();
			list = boardDAO.list();
			return list;
		
	}
}
