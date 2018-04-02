package com.webjjang.crawler;
import java.io.BufferedReader;		 			
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.webjjang.util.DBUtil;

/*
 * 	 keyWord를 입력받아 Naver News에 검색 후  
 * 	 1페이지 html을 Jsoup로  파싱한다.
 * 	   그리고 키워드, 제목, 내용, url, 게시일을 저장.
 */
public class NaverNewsCrawler {
		
	
	
		public static void crawler(String keyWord) {
//			System.out.println("NaverNewsCrawler.crawler()"); 
			Connection con1 = null;
			PreparedStatement pstmt = null;
			
        String clientId = "sOnzbjNNnPqK2HFfZbqx";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "lIHWxpuLxj";//애플리케이션 클라이언트 시크릿값";

        try {
            // 크롤링 작업
        	String text = URLEncoder.encode(keyWord, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/news.xml?&sort=sim&query="+ text; 
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            
            org.jsoup.nodes.Document  doc = Jsoup.parse(response.toString());
           
            Elements ar1 = doc.select("title"); 
            Elements ar2 = doc.select("description"); 
            Elements ar3 = doc.select("originallink"); 
            Elements ar4 = doc.select("pubdate"); 
            
            // 데이터 담을 객체 선언.
            ArrayList<String> titles = new ArrayList<>();    
            ArrayList<String> contents = new ArrayList<>();    
            ArrayList<String> url_addresses = new ArrayList<>();    
            ArrayList<String> pubDates = new ArrayList<>();    
            
            //title
            for(int i=0; i<ar1.size(); i++){
            	Element a = ar1.get(i);
            	String title =a.text().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "")
            			.replaceAll("&quot", "")
            			.replaceAll("&amp", "")
            			.replaceAll("&lt;", "");
            	String title1 = title.substring(0,10)+"...";
            	titles.add(i, title1);
            }
           
            //content
            for(int j=0; j<ar2.size(); j++){
                Element b = ar2.get(j);
                String content =b.text().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "")
            			.replaceAll("&quot", "")
            			.replaceAll("&amp", "");
//                String content1=content.substring(0, 23)+"...";
                contents.add(j, content);
            }
            
            //url
            for(int l=0; l<ar3.size(); l++){
            	Element c = ar3.get(l);
            	String urls =c.text().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "")
            			.replaceAll("&quot", "")
            			.replaceAll("&amp", "");
            	url_addresses.add(l,urls);
            }
           
            //pubDate
            for(int k=0; k<ar4.size(); k++){
            	Element d = ar4.get(k);
            	String pubDate =d.text().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "")
            			.replaceAll("&quot", "")
            			.replaceAll("&amp", "");
            	String pubDate1 = pubDate.substring(0, 16);
            	pubDates.add(k, pubDate1);
            }
            // 입력받은 키워드를 DB에 입력.
            
            // 1. 드라이버 확인  2. 연결
            con1 = DBUtil.getConnection();
            
            // 3. sql문 작성
            String sql = " insert into news_board(no, keyword, title, url, content, pubDate) "
            		+ " values(news_seq.nextval, ?, ?, ?, ?, ?)";
            
            // 4. 처리문 객체 
            pstmt=con1.prepareStatement(sql);
            for(int p=0;p<=9;p++) {
            	pstmt.setString(1, keyWord);
            	pstmt.setString(2, titles.get(p));
            	pstmt.setString(3, url_addresses.get(p));
            	pstmt.setString(4, contents.get(p));
            	pstmt.setString(5, pubDates.get(p));
            	
            	// 5.실행
            	pstmt.executeQuery();
            	
            	// 6. 오류 없으면 처리됨.
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }finally {
			try {
				//7. 닫기
				DBUtil.close(con1, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
        
    }
		
}
