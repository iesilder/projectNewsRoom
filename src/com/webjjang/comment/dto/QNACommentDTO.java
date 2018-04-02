package com.webjjang.comment.dto;

public class QNACommentDTO {

	// 댓글과 관련된 정보들을 변수로 입력한다.
	private int no, qno;
	private String content, writer, writedate;
	
	// 댓글 쓰기용 생성자를 입력
	public QNACommentDTO(int qno, String content, String writer) throws Exception {
		this.qno=qno;
		this.content = content;
		this.writer = writer;
		
	}
	
	// 댓글 보기용 생성자를 입력한다.
	public QNACommentDTO(int no, String content, String writer, String writedate) throws Exception {
		this.no = no;
		this.content = content;
		this.writer = writer;
		this.writedate = writedate;
	}

	
	// 데이터를 꺼내고 넣는 getter와 setter를 작성한다.
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getQno() {
		return qno;
	}
	public void setQno(int qno) {
		this.qno = qno;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	
	@Override
	public String toString() {
		return "QNACommentDTO [no=" + no + ", qno=" + qno
				+ ", content=" + content + ", writer=" + writer
				+ ", writedate=" + writedate + "]";
	}
}
