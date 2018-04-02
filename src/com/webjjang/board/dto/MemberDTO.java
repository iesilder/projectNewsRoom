package com.webjjang.board.dto;

public class MemberDTO {
	private String id, pw, name, nick, tel, email;
	private int grade;

	
	//관리자용
	public MemberDTO(String id, String pw, String name, String nick, String tel, String email, int grade) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.nick = nick;
		this.tel = tel;
		this.email = email;
		this.grade = grade;
	}

	// 리스트, 뷰 출력
	public MemberDTO(String id, String name, String nick, String tel, String email, int grade) {
		super();
		this.id = id;
		this.name = name;
		this.nick = nick;
		this.tel = tel;
		this.email = email;
		this.grade = grade;
	}

	// 리스트 출력
	public MemberDTO(String id, String name, int grade) {
		super();
		this.id = id;
		this.name = name;
		this.grade = grade;
	}

	// 회원가입
	public MemberDTO(String id, String pw, String name, String nick, String tel, String email) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.nick = nick;
		this.tel = tel;
		this.email = email;
	}
	
	

	public MemberDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "memberDTO [id=" + id + ", pw=" + pw + ", name=" + name + ", nick=" + nick + ", tel=" + tel + ", email="
				+ email + ", grade=" + grade + "]";
	}

}
