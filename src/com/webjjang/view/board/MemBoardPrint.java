package com.webjjang.view.board;

import java.util.List;

import com.webjjang.board.dto.MemBoardDTO;

public class MemBoardPrint {

	public void list(List<MemBoardDTO> list) {
		// TODO Auto-generated method stub
		for (MemBoardDTO memBoardDTO : list)
			System.out.println("" + "글번호:" + memBoardDTO.getNo() + "\n" + "제목:" + memBoardDTO.getTitle() + "\n" + "ID:"
					+ memBoardDTO.getId() + "\n" + "작성일:" + memBoardDTO.getWritedate() + "\n");
	}

}
