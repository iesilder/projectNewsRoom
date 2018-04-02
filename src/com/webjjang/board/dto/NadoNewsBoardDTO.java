package com.webjjang.board.dto;

public class NadoNewsBoardDTO {
	private int no;
	private String title;
	private String content;
	private String writer;
	private String writeDate;
	private int hit;

	public NadoNewsBoardDTO(){
		super();
	}
	
	public NadoNewsBoardDTO(int no, String title, String content, String writer, String writeDate, int hit) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.writeDate = writeDate;
		this.hit = hit;
	}

	public NadoNewsBoardDTO(int no, String title, String writer, String writeDate, int hit) {
		super();
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.writeDate = writeDate;
		this.hit = hit;
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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

}
