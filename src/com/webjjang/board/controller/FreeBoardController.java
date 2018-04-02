package com.webjjang.board.controller;

import java.util.List;
import java.util.regex.Pattern;

import com.webjjang.board.service.FreeBoardDeleteService;
import com.webjjang.board.service.FreeBoardListService;
import com.webjjang.board.service.FreeBoardUpdateService;
import com.webjjang.board.service.FreeBoardViewService;
import com.webjjang.board.service.FreeBoardWriteService;
import com.webjjang.board.dto.FreeBoardDTO;
import com.webjjang.util.FreeUtil;
import com.webjjang.view.board.FreeBoardPrint;



/*
 * 메뉴를 보여주고 선택하면 처리한다 반복처리 한다
 */
public class FreeBoardController implements ProcessInterface {
	private String title;
	private String content;
	private String writer;
	private String pattern = "^[0-9]*$";
	
	public void selectMenu()  {
		while(true) {
			try {
				//메뉴출력
			
			//메뉴선택
			String menu = FreeUtil.getMenu
					("1.글 리스트     "
							+ " 2.글 보기    "
							+ " 3.글 쓰기     "
							+ " 4.글 수정     "
							+ " 5.글 삭제    "
							+ " 0.이전메뉴");			
			//메뉴처리
			switch (menu) {
			 case "1":
				
				// 리스트를 처리하는 객체를 생성, 호출
				FreeBoardListService boardListService = new FreeBoardListService();
				//메서드를 호출해서 실행
				List<FreeBoardDTO> list 
				= boardListService.process();
				//가져온 데이터를 출력하기
				FreeBoardPrint boardPrint 
				= new FreeBoardPrint();
				boardPrint.list(list);
				break;
			 case "2":
				 
					// 리스트를 처리하는 객체를 생성, 호출 
					int no = FreeUtil.getIntWithcheck("글번호 입력하세요.");
					//controller -=> service -> dao
					FreeBoardViewService boardViewService 
					= new FreeBoardViewService();
					//메서드를 호출해서 실행 
					FreeBoardDTO boardDTO
					= boardViewService.process(no);
					//가져온 데이터를 출력하기
					FreeBoardPrint boardprint2 
					= new FreeBoardPrint();
					boardprint2.view(boardDTO);
				
				
				break;
			 case "3":
					System.out.println("글 쓰기");
					// 오라클에 넣을 데이터를 생성해서 데이터를 채운다
					FreeBoardDTO boardDTO1 = 
							new FreeBoardDTO
							(FreeUtil.getMenu("제목"),
							FreeUtil.getMenu("내용"),
							FreeUtil.getMenu("작성자"));
							// 객체를 생성하고 호출
					FreeBoardWriteService boardWriteService
					= new FreeBoardWriteService();
					boardWriteService.process(boardDTO1);
					
					break;
			 case "4":
					System.out.println("글 수정");
					menu = FreeUtil.getMenu("수정할 글 번호를 입력하세요.");
					if (!Pattern.matches(pattern, menu)) {
						System.out.println("글 번호를 입력하세요");
						continue;
					}
					title = FreeUtil.getMenu("제목을 입력하세요.");
					content = FreeUtil.getMenu("본문을 입력하세요.");
					writer = FreeUtil.getMenu("작성자를 입력하세요.");
					
					FreeBoardUpdateService freeBoardUpdateService = new FreeBoardUpdateService();
					freeBoardUpdateService.update(title, content, writer, menu);
					
					break;
					
			 case "5":
				 System.out.println("글 삭제");
				 menu = FreeUtil.getMenu("삭제할 글 번호를 입력하세요.");
				 if(!Pattern.matches(pattern, menu)) {
					 System.out.println("글 번호를 입력하세요");
					 continue;
				 }
				 FreeBoardDeleteService freeBoardDeleteService = new FreeBoardDeleteService();
				 freeBoardDeleteService.delete(menu);
				 break;
				
				
					
			case "0":
				System.out.println("메인 메뉴로 이동");
				return;

			default:
				System.out.println("잘못된 메뉴를 선택 하셨습니다");
				System.out.println("다시 메뉴를 선택해 주세요");
				break;
			}
			
			} catch (Exception e){
				System.out.println();
				e.printStackTrace();
			}
		}
	}

	@Override
	public Object process(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
