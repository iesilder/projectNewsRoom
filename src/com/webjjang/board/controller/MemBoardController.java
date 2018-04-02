package com.webjjang.board.controller;

import java.util.List;

import com.webjjang.board.dto.MemBoardCommDTO;
import com.webjjang.board.dto.MemBoardDTO;
import com.webjjang.board.dto.MemberDTO;
import com.webjjang.board.service.MemBoardCheckService;
import com.webjjang.board.service.MemBoardCommDelService;
import com.webjjang.board.service.MemBoardCommListService;
import com.webjjang.board.service.MemBoardDelService;
import com.webjjang.board.service.MemBoardListService;
import com.webjjang.board.service.MemBoardUpdateService;
import com.webjjang.board.service.MemBoardViewService;
import com.webjjang.board.service.MemBoardWriteService;
import com.webjjang.main.SelectMenuInterface;
import com.webjjang.util.InUtil;
import com.webjjang.util.MemManageUtil;
import com.webjjang.util.OutUtil;
import com.webjjang.view.board.MemBoardCommPrint;
import com.webjjang.view.board.MemBoardPrint;

public class MemBoardController implements SelectMenuInterface {

	@SuppressWarnings("finally")
	public void selectMenu(MemberDTO userDTO) {
		// TODO Auto-generated method stub
		OutUtil.printMenu("회원 전용 게시판에 접속하셨습니다.", "*", 14);
		while (true) {

			String menu = InUtil.getMenu("1.리스트 보기\n2.글보기\n3.글쓰기\n4.글수정\n5.글삭제\n0.이전 메뉴로", "메뉴를 입력하세요.");
			switch (menu) {
			case "1":
				try {
					// 객체 생성
					MemBoardListService memBoardListService = new MemBoardListService();
					MemBoardPrint memBoardPrint = new MemBoardPrint();
					// 처리
					List<MemBoardDTO> list = memBoardListService.process();
					memBoardPrint.list(list);
					break;
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("글이 없습니다.");
					break;
				}

			case "2":
				int no = InUtil.getIntWithCheck("원하시는 글의 번호를 입력하여 주세요.");
				try {
					// 객체 생성
					MemBoardViewService memBoardViewService = new MemBoardViewService();
					MemManageUtil print = new MemManageUtil();
					MemBoardDTO memBoardDTO = new MemBoardDTO();

					// 처리
					memBoardDTO = memBoardViewService.process(no);
					print.printBoardInfo(memBoardDTO);
					// 댓글을 리스트로 출력
					MemBoardCommListService memBoardCommListService = new MemBoardCommListService();
					MemBoardCommPrint memBoardCommPrint = new MemBoardCommPrint();
					List<MemBoardCommDTO> list1 = memBoardCommListService.process(no);
					memBoardCommPrint.list(list1);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("작성된 글 혹은 댓글이 없습니다.");
				} finally {
					// 댓글을 작성할지 말지 삭제 할 지 말지 결정.
					MemBoardCommController memBoardCommController = new MemBoardCommController();
					memBoardCommController.selectMenu(userDTO, no);
					break;
				}

			case "3":
				try {
					// 객체 생성
					MemBoardWriteService memBoardWriteService = new MemBoardWriteService();
					MemManageUtil memManageUtil = new MemManageUtil();
					String id = userDTO.getId();
					String title = memManageUtil.patternCheck("^.{1,100}$", "글 제목을 입력하세요.", "제목은 1자에서 100자 사이입니다.");
					String content = memManageUtil.patternCheck("^.{0,1000}$", "내용을 입력하세요.", "최대 1000자까지만 가능합니다.");
					// 처리
					memBoardWriteService.process(id, title, content);

					System.out.println("글이 정상적으로 등록되었습니다.");
					break;
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("작성 도중 에러가 발생하였습니다.");
				}

			case "4":
				try {
					// 1차 객체 생성
					MemBoardCheckService memBoardCheckService = new MemBoardCheckService();
					MemBoardDTO memBoardDTO2 = new MemBoardDTO();
					String checkId = userDTO.getId();
					int checkNo = InUtil.getIntWithCheck("수정하실 글 번호를 입력하세요.");
					// 1차 처리
					memBoardDTO2 = memBoardCheckService.process(checkId, checkNo);
					if (checkId.equals(memBoardDTO2.getId())) {
						// 2차 객체 생성
						MemManageUtil memManageUtil2 = new MemManageUtil();
						MemBoardViewService memBoardViewService2 = new MemBoardViewService();
						MemBoardUpdateService memBoardUpdateService = new MemBoardUpdateService();
						// 처리
						memManageUtil2.printBoardInfo(memBoardViewService2.process(checkNo));
						OutUtil.printMenu("수정할 내용을 입력하세요.\n바꾸실 내용이 없다면 엔터로 넘어가시면 됩니다.", "-", 14);
						memBoardUpdateService.process(memManageUtil2.updateBoard("수정하실 제목을 입력하세요.", "title"),
								memManageUtil2.updateBoard("수정하실 내용을 입력하세요.", "content"), checkNo);
						System.out.println("정상적으로 수정되었습니다.");
					} else {
						System.out.println("자신이 쓴 글만 수정 가능합니다.\n글 번호를 다시 확인하십시오.");
						break;
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("수정 도중에 에러가 발생하였습니다.");
				}

			case "5":
				try {
					// 1차 객체 생성
					MemBoardCheckService memBoardCheckService1 = new MemBoardCheckService();
					MemBoardDTO memBoardDTO3 = new MemBoardDTO();
					String delId = userDTO.getId();
					int delNo = InUtil.getIntWithCheck("삭제하실 글 번호를 입력하세요.");
					// 1차 처리
					memBoardDTO3 = memBoardCheckService1.process(delId, delNo);
					if (delId.equals(memBoardDTO3.getId())) {
						String delMenu = InUtil.getMenu("정말로 삭제하시겠습니까?(y/n)");
						switch (delMenu) {
						case "y":
							MemBoardCommDelService memBoardCommDelService = new MemBoardCommDelService();
							memBoardCommDelService.process(delNo);
							MemBoardDelService memBoardDelService = new MemBoardDelService();
							memBoardDelService.prcess(delNo);
							System.out.println("정상적으로 삭제되었습니다.");
							break;
						case "n":
							System.out.println("다시 메뉴로 돌아갑니다.");
							break;

						default:
							System.out.println("잘못 입력하셨습니다. \n다시 입력하세요.");
							break;
						}

					} else {
						System.out.println("자신이 쓴 글만 수정 가능합니다.\n글 번호를 다시 확인하십시오.");
						break;
					}
					break;
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("삭제 도중 에러가 발생하였습니다.");
				}

			case "0":
				System.out.println("회원 게시판을 나갑니다.");
				return;

			default:
				System.out.println("메뉴를 다시 입력하세요.");
				break;
			}

		} // end of while;

	}

	@Override
	public void selectMenu() {
		// TODO Auto-generated method stub

	}

}
