package com.webjjang.board.dto;

// 글번호, 제목, 내용, 글쓴이, 작성일, 조회수 등의 정보를 제공하는 클래스

public class QNABoardDTO {
	
	
	// 글쓰기에 필요한 정보들을 변수로..
	private int no;
	private String title, content, writer, writedate;
	private int hit;
	
	// 글 쓰기용 생성자를 작성하자
	public QNABoardDTO(String title, String content, String writer) throws Exception {
		super();
		this.title = title;
		this.content = content;
		this.writer = writer;
	
	}

	// 글 리스트용 생성자를 작성하자
	public QNABoardDTO(int no, String title, String writer, String writedate, int hit) throws Exception {
		super();
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.writedate = writedate;
		this.hit = hit;
	}
	
	// 글 보기용 생성자를 작성하자
	public QNABoardDTO(int no, String title, String content, String writer, String writedate, int hit) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.writedate = writedate;
		this.hit = hit;
	}
	// 글 수정용 생성자를 작성하자.
	public QNABoardDTO(int no, String title, String content, String writer) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.writer = writer;
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

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}
	
	@Override
	public String toString() {
		return "BoardDTO [no=" + no + ", title=" + title
				+ ", content=" + content + ", writer=" + writer
				+ ", writedate=" + writedate
				+ ", hit=" + hit + "]";
	}
}
