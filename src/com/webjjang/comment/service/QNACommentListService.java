package com.webjjang.comment.service;

import java.util.List;

import com.webjjang.board.controller.ProcessInterface;
import com.webjjang.comment.dao.QNACommentDAO;
import com.webjjang.comment.dto.QNACommentDTO;

public class QNACommentListService implements ProcessInterface {

	public List<QNACommentDTO> process(Object no) throws Exception {
		List<QNACommentDTO> list = null;
		QNACommentDAO qnaCommentDAO = new QNACommentDAO();
		list = qnaCommentDAO.list(no);
		return list;
	}
	
//	@Override
//	public List<QNACommentDTO> process(Object obj) throws Exception {
		// TODO Auto-generated method stub
//		return null;
//	}
}
