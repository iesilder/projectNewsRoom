package com.webjjang.board.controller;

import java.util.List;

import com.webjjang.board.dto.MemberDTO;
import com.webjjang.board.service.MemDelUserService;
import com.webjjang.board.service.MemListService;
import com.webjjang.board.service.MemLoginService;
import com.webjjang.board.service.MemUpdateService;
import com.webjjang.board.service.MemViewService;
import com.webjjang.main.SelectMenuInterface;
import com.webjjang.util.InUtil;
import com.webjjang.util.MemManageUtil;
import com.webjjang.util.OutUtil;
import com.webjjang.view.board.MemPrint;

public class MemManageController implements SelectMenuInterface {

	public void selectMenu(MemberDTO userDTO) {
		// while을 이용해 메뉴를 계속 출력

		while (true) {
			try {
				OutUtil.printMenu("1.회원게시판접속\n2.회원 명단 보기\n3.회원 찾기\n4.내 정보 보기\n5.내 정보 수정\n6.탈퇴\n0.이전메뉴", "/", 14);
				String menu = InUtil.getMenu("메뉴를 선택하세요");

				switch (menu) {
				case "1":
					System.out.println("회원 게시판에 접속합니다.");
					MemBoardController memBoardController = new MemBoardController();
					memBoardController.selectMenu(userDTO);
					break;

				case "2":
					System.out.println("회원 명단 보기입니다.");
					// 객체를 생성 및 처리.

					MemListService memListService = new MemListService();
					MemPrint memPrint = new MemPrint();
					List<MemberDTO> list = memListService.process();
					memPrint.list(list);
					break;

				case "3":
					System.out.println("회원 찾기입니다.");
					MemManageUtil print = new MemManageUtil();
					String inputId = InUtil.getMenu("찾으실 회원의 아이디(ID)를 입력하세요 ");
					MemViewService memViewService = new MemViewService();
					MemberDTO memberDTO = memViewService.process(inputId);
					print.printUserInfo(memberDTO);
					break;

				case "4":
					System.out.println("내 정보 보기입니다.");
					MemManageUtil memManageUtil = new MemManageUtil();
					MemViewService memViewService2 = new MemViewService();
					memManageUtil.printUserInfo(memViewService2.process(userDTO.getId()));
					break;

				case "5":
					System.out.println("내 정보 수정입니다.");
					// 객체 생성
					// 비밀번호를 입력 받아 동일하면 처리 진행
					String id = userDTO.getId();
					String pw = InUtil.getMenu("비밀번호를 입력하세요.");
					MemLoginService memLoginService = new MemLoginService();
					MemberDTO updateUserInfo = memLoginService.process(id, pw);
					// 처리
					if (pw.equals(updateUserInfo.getPw())) {
						// 객체 호출
						MemManageUtil memManageUtil1 = new MemManageUtil();
						MemViewService memViewService3 = new MemViewService();
						MemUpdateService memUpdateService = new MemUpdateService();
						// 처리문
						memManageUtil1.printUserInfo(memViewService3.process(id));
						OutUtil.printMenu("수정할 내용을 입력하세요.\n바꾸실 내용이 없다면 엔터로 넘어가시면 됩니다.", "-", 14);
						memManageUtil1.printUserInfo(memUpdateService.process(updateUserInfo.getId(),
								memManageUtil1.update("비밀번호", "pw", userDTO),
								memManageUtil1.update("이름", "name", userDTO),
								memManageUtil1.update("닉네임", "nick", userDTO),
								memManageUtil1.update("전화번호", "tel", userDTO),
								memManageUtil1.update("이메일", "email", userDTO)));
						System.out.println("정상적으로 변경되었습니다.");
					} else {
						System.out.println("비밀번호가 틀렸습니다.\n다시 시도해주세요.");
					}
					break;

				case "6":
					System.out.println("탈퇴하시겠습니까?(yes/no)");
					String select = InUtil.getMenu("");
					switch (select) {
					case "y":
					case "Y":
					case "yes":
					case "YES":
						String delId = userDTO.getId();
						String delPw = InUtil.getMenu("삭제를 위해 비밀번호를 입력하세요.");
						if (delPw.equals(userDTO.getPw())) {
							MemDelUserService memDelUserService = new MemDelUserService();
							memDelUserService.process(delId, delPw);
							System.out.println("탈퇴하셨습니다.");
							return;
						} else {
							System.out.println("비밀번호가 틀렸습니다. 다시 시도하세요.");
							break;
						}
					case "n":
					case "N":
					case "no":
					case "NO":
						break;

					default:
						System.out.println("y/n만 입력하십시오.");
						break;
					}

				case "0":
					return;

				default:
					System.out.println("잘못 입력하셨습니다.\n다시 입력하세요.");
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("에러가 발생하였습니다. 다시 시도하세요.");
			}
		} // end of while;

	}

	@Override
	public void selectMenu() {
		// TODO Auto-generated method stub

	}

}// end of class MEmManageController
