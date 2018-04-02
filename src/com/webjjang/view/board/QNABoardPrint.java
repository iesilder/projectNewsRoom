package com.webjjang.view.board;

import java.util.List;

import com.webjjang.board.dto.QNABoardDTO;
import com.webjjang.util.OutUtil;

public class QNABoardPrint {

	public void list(List<QNABoardDTO> list) throws Exception {
		// TODO Auto-generated method stub
//		System.out.println("QNABoardPrint.list()");
		OutUtil.printTitle("Q & A 리스트");
		OutUtil.printMenu("번호\t\t 제목\t\t 작성자\t\t 작성일\t\t 조회수", "*", 70);
		for(QNABoardDTO qnaBoardDTO : list)
			System.out.println(""  +  qnaBoardDTO.getNo()  +  "\t" + qnaBoardDTO.getTitle()
			+  "\t\t"  + qnaBoardDTO.getWriter() +  "\t"  + qnaBoardDTO.getWritedate()
			+  "\t"  + qnaBoardDTO.getHit() +  "\t" );
	}

	public void view(QNABoardDTO qnaBoardDTO) throws Exception {
//		System.out.println("QNABoardDTO.view()");
		OutUtil.printMenu("번호\t\t 제목\t\t 내용\t\t 작성자\t\t 작성일\t\t 조회수 ", "*", 80);
		if(qnaBoardDTO == null)
			System.out.println("데이터가 존재하지 않습니다.");
		else System.out.println(""  +  qnaBoardDTO.getNo()  +  "\t" + qnaBoardDTO.getTitle()
		+  "\t\t" + qnaBoardDTO.getContent() + "\t"  + qnaBoardDTO.getWriter() +  "\t"  + qnaBoardDTO.getWritedate()
		+  "\t"  + qnaBoardDTO.getHit() +  "\t");
	}
	

}
