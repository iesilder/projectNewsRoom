package com.webjjang.board.controller;

import java.util.List;

import com.webjjang.board.dto.MemBoardCommDTO;
import com.webjjang.board.dto.MemberDTO;
import com.webjjang.board.service.MemBoardCommCheckService;
import com.webjjang.board.service.MemBoardCommListService;
import com.webjjang.board.service.MemBoardCommWriteService;
import com.webjjang.board.service.MemBoardDelCommService;
import com.webjjang.main.SelectMenuInterface;
import com.webjjang.util.InUtil;
import com.webjjang.util.MemManageUtil;
import com.webjjang.view.board.MemBoardCommPrint;

public class MemBoardCommController implements SelectMenuInterface {

	public void selectMenu(MemberDTO userDTO, int no) {
		// TODO Auto-generated method stub
		while (true) {
			try {
				String menu = InUtil.getMenu("1. 댓글 쓰기\n2. 댓글 삭제\n0.이전 메뉴로", "실행하실 메뉴를 입력해주세요.");
				switch (menu) {
				case "1":
					MemBoardCommWriteService memBoardCommWriteService = new MemBoardCommWriteService();
					MemManageUtil memManageUtil = new MemManageUtil();
					String title = memManageUtil.patternCheck("^.{1,1000}$", "댓글을 입력하세요.", "댓글은 1000자를 넘을 수 없습니다.");
					memBoardCommWriteService.process(userDTO.getId(), title, no);
					
					//댓글 재출력
					MemBoardCommListService memBoardCommListService = new MemBoardCommListService();
					MemBoardCommPrint memBoardCommPrint = new MemBoardCommPrint();
					List<MemBoardCommDTO> list1 = memBoardCommListService.process(no);
					memBoardCommPrint.list(list1);
					break;
				case "2":
					MemBoardCommCheckService memBoardCommCheckService = new MemBoardCommCheckService();
					MemBoardCommDTO boardCommDTO = new MemBoardCommDTO();
					String id = userDTO.getId();
					int commno = InUtil.getIntWithCheck("삭제할 댓글의 번호를 입력하세요.");
					boardCommDTO = memBoardCommCheckService.process(commno, id, no);
					if (id.equals(boardCommDTO.getId())) {
						String menu1 = InUtil.getMenu("정말로 삭제하시겠습니까?(y/n)");
						switch (menu1) {
						case "y":
							MemBoardDelCommService memBoardCommDelService = new MemBoardDelCommService();
							memBoardCommDelService.process(commno, no);
							System.out.println("댓글이 삭제되었습니다.");
							break;
						case "n":
							System.out.println("삭제를 종료합니다.");
							break;

						default:
							System.out.println("y,n 중 하나를 다시 입력하세요.");
							break;
						}

					} else {
						System.out.println("자신이 작성한 댓글만 삭제 할 수 있습니다.");
						break;
					}

				case "0":
					System.out.println("이전 메뉴로 돌아갑니다.");
					return;

				default:
					System.out.println("잘못 입력하셨습니다. \n다시 입력하세요.");
					break;
				}

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("오류가 발생하였습니다. 다시 시도하세요.");
			}
		} // end of while;
	}

	@Override
	public void selectMenu() {
		// TODO Auto-generated method stub

	}

}
