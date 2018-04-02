package com.webjjang.board.service;

import com.webjjang.board.dao.NadoNewsCrawlingDAO;

//크롤링 서비스 DAO를 호출하고 예외가 있으면 controller로 넘겨준다.
public class NadoNewsCrawlingService {
	public void nadoNewsCrawling() throws Exception{
		NadoNewsCrawlingDAO nadoNewsCrawlingDAO = new NadoNewsCrawlingDAO();		
			nadoNewsCrawlingDAO.nadoNewsCrawling();
			
	}
}
