package com.itwill.jsp2.dbcp;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DbcpTest {
	private static final Logger log = LoggerFactory.getLogger(DbcpTest.class);
	
	@Test
	public void testHikariCP() throws SQLException {
		// HikariCP의 환경 설정을 할 수 있는 객체를 생성.
		HikariConfig config = new HikariConfig();
		
		// 데이터베이스에 접속할 수 있는 환경 설정.
		config.setDriverClassName("oracle.jdbc.OracleDriver"); //드라이버 클래스 이름을 세팅.
		// 이게 있어야 생성자를 생성할 수 있음.
		
		config.setJdbcUrl("jdbc:oracle:thin:@Localhost:1521:xe");
		config.setUsername("jspstudy");
		config.setPassword("jspstudy");
		
//		config.setMinimumIdle(0); // 제일 처음에 만들어지는 pool의 갯수?
//		config.getMaximumPoolSize() // 최대 몇개까지 맺어지게 할건지
//		config.setIdleTimeout(); // 놀고있는 애들을 몇개까지 비워 두겠나
		
		// DataSource(데이터 소스 - 커넥션 풀) 객체 생성 
		// -> 데이터베이스 서버와 물리적인 연결(커넥션)이 맺어짐.
		HikariDataSource ds = new HikariDataSource(config); 
		log.debug("ds ={}", ds);
		// 환경설정 정보를 가지고서 데이터 소스에서 커넥션이 맺어지는 곳
		
		// 데이터 소스(커넥션 풀)에서 이미 생성된 커넥션 객체를 빌려옴. 
		Connection conn = ds.getConnection(); // 이름은 똑같지만 커넥션은 이미 만들어진 상태에서 빌려오기만 함
		Assertions.assertNotNull(conn);
		log.debug("connection = {}", conn);
		
		// Statement 생성, SQL 실행(executeQuery, executeUpdate), 결과 처리
		
		// 사용했던 커넥션 객체를 데이터 소스(커넥션 풀)에 반환. 
		conn.close();
		log.debug("커넥션이 반환 성공.");
		
	}
}
