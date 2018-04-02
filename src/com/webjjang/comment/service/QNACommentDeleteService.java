package com.webjjang.comment.service;

import com.webjjang.comment.dao.QNACommentDAO;

public class QNACommentDeleteService {

	public void delete(int no, int qno) throws Exception {
//		System.out.println("QNACommentDeleteService.delete()");
		QNACommentDAO qnaCommentDAO = new QNACommentDAO();
		qnaCommentDAO.delete(no, qno);
	}
}
