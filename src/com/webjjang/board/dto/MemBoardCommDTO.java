package com.webjjang.board.dto;

public class MemBoardCommDTO {

	private int commno, no;
	private String content, id, writedate;

	
	// 전체 출력
	public MemBoardCommDTO(int commno, String content, String id, String writedate) {
		super();
		this.commno = commno;
		this.content = content;
		this.id = id;
		this.writedate = writedate;
	}

	// 기본 생성자
	public MemBoardCommDTO() {
		super();
	}

	// 쓰기 수정용 생성자
	public MemBoardCommDTO(String content) {
		super();
		this.content = content;
	}

	public int getCommno() {
		return commno;
	}

	public void setCommno(int commno) {
		this.commno = commno;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}

	@Override
	public String toString() {
		return "MemBoardCommDTO [commno=" + commno + ", no=" + no + ", content=" + content + ", id=" + id
				+ ", writedate=" + writedate + "]";
	}

}
