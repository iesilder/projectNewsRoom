package com.webjjang.view.board;

import java.util.List;


import com.webjjang.board.dto.FreeBoardDTO;
import com.webjjang.util.FreeOutUtil;

public class FreeBoardPrint {
	public void list(List<FreeBoardDTO> list) {
		System.out.println("\n\n                   *** 게시판 리스트  ***");
		
		System.out.println("------------------------------------------------------");
		System.out.println(" 번호              제목                     작성자                            작성일                  조회수");
		System.out.println("------------------------------------------------------");
		// 데이터 출력
		for(FreeBoardDTO boardDTO:list)
			System.out.printf("[%2d]%9s]%9s] %14s]    [%d]\n",
				 boardDTO.getNo(),  
				 boardDTO.getTitle(),  
				 boardDTO.getWriter(), 
				 boardDTO.getWritedate(),  
				 boardDTO.getHit() 
					);
		System.out.println("------------------------------------------------------");

	}

						
	public void view(FreeBoardDTO boardDTO) {
		
		FreeOutUtil.printTitle("\n\n                   *** 선택된 글 보기  ***");
		String menu = "번호                 제목                   작성자                             작성일                           조회수";
		FreeOutUtil.printMenu(menu, "-", 53);
		if(boardDTO == null) {
			System.out.println("데이터가없습니다");
		}else 
		System.out.printf("[%1d]%10s%10s%22s   [%d]\n",
				 boardDTO.getNo(), 
				 boardDTO.getTitle(),
				 boardDTO.getWriter(), 
				 boardDTO.getWritedate(),
				 boardDTO.getHit()
					);
		FreeOutUtil.repeatChar("-", 53);
		System.out.println("");
	}
}



