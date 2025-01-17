package com.itwill.blog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.blog.domain.Member;
import com.itwill.blog.repository.MemberDao;

public enum MemberService {
	// 싱글톤 패턴 적용
	INSTANCE;
	
	// 데이터 베이스 접근을 위한 MemberDao 메서드 객체 생성(싱글톤)
	private final MemberDao memberDao = MemberDao.INSTANCE;
	
	// 디버그 로그 출력을 위한 객체 생성
	private static final Logger log = LoggerFactory.getLogger(MemberDao.class);
	
	// 로그인 서비스
	public Member signIn(String username, String password) {
		
		Member member = memberDao.select(username, password);
		log.debug("signIn result={}", member);
		return member;
	}
	
	// 회원가입 서비스
	public int signUp(Member member) {
		
		log.debug("signUp(member={})", member);
		int result = memberDao.insert(member);
		
		log.debug("insert result = {}", result);
		
		return result;
		
	}

	public boolean addPoints(int id, int points) {
		log.debug("addPoints(id={},points={})", id, points);
		
		if(points <= 0) {
			return false;
		}
		
		return memberDao.updatePoint(id, points);
	}
	public int getCurrentPoints(int id) {
		
		int result = memberDao.select(id);
		
		return result;
	}
	

}
