package com.webjjang.view.board;

import java.util.List;

import com.webjjang.board.dto.MemBoardCommDTO;

public class MemBoardCommPrint {

	public void list(List<MemBoardCommDTO> list) {
		// TODO Auto-generated method stub
		for (MemBoardCommDTO memboardCommDTO : list)
			System.out.println("" + "ID: " + memboardCommDTO.getId() + "\n" + "댓글:" + memboardCommDTO.getContent()
					+ "\n" + "작성일: " + memboardCommDTO.getWritedate());
	}

}
