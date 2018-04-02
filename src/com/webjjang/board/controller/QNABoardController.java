package com.webjjang.board.controller;

import java.util.List;
import java.util.regex.Pattern;


import com.webjjang.board.dto.QNABoardDTO;
import com.webjjang.board.service.QNABoardDeleteService;
import com.webjjang.board.service.QNABoardListService;
import com.webjjang.board.service.QNABoardUpdateService;
import com.webjjang.board.service.QNABoardViewService;
import com.webjjang.board.service.QNABoardWriteService;
import com.webjjang.comment.dto.QNACommentDTO;
import com.webjjang.comment.service.QNACommentDeleteService;
import com.webjjang.comment.service.QNACommentListService;
import com.webjjang.comment.service.QNACommentWriteService;
import com.webjjang.main.SelectMenuInterface;
import com.webjjang.util.InUtil;
import com.webjjang.util.OutUtil;
import com.webjjang.view.board.QNABoardPrint;
import com.webjjang.view.comment.QNACommentPrint;


public class QNABoardController implements SelectMenuInterface {

	public void selectMenu() {
//		System.out.println("QNABoardController.selectMenu()");
		
		// 질의응답 게시판 탭 작성
			OutUtil.printTitle("Q & A");
			
		// 메뉴 출력, 입력, 처리 과정을 무한 반복하는 메서드를 작성 -> 반복문 이용
		try {	
			while (true) {
				// 메뉴를 출력하고, 메뉴를 구성한다.
				// 일반적인 게시판과 유사한 구조의 게시판 구조를 형성한다.
				OutUtil.printMenu("1.질문 목록 \n2.질문 보기 \n3.질문 작성 \n4.질문 수정 \n5.질문 삭제 \n6.댓글 작성 \n7.댓글 삭제 \n0.이전 메뉴", "*", 30);
				String menu = InUtil.getMenu("메뉴를 선택하세요.");
				String title = null;
				String content = null;
				String writer = null;
				int qno = 0;
				
				//정규식을 통해 입력받은 문자가 숫자인지 아닌지를 확인한다.
				String character = "^[0-9]*$";
				
				if(!Pattern.matches(character, menu)) {
					System.out.println("숫자가 아닙니다.");
					continue;
				}	
				
				// 각 메뉴마다 다른 작업들을 처리해야 하므로, 조건문을 활용하여 이를 이행한다.
				switch (menu) {
				case "1":
					System.out.println("질문 목록");
					// list service를 호출하여 데이터를 가져온다. (객체 생성 및 호출)
					QNABoardListService qnaBoardListService
					= new QNABoardListService();
					List<QNABoardDTO> list
					= qnaBoardListService.process();
					// 가져온 데이터를 출력한다.
					QNABoardPrint qnaBoardPrint
					= new QNABoardPrint();
					qnaBoardPrint.list(list);
					break;
				case "2":
					System.out.println("질문 보기");
					// 글 번호에 맞는 게시판의 데이터를 가져온다. BoardDTO 참고
					// 데이터를 가져올 객체를 생성하고, 호출한다. 
					// 작업 처리 순서 : (controller -> service -> DAO)
					int no = InUtil.getIntWithCheck("질문 번호 입력");
					QNABoardViewService qnaBoardViewService
					= new QNABoardViewService();
					QNABoardDTO qnaBoardDTO
					= qnaBoardViewService.process(no);
					// 가져온 데이터를 출력
					QNABoardPrint qnaBoardPrint2
					= new QNABoardPrint();
					qnaBoardPrint2.view(qnaBoardDTO);																							
					// 댓글 데이터를 가져온다.
					try {QNACommentListService qnaCommentListService
					= new QNACommentListService();
					QNACommentPrint qnaCommentPrint = new QNACommentPrint();
					List<QNACommentDTO> list1 = qnaCommentListService.process(no);
					qnaCommentPrint.list(list1);
					} catch (Exception e) {
						System.out.println("댓글이 존재하지 않습니다.");
					}
					break;
				case "3":
					System.out.println("질문 작성");
					// 오라클에 넣은 데이터를 생성해서 데이터를 채움
					QNABoardDTO qnaBoardDTO1 = new QNABoardDTO
							(InUtil.getMenu("제목"),
							InUtil.getMenu("내용"),
							InUtil.getMenu("작성자"));
					// 객체를 생성하고 호출한다.
					QNABoardWriteService qnaBoardWriteService
					= new QNABoardWriteService();
					qnaBoardWriteService.process(qnaBoardDTO1);
					break;
				case "4":
					System.out.println("질문 수정");
					no = InUtil.getIntWithCheck("수정할 질문의 번호를 입력하세요");
					title = InUtil.getMenu("제목");
					content = InUtil.getMenu("본문");
					writer = InUtil.getMenu("작성자");
					QNABoardUpdateService qnaBoardUpdateService
					= new QNABoardUpdateService();
					qnaBoardUpdateService.update(title, content, writer, no);
					break;
				case "5":
					System.out.println("질문 삭제");	
					no = InUtil.getIntWithCheck("삭제할 질문의 번호를 입력하세요.");
					try 
					{QNABoardDeleteService qnaBoardDeleteService
					= new QNABoardDeleteService();
					qnaBoardDeleteService.delete(no);
					} catch (Exception e) {
						System.out.println("주의 : 댓글이 달려있는 글은 삭제할 수 없습니다.");
					}
					break;
				case "6":
					System.out.println("댓글 작성");
					// 오라클에 넣은 데이터를 생성해서 데이터를 채움
					qno = InUtil.getInt("원문 번호");
					content = InUtil.getMenu("내용");
					writer = InUtil.getMenu("작성자");
					// 객체를 생성하고 호출한다.
					QNACommentWriteService qnaCommentWriteService
					= new QNACommentWriteService();
					qnaCommentWriteService.process(qno, content, writer);
					break;
				case "7":
					System.out.println("댓글 삭제");
					qno = InUtil.getIntWithCheck("삭제할 댓글이 위치한 글의 번호를 입력해주세요.");
					no = InUtil.getIntWithCheck("삭제할 댓글의 번호를 선택해주세요.");
					QNACommentDeleteService qnaCommentDeleteService
					= new QNACommentDeleteService();
					qnaCommentDeleteService.delete(no, qno);
					break;

				case "0":
					System.out.println("메인 메뉴로 돌아갑니다.");
					return;				
				default:
					System.out.println("잘못된 메뉴를 선택하셨습니다.");
					System.out.println("다시 입력하여 주십시오.");
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("작업 처리중 오류가 발생하였습니다.");
		}
	}
}
