package com.itwill.jsp2.ojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oracle.jdbc.OracleDriver;

public class OjdbcTest {
	private static final Logger log = LoggerFactory.getLogger(OjdbcTest.class);
	
	@Test
	public void testSelect() throws SQLException {
		// TODO: Oracle DB jspstidy 계정으로 접속, POST 테이블 내용을 검색/출력
		
		// 1. Oracle JDBC 라이브러리를 등록.
		DriverManager.registerDriver(new OracleDriver());
		
		log.debug("오라클 드라이버 등록 성공");
		
		// 2. Oracle 접속 - Connection 객체 생성.
		
		final String URL = "jdbc:oracle:thin:@Localhost:1521:xe";
		final String USER = "jspstudy";
		final String PASSWORD = "jspstudy";
			
		Connection conn = null;
		
		conn = DriverManager.getConnection(URL, USER, PASSWORD);
		Assertions.assertNotNull(conn);
		//-> conn 객체가 null이 아니면 단위 테스트 성공(success), 그렇지 않으면 실패(failure).
		log.debug("conn= " + conn);
				
		
		
		
		// 3. Statement 객체 생성.
		final String sql = "select * from posts";
		PreparedStatement stmt = conn.prepareStatement(sql);
		Assertions.assertNotNull(stmt);
		log.debug("stmt= " + stmt);
		
		// 4. SQL 실행. insert, delete 는 update .. select 는 질의 query
		ResultSet rs = stmt.executeQuery();
		Assertions.assertNotNull(rs);
		log.debug("rs= " + rs);
		
		//5. 결과 처리.
		while (rs.next()) { // ResultSet이 (다음) 레코드(행)를 가지고 있으면
			int id = rs.getInt("ID"); // ID 컬럼의 값을 읽음.
			String title = rs.getString("TITLE"); //TITLE 컬럼의 값을 읽음.
			String content = rs.getString("CONTENT");
			String author = rs.getString("AUTHOR");
			LocalDateTime createdTime = rs.getTimestamp("CREATED_TIME").toLocalDateTime();
			LocalDateTime modifiedTime = rs.getTimestamp("MODIFIED_TIME").toLocalDateTime();
			
			log.debug("{} | {} | {} | {} | {} | {}",
					id, title, content, author, createdTime, modifiedTime);			
					
		}
		
		// 사용했었던 리소스(들)을 해제. (객체가 생성된 순서의 반대)로 해제.
		rs.close();
		stmt.close();
		conn.close();
		log.debug("오라클 접속 해제");
		
		// 어떤 메서드를 호출했을 때 어떤 경우에 .. 널이 아닌 경우나.. 
		// 개발자의 의도대로 코드를 확인해보려는게 단위테스트의 목적
		// 개발자의 주장에 원하는게 왔는지 사용하는게 Assertions , 
		// Assertions 
		
		// Connection Pool . 여러 사용자가 요청이 오면 서버에 부하가 걸리는데
		// 시작과 동시에 초기화함가 동시에 미리 데이터베이스와 커넥션을 맺어 두는것
		// 웹서버가 끝날때가지 유지가 되는 것.
		// 미리 맺어둔 커넥션을 빌려주고 끝나게 되면 풀에게 되돌려 주는것
		// 커넥션 풀이 다 사용중이라면 기다리고 있다가 빈 커넥션이 생기면 거기를 사용하는것.
		// 이미 커넥션이 맺어져 있으니 물리적인 시간은 필요없어지게되고 
		
		// 커넥션과 클로즈를 여러번 하지 않고 웹서버가 실행되는 시간에 한정된 개수 커넥션을 유지하면서
		// 데이터 베이스를 사용하는것을 Connection Pool
		
	}

}
