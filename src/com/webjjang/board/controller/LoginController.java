package com.webjjang.board.controller;

import com.webjjang.board.dto.MemberDTO;
import com.webjjang.board.service.MemJoinService;
import com.webjjang.board.service.MemLoginService;
import com.webjjang.main.SelectMenuInterface;
import com.webjjang.util.InUtil;
import com.webjjang.util.MemManageUtil;
import com.webjjang.util.OutUtil;

public class LoginController implements SelectMenuInterface {
	@Override
	public void selectMenu() {
		// 로그인을 위한 장소
		OutUtil.printTitle("회원관리 탭");

		// 무한 반복을 위해 while문 삽입.
		while (true) {
			try {
				OutUtil.printMenu("1.로그인 하기\n2.회원가입하기\n0.이전메뉴", "/", 14);
				// 메뉴를 입력받아 각각을 출력하는 프로그램
				String menu = InUtil.getMenu("메뉴를 선택하세요");
				switch (menu) {
				case "1":
					// 객체 생성
					MemLoginService memLoginService = new MemLoginService();
					String id = InUtil.getMenu("아이디를 입력하세요."); // id를 입력받아 process에 넣기
					String pw = InUtil.getMenu("비밀번호를 입력하세요."); // pw를 입력받아 process에 넣기
					MemberDTO userDTO = memLoginService.process(id, pw);
					// 처리
					// userDTO가 null이 아니면 회원정보가 있다는 말.
					if (userDTO != null) { // userDTO가 null이 아니면 회원용 메뉴로 들어간다.
						System.out.println(userDTO.getName() + "님 환영합니다!");
						MemManageController memManageController = new MemManageController();
						memManageController.selectMenu(userDTO);
						break;
					} else if (userDTO == null) {// userDTO가 null이면 while을 돌린다.
						System.out.println("ID와 PW를 다시 확인하세요.");
						break;
					}

				case "2":
					OutUtil.printTitle("회원가입입니다. \n전화번호, 이메을을 제외한 나머지 모든 정보를 입력하셔야 합니다.");
					// 객체 생성
					// 전체 객체가 전부 오라클에서 not null이다.
					MemJoinService memJoinService = new MemJoinService();
					MemManageUtil memManageUtil = new MemManageUtil();
					String joinId = memManageUtil.patternCheck("^[A-Za-z0-9]{6,10}$",
							"아이디는 영문자와 숫자만 가능합니다.\n아이디에 공백은 허용하지 않습니다.\n아이디는 최소 6자리, 최대 10자리 입니다.\n아이디를 입력하세요.",
							"아이디는 영문자와 숫자만 가능합니다.\n아이디에 공백은 허용하지 않습니다.\n다시 시도하세요.");
					if (memManageUtil.nullCheck(joinId) == false)
						break;
					String joinPw = memManageUtil.patternCheck("^(?=.*[a-zA-Z]+)(?=.*[!@#$%^*+=-]|.*[0-9]+).{8,16}$",
							"비밀번호는 영문 숫자가 혼합되어야 합니다.\n비밀번호에 공백을 입력 할 수 없습니다.\n비밀번호는 최소 8자리 최대 16자리입니다.\n비밀번호를 입력하세요.",
							"비밀번호는 공백이 들어가서는 안 됩니다.\n다시 입력하세요.");
					if (memManageUtil.nullCheck(joinPw) == false)
						break;
					String joinName = memManageUtil.patternCheck("^[A-Za-z0-9가-힣]{1,10}$", "이름을 입력하세요.",
							"이름은 최소 1자 최대 10자 입력이 가능합니다.");
					if (memManageUtil.nullCheck(joinName) == false)
						break;
					String joinNick = memManageUtil.patternCheck("^[A-Za-z0-9가-힣]{1,10}$", "닉네임을 입력하세요.",
							"닉네임은 최소 1자 최대 10자 입력이 가능합니다.");
					if (memManageUtil.nullCheck(joinNick) == false)
						break;
					String joinTel = memManageUtil.patternCheck("^[0-9-]*$", "전화번호를 입력하세요.", "전화번호는 숫자와 -만 가능합니다.");
					if (memManageUtil.nullCheck(joinTel) == false)
						break;
					String joinEmail = memManageUtil.rePatternCheck("^[@.]*$", "이메일을 입력하세요.", "유효한 이메일이 아닙니다.");
					if (memManageUtil.nullCheck(joinEmail) == false)
						break;
					// 처리
					memJoinService.process(joinId, joinPw, joinName, joinNick, joinTel, joinEmail);
					System.out.println("가입이 완료되었습니다!");
					break;

				case "0":
					return;

				default:
					System.out.println("다시 입력하세요.");
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("오류가 났습니다. 다시 시도하세요.");
			}
		} // end of while;

	}// end of selectMenu()

}// end of class BoardController
