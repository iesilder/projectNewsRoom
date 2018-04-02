package com.webjjang.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.webjjang.util.DBUtil;

public class NadoNewsCrawlingDAO {

	// "경향 스포츠 신문 연예 파트"의 주소를 address에 저장한다.
	private final static String address = "http://sports.khan.co.kr/news/page/entertainment.html";

	// 오라클에 입력할 때 필요한 커넥션 객체와 pstmt객체 준비
	private Connection conn;
	private PreparedStatement pstmt;
	// sql에 필요한 변수
	private String sql;

	// 크롤링에서 에러가 나면 익셉션을 throws한다.
	public void nadoNewsCrawling() throws Exception {

		// 커넥트.get()을 이용해 변수 doc HTML문서를 몽땅 긁어온다.
		Document doc = Jsoup.connect(address).header("User-Agent", "Mozilla/5.0").get();

		// 기사 본문에 필요한 myNews, 제목, 기자이름에 필요한 ArrayList를 각각 만든다.
		ArrayList<String> myNews = new ArrayList<String>();
		ArrayList<String> title = new ArrayList<>();
		ArrayList<String> writer = new ArrayList<>();

		// Elements객체에 기사 본문이 첨부되어 있는 Link주소를 담는다.
		Elements link = doc.select(".txt a");

		// Elements객체에 제목과 기자 이름을 각각 담는다.
		Elements titleElements = doc.select(".listWrap");
		Elements writerElements = doc.select(".byline");

		// Elements를 순회하면서 Link에 담긴 각 Element를 a로 뽑아낸다.
		for (Element a : link) {
			// a에 담긴 문자열중 주소만 뽑아낸다.
			String address1 = a.toString().substring(a.toString().indexOf('"') + 1, 93);

			// 다시 Connect를 이용해서 본문이 담긴 HTML을 몽땅 긁어온다.
			Document doc1 = Jsoup.connect(address1).header("User-Agent", "Mozilla/5.0").get();

			// 준비된 ArrayList에 긁어온 기사 본문을 담아낸다.
			myNews.add(doc1.select(".content_text").text());
		}

		// 기사가 개행이 되어 있지 않으므로 50줄마다 개행시켜주기 위해 StringBuilder ArrayList를 준비한다.
		ArrayList<StringBuilder> sbAr = new ArrayList<>();

		// 각각의 List를 순회하면서 myNews에 있던 String을 StrinBuilder에 담아준다.
		for (int i = 0; i < myNews.size(); i++) {
			sbAr.add(new StringBuilder(myNews.get(i)));
			// StringBuilder에 있는 문자열의 인덱스가 50개 째가 될 때마다 개행문자 "\n"을 insert해준다.
			for (int j = 0; j < myNews.get(i).length(); j++) {
				if ((j >= 50) && (j % 50 == 0)) {
					sbAr.get(i).insert(j, "\n");
				}
			}
		}

		// 작업을 편하게 하기 위해 다시 StringBuilder를 String배열에 담는다.
		myNews = new ArrayList<String>();
		for (int i = 0; i < sbAr.size(); i++) {
			myNews.add(sbAr.get(i).toString());
		}

		// 제목 객체의 구성은 제목 + 요약이므로 너무 길기 때문에 30글자로 제한해 준다.
		for (int i = 0; i < titleElements.size(); i++) {
			String a = titleElements.get(i).text();
			title.add(a.substring(0, 30));
		}
		
		//제목 뒤에 ... 줄임표시를 넣어준다.
		for(int i =0; i < title.size(); i++) {
			for(int j=0; j <35; j++) {
				if(j>=title.get(i).length()) {
					String str = title.get(i);
					str += ".";
					title.set(i, str);
				}else {
					
				}
			}
		}

		// 기자 이름의 경우 날짜와 기사이름 신문사가 들어 있는 자료이므로 기자 이름만 뽑아내 준다.
		for (int i = 0; i < writerElements.size(); i++) {
			String a = writerElements.get(i).text();
			writer.add(a.substring(6, 9));
		}

		
		//오라클에 연결하기 위해 객체들을 준비하고 SQL을  준비한다.
		conn = DBUtil.getConnection();
		//데이터가 중복될 수 있으므로 Drop테이블과 Drop 시쿼스를 한번 해준 후에 다시 create를 한다.
		setSql("DROP TABLE nadoboard");
		pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();

		setSql("DROP SEQUENCE nadoboard_seq");
		pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();

		//create SQL. 데이터 양이 많을 수 있으므로 넉넉하게 잡는다.
		setSql("create table nadoboard( " + "    no number primary key, " + "    title VARCHAR2(4000), "
				+ "    content CLOB, " + "    writer VARCHAR2(4000), " + "    writedate DATE DEFAULT SYSDATE, "
				+ "    hit number DEFAULT 0)");
		pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();

		//시퀀스를 만든다.
		setSql("CREATE SEQUENCE nadoboard_seq");
		pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();

		//본문의 사이즈 만큼 순회하면서 각각의 제목, 본문, 내용을 DB에 insert한다.
		for (int i = 0; i < myNews.size(); i++) {
			conn = DBUtil.getConnection();
			setSql("INSERT INTO nadoboard(no, title, content, writer, writedate) "
					+ " VALUES(nadoboard_seq.nextval, ?, ?, ?, SYSDATE)");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title.get(i));
			pstmt.setString(2, myNews.get(i));
			pstmt.setString(3, writer.get(i));
			pstmt.executeUpdate();
		}

		//객체 닫기
		DBUtil.close(conn, pstmt);

	}

	
	// SQL을 작성하는 setSql 메서드 작성
	private void setSql(String string) {
		this.sql = string;
	}
}
