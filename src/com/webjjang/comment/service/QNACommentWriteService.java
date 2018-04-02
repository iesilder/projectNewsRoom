package com.webjjang.comment.service;

import com.webjjang.board.controller.ProcessInterface;
import com.webjjang.comment.dao.QNACommentDAO;

public class QNACommentWriteService implements ProcessInterface {
	
	QNACommentDAO qnaCommentDAO = new QNACommentDAO();
	
	@Override
	public Object process(Object obj) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}
		
	public void process(int qno, String content, String writer) throws Exception {
//		System.out.println("QNACommentWriteService.process()");
		
		// list에 데이터를 가져와서 채우는 프로그램을 작성, 채운 것을 리턴하여 넘긴다.
		// 객체를 생성하고, 호출한다.
		
		qnaCommentDAO.write(qno, content, writer);
	}
}
