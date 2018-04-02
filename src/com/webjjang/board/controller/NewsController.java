package com.webjjang.board.controller;

import java.util.List;
import java.util.regex.Pattern;

import com.webjjang.board.dto.NewsDTO;
import com.webjjang.board.service.NewsDeleteService;
import com.webjjang.board.service.NewsListService;
import com.webjjang.board.service.NewsUpdateService;
import com.webjjang.board.service.NewsViewService;
import com.webjjang.board.service.NewsWriteService;
import com.webjjang.main.SelectMenuInterface;
import com.webjjang.util.InUtil;
import com.webjjang.util.KeyWordUtil;
import com.webjjang.view.board.NewsPrint;

public class NewsController implements SelectMenuInterface {
	public void selectMenu() {
//		System.out.println("NewsController.selectMenu()");
		
		//메뉴 출력 -> 입력과 처리 반복
		while(true) {
			String menu = InUtil.getMenu( "1.뉴스리스트 "
										+ "2.뉴스 보기 "
										+ "3.뉴스 쓰기 "
										+ "4.뉴스 수정 "
										+ "5.뉴스 삭제 "
										+ "0.이전메뉴","메뉴입력");
			String pattern = "^[0-9]*$";
			
			if(!Pattern.matches(pattern, menu)) {
				System.out.println("숫자가 아닙니다.");
				continue;
			}
			
			
				
		switch(menu) {
		case "1":
			System.out.println("뉴스 리스트입니다.");
			NewsListService newsListService
			= new NewsListService();
			try {
			List<NewsDTO> list 
			= newsListService.process();
			
			NewsPrint newsPrint = new NewsPrint();
			newsPrint.list(list);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		case "2":
			int no = InUtil.getIntWithCheck("글번호를 입력해주세요>>");
			KeyWordUtil.setNum(no);
			NewsViewService newsViewService
			= new NewsViewService();
			try {
			NewsDTO newsDTO = newsViewService.process(no);
			NewsPrint newsPrint2 = new NewsPrint();
			newsPrint2.view(newsDTO);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		case "3":
			System.out.println("뉴스 쓰기입니다.");
			NewsDTO newsDTO2 
			= new NewsDTO(InUtil.getMenu("제목"), 
							InUtil.getMenu("URL"), 
							InUtil.getMenu("내용"));
			NewsWriteService newsWriteService
			= new NewsWriteService();
			try {
			newsWriteService.process(newsDTO2);	
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		case "4":
			System.out.println("뉴스 수정입니다.");
			// 수정할 기사 번호와 제목, 본문, url를 차례대로 입력받는다.
			no= InUtil.getIntWithCheck("수정할 기사 번호를 입력하세요>>");
			String title = InUtil.getMenu("제목을 입력하세요>");
			String url = InUtil.getMenu("URL을 입력하세요>");
			String content = InUtil.getMenu("내용을 입력하세요>");
			try {
			NewsUpdateService newsUpdateService = new NewsUpdateService();
			newsUpdateService.process(no, title, url, content);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		case "5":
			System.out.println("뉴스 삭제입니다.");
			// 삭제할 기사 번호를 입력받는다.
			no = InUtil.getIntWithCheck("삭제할 기사 번호를 입력하세요>");
			NewsDeleteService newsDeleteService = new NewsDeleteService();
			try {
			newsDeleteService.process(no);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		case "0":
			System.out.println("이전메뉴 입니다.");
			return;
		default:
			System.out.println("잘못된 메뉴를 선택하셨습니다.");
			break;	
		}
		
		}
			
	}
}
