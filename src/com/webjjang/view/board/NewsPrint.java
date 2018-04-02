package com.webjjang.view.board;

import java.util.List;	

import com.webjjang.board.dto.NewsDTO;

	public class NewsPrint {
		public void list(List<NewsDTO> list){
//			System.out.println("NewsPrint.list()");
			for(int i = 0; i < list.size(); i++){
				printUtil(list.get(i));
			}
			
		}
		//기사 본문을 출력한다. (50번째 문자마다 개행을 한다.)
		public void view(NewsDTO newsDTO){
//			System.out.println("NewsPrint.view()");
			printUtil(newsDTO);
			System.out.println("--------------------------------------------------");
			System.out.println("URL:   "+newsDTO.getUrl());
			System.out.println("요약내용:   "+newsDTO.getContent());
			System.out.println("--------------------------------------------------");
		}
	
	
	//printf를 이용해서 각각의 자릿수에 맞게 변수들을 호출한다. 기사 본문은 나중에 출력하고. 일단 넘버, 제목, 작성자, 작성일, 조회수만 위에 출력한다.
	private void printUtil(NewsDTO newsDTO){
		System.out.printf("[%3d][%-20s][%15s][%3s]\n"
				, newsDTO.getNo()
				, newsDTO.getTitle()
				, newsDTO.getPubDate()
				, newsDTO.getHit()
				);
	}
}
