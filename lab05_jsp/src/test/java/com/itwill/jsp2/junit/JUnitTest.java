package com.itwill.jsp2.junit;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JUnitTest {
	private static final Logger log = LoggerFactory.getLogger(JUnitTest.class);
	
//	@Test
	public void test1() {	//Test 애너테이션만 해주고 테스트는 JUnit테스트
		log.debug("JUnit1 테스트입니다...");
	}
	
	@Test
	public void test2() {	//Test 애너테이션만 해주고 테스트는 JUnit테스트
		log.debug("JUnit2 테스트입니다...");
	}
	
	//JUnit 엔진이 호출하는 순서 
	// DB 테스트나 메서드 테스트등 할 수 있음.
}
