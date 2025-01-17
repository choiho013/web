package com.itwill.jsp2.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.domain.Member;

public class MemberDaoTest {
	private static final Logger log = LoggerFactory.getLogger(MemberDaoTest.class);
	private final MemberDao memberDao = MemberDao.INSTANCE;
	
	
	//MemberDao.insert() 메서드 단위 테스트
//	@Test
	public void testInsert() {
		Member member = Member.bulider()
				.username("admin").password("admin1234").email("admin@itwill.com").build();
		int result = memberDao.insert(member);
		Assertions.assertEquals(1, result);
	}
	
	@Test
	public void testSelect(){
		//username과 password가 일치하는 사용자가 있는 경우.		
		Member m1 = memberDao.select("admin", "admin1234");
		Assertions.assertNotNull(m1);

		//username과 password가 일치하는 사용자가 없는 경우.
		Member m2 = memberDao.select("admin", "1234");
		Assertions.assertNull(m2);
		
	}
	
}
