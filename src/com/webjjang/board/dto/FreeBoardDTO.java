/*
 *  게시판 데이터를 저장하는 객체
 *  2018-03-06
 *  이산하
 *  번호,제목,작성내용,작성자,작성일,조회수
 */
package com.webjjang.board.dto;

public class FreeBoardDTO {
	private int no;
	private String title;
	private String content;
	private String writer;
	private String writedate;
	private int hit;

//초기화블럭-생성될때 자동으로 한번만 실행된다. 기본 값을 세팅할 때 사용
	{
		//System.out.println("기본초기화 블럭");
		//no=100;
	}
	static {
		//System.out.println("static 초기화 블럭");
//		no= 50;
	}
	
	
	//생성자 선언
	//생성을 먼저하고 값은 나중에 넣겠다.
	//기본 생성자를 직접 만들었다 new BoardDTO()
	public FreeBoardDTO() {
		//System.out.println("기본생성자 실행");
		//no=50;
	} 
	//데이터를 넣을 때 데이터가 준비가 다 되어있는 경우
	public FreeBoardDTO(int no, String title, String content, String writer, String writedate, int hit) {
//		super(); //부모 클래스를 생성한다
		this.no = no;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.writedate = writedate;
		this.hit = hit;
	}
//게시판글쓰기
	public FreeBoardDTO(String title, String content, String writer) { 
		// 위에 모든 데이터를 받아서 처리하는 부분에서 중복이 된다
		this(0, title, content, writer, null, 0);
//		this.title = title;
//		this.content = content;
//		this.writer = writer;
	}

	//데이터를 꺼내고 넣는 getter 집어넣는 setter 작성
	public int getNo() {return no;}
	public void  setNo(int no) {this.no=no;}
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
		return "BoardDTO [no=" + no + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", writedate=" + writedate + ", hit=" + hit + "]";
	}
}
