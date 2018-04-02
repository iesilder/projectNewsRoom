/*
 * 메인메뉴
 *
 * 개발은 자기 컴퓨터로 하고 그걸 복사해서 붙여넣기.
 * 이후 커밋 필수.
 * 
 * 주석 최대한 많이 달도록 노력하기.
 * 
 * 20180307
 * 유태선
 */

package com.webjjang.main;

import com.webjjang.board.controller.FreeBoardController;
import com.webjjang.board.controller.LoginController;
import com.webjjang.board.controller.NadoNewsController;
import com.webjjang.board.controller.NewsController;
import com.webjjang.board.controller.QNABoardController;
import com.webjjang.crawler.NaverNewsCrawler;
import com.webjjang.util.InUtil;
import com.webjjang.util.KeyWordUtil;
import com.webjjang.util.OutUtil;

public class Main {
	public static String keyWord;
	public static void main (String[] args) {
		// TODO Auto-generated method stub
		OutUtil.printTitle("환영합니다!");
		
		while(true) {
			OutUtil.printMenu("1.회원관리\n2.뉴스보기\n3.나도기자\n4.자유게시판\n5.Q&A\n0.종료", "*", 14);
			String menu = InUtil.getMenu("메뉴를 선택하세요");
			switch(menu) {
			case "1":
				LoginController loginController = new LoginController();
				loginController.selectMenu();
				break;
			case "2":
				String keyWord = InUtil.getMenu("뉴스게시판 입니다.", "검색어를 입력하세요");
				KeyWordUtil.setKeyword(keyWord); 
				NaverNewsCrawler.crawler(keyWord);
				NewsController newsController
				= new NewsController();
				newsController.selectMenu();
				break;
			case "3":
				NadoNewsController nadoNewsController = new NadoNewsController();
				nadoNewsController.selectMenu();
				break;
			case "4":
				FreeBoardController freeBoardController = new FreeBoardController();
				try {
					freeBoardController.selectMenu();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "5":
				QNABoardController qnaBoardController = new QNABoardController();
				qnaBoardController.selectMenu();
				break;
			case "0":
				System.exit(0);
				break;
			default:
				System.out.println("다시 입력하세요.");
				break;
			}
		
		}//end of while;
	}//end of main()

}//end of class Main
