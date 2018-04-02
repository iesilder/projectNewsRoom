package com.webjjang.board.dto;


public class NewsDTO {

	int no;
	String title,url,content;
	String pubDate;
	int hit;
	
	//기본생성자
	public NewsDTO() {
	}
	
	//리스트용 생성자
	public NewsDTO(int no, String title, String pubDate, int hit) {
		this(no,title,null,null,pubDate,hit);
	}
	
	//글 보기용 생성자
	public NewsDTO(int no, String title, String url, String content, String pubDate,int hit) {
		this.no = no;
		this.title = title;
		this.url = url;
		this.content = content;
		this.pubDate = pubDate;
		this.hit = hit;
	}
	//글쓰기용 생성자
	public NewsDTO(String title, String url, String content) {
		this.title = title;
		this.url = url;
		this.content = content;
	}

	//getter와 setter
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}
	
	@Override
	public String toString() {
		return "NewsDTO [no=" + no + ", title=" + title + ", url=" + url + ", content=" + content + ", pubDate="
				+ pubDate + ", hit=" + hit + "]";
	}

	
	
	
}
