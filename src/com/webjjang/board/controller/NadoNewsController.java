package com.webjjang.board.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.webjjang.board.dto.NadoNewsBoardDTO;
import com.webjjang.board.service.NadoNewsCrawlingService;
import com.webjjang.board.service.NadoNewsDeleteService;
import com.webjjang.board.service.NadoNewsListService;
import com.webjjang.board.service.NadoNewsUpdateService;
import com.webjjang.board.service.NadoNewsViewService;
import com.webjjang.board.service.NadoNewsWriteService;
import com.webjjang.main.SelectMenuInterface;
import com.webjjang.util.InUtil;
import com.webjjang.view.board.NadoNewsBoardPrint;



public class NadoNewsController implements SelectMenuInterface{
	private List<NadoNewsBoardDTO> list;
	private NadoNewsBoardDTO boardDTO;
	private NadoNewsBoardPrint boardPrint;
	private int no;
	private String title;
	private String content;
	private String writer;

	public void selectMenu(){
		
				
		while(true){			
			
			String menu = InUtil.getMenu("1.기사 목록 "
								+"2.기사 읽기 "
								+"3.기사 쓰기 "
								+"4.기사 수정 "
								+"5.기사 삭제 "
								+"6.기사 로드 "
								+"0.이전 메뉴", "메뉴를 입력하세요 >");		
			
			//정규식을 통해 입력받은 문자(menu)가 숫자인지 파악하기
			String pattern = "^[0-9]*$";
			
			if(!Pattern.matches(pattern, menu)) {
				System.out.println("숫자가 아닙니다.");
				continue;
			}
			
			switch (menu) {
			case "1":
				//리스트 서비스의 process 호출
				NadoNewsListService nadoNewsListService = new NadoNewsListService();
				if (list == null) list = new ArrayList<NadoNewsBoardDTO>();
				try {
					list = nadoNewsListService.process();
				} catch (Exception e) {
					System.out.println("리스트를 가져오는 중 오류가 발생했습니다.");
//					e.printStackTrace();
				}
				//출력하는 프로그램
				if(boardPrint == null) boardPrint = new NadoNewsBoardPrint();
				boardPrint.listPrint(list);
				break;
				
			case "2":
				//뷰 서비스의 process 호출
				no = InUtil.getIntWithCheck("기사 번호를 입력하세요 >");
				NadoNewsViewService nadoNewsViewService = new NadoNewsViewService();
				if (boardDTO == null) boardDTO = new NadoNewsBoardDTO();
				try {
					boardDTO = nadoNewsViewService.process(no);
				} catch (Exception e) {
					System.out.println("뉴스를 가져오는 중 오류가 발생했습니다.");
//					e.printStackTrace();
				}
				//출력하는 프로그램
				if(boardPrint == null) boardPrint = new NadoNewsBoardPrint();
				boardPrint.viewPrint(boardDTO);
				break;
				
			case "3":
				//기사를 작성하기 위해서 제목, 내용, 작성자를 입력받는다.
				title = InUtil.getMenu("제목을 입력하세요>");
				content = InUtil.getMenu("본문을 입력하세요>");
				writer = InUtil.getMenu("작성자를 입력하세요>");
				NadoNewsWriteService nadoNewsWriteService = new NadoNewsWriteService();
				try {
					//write서비스의 write함수 호출
					nadoNewsWriteService.write(title, content, writer);
				} catch (Exception e) {
					System.out.println("뉴스를 작성하는 도중 오류가 발생했습니다.");
//					e.printStackTrace();
				}
				break;

			case "4":
				//수정할 기사 번호와 제목, 본문, 작성자를 차례대로 입력 받는다.
				no = InUtil.getIntWithCheck("수정할 기사 번호를 입력하세요>");
				title = InUtil.getMenu("제목을 입력하세요>");
				content = InUtil.getMenu("본문을 입력하세요>");
				writer = InUtil.getMenu("작성자를 입력하세요>");
				
				//업데이트 서비스의 update 메서드 호출
				NadoNewsUpdateService nadoNewsUpdateService = new NadoNewsUpdateService();
				try {
					nadoNewsUpdateService.update(title, content, writer, no);
				} catch (Exception e) {
					System.out.println("뉴스를 수정하는 중 오류가 발생했습니다.");
//					e.printStackTrace();
				}
				
				break;
			
			case "5":
				//삭제할 기사 번호를 입력받는다.
				no = InUtil.getIntWithCheck("삭제할 기사 번호를 입력하세요>");
				NadoNewsDeleteService nadoNewsDeleteService = new NadoNewsDeleteService();
				//delete 메서드 호출
				try {
					nadoNewsDeleteService.delete(no);
				} catch (Exception e) {
					System.out.println("뉴스를 삭제하는 중 오류가 발생했습니다.");
//					e.printStackTrace();
				}
				break;
			
			case "6":
				//크롤링할 기사를 데이터 베이스에 저장하기 위해서 크롤링 서비스의 nadoNewsCrawling 메서드를 호출한다.
				NadoNewsCrawlingService nadoNewsCrawlingService = new NadoNewsCrawlingService();
				try {
					nadoNewsCrawlingService.nadoNewsCrawling();
				} catch (Exception e) {
					System.out.println("기사를 긁어오는 도중 오류가 발생했습니다.");
//					e.printStackTrace();
				}
				
				break;
				
				// 0 번 선택시 뒤로가기
			case "0":
				return;
			
				// 디폴트값으로 잘못된 메뉴를 입력했다고 출력한다.
			default:
				System.out.println("잘못된 메뉴를 입력 하셨습니다.");
				break;
			}
			
		}
	}
}
