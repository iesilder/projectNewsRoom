package com.webjjang.view.comment;

import java.util.List;

import com.webjjang.comment.dto.QNACommentDTO;

public class QNACommentPrint {

	public void view(QNACommentDTO qnaCommentDTO) throws Exception {
		// System.out.println("QNACommentDTO.view()");
		System.out.println("=============== 댓글 ===============");

		if (qnaCommentDTO == null)
			System.out.println("댓글이 없습니다.");
		else
			System.out.println(qnaCommentDTO);
	}

	public void list(List<QNACommentDTO> list) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("========================================");
		for (QNACommentDTO qnaCommentDTO : list)
			System.out.println("" + "번호: " + qnaCommentDTO.getNo() + "\t\t" + "" + "작성자: " + qnaCommentDTO.getWriter()
					+ "\n" + "댓글:" + qnaCommentDTO.getContent() + "\n" + "작성일: " + qnaCommentDTO.getWritedate());
		System.out.println("========================================");

	}
}
