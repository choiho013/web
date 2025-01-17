package com.itwill.spring2.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class) // 괄호안에 있는 클래스가 메인역할을하고 테스트 메서드를 실행 시킨다
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/application-context.xml"}) // 로케이션즈가 스트링 배열이기 때문에 초기화 하듯
// 컨텍스트 가 여러개가 있을 수 있기 때문에 
// 톰캣이 시작되면서 리스너가 읽어들이는 동작을 ExteinWith가 대신하고 컨택스트가 어디에 있는지 알려줘야함
public class DataSourceTest {
	
	/*
	 * 의존성 주입(DI: Dependency Injection), 제어의 역전(IoC: Inversion of Control)
	 * 전통적인 자바 개발 방법에서는 객체를 사용하는 곳에서 객체를 생성하고, 그 기능을 이용함.
	 * 스프링 프레임워크에서는 스프링 컨테이너가 필요한 객체들을 미리 생성하고 관리하고 있다가
	 * 객체를 필요로 하는 곳에서는 변수 선언과 애너테이션으로 
	 * 스프링 컨테이너가 관리하는 빈(자바 객체)을 주입 받아서 사용.
	 * 
	 *  웹 mvc 프레임 워크, 의존성 주입을 제공하는 프레임 워크 제어의 모든것을 스프링 컨테이너가 관리하고
	 *  개발자 유지보수를 수월하게 하기 위해서.
	 *  
	 *  자바 코드를 변경할 필요가 없다는게 컴파일을 새로할 필요가없고 
	 *  스프링 컨테이너가 xml을 보고 생성하기 때문에 xml만 수정하면 
	 *  
	 *  의존성 주입
	 *  객체 생성은 프레임워크  주입받아서 사용
	 *  프로그램의 유지보수를 더 쉽게 하기 위해서.
	 *  
	 */
	
	@Autowired // 자동으로 와이어..
	// 스프링 프레임워크에게 히카리데이터 소스에 변수에 생성된 객체를 넣으라는 말 - 의존성 주입
	// 스프링 프레임워크가 xml을 읽어서 
	// 필드를 선언 하고 스프링 프레임워크에 받아서 사용하면 됨.
	private HikariDataSource ds;
	
	@Autowired
	private SqlSessionFactory sqlSession;
	
	@Test
	public void testDataSource() throws SQLException {
		Assertions.assertNotNull(ds);
		log.debug("ds={}", ds);
		
		Connection conn = ds.getConnection();
		Assertions.assertNotNull(conn);
		log.debug("conn={}", conn);
		
		conn.close();
		log.debug("커넥션 객체를 풀에 반환.");
	}
	
	
	@Test
	public void testSqlSession() {
		Assertions.assertNotNull(sqlSession);
		log.debug("sqlSession={}", sqlSession);
	}

}
