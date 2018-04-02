/*
 * 프로세스 인터페이스
 * 
 * 프로세스 특성상 괄호 안 에 오브젝트를 받을 필요가 없으면 괄호 안에 null을 입력
 * 
 * 20180307
 * 유태선
 */
package com.webjjang.board.controller;

public interface ProcessInterface {
	public Object process(Object obj) throws Exception;
}
