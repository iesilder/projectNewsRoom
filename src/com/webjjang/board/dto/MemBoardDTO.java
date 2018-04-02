package com.webjjang.board.dto;

public class MemBoardDTO {
	private int no;
	private String title, content, id, writedate;
	
	//보기용
	public MemBoardDTO(int no, String title, String content, String id, String writedate) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.id = id;
		this.writedate = writedate;
	}
	
	
	//리스트용
	public MemBoardDTO(int no, String title, String id, String writedate) {
		super();
		this.no = no;
		this.title = title;
		this.id = id;
		this.writedate = writedate;
	}



	//기본 생성자
	public MemBoardDTO() {
		super();
	}
	
	//쓰기 및 수정
	public MemBoardDTO(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
		return "MemBoardDTO [no=" + no + ", title=" + title + ", content=" + content + ", id=" + id + ", writedate="
				+ writedate + "]";
	}
	

}
