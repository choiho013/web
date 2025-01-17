package com.itwill.jsp2.datasourceutil;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static com.itwill.jsp2.datasourceutil.DataSourceUtil.close;

import java.sql.Connection;
import java.sql.SQLException;

//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariDataSource;

public class DataSourceUtilTest {
	private static final Logger log = LoggerFactory.getLogger(DataSourceUtilTest.class);
	private final DataSourceUtil dsUtil = DataSourceUtil.INSTANCE;
	private final HikariDataSource ds = DataSourceUtil.INSTANCE.getDataSource();
	
	@Test
	public void test() throws SQLException {
		Connection conn = ds.getConnection();
		assertNotNull(conn); //import static 문을 사용한 경우.
		log.debug("conn{} ",conn);
		
		close(conn, null); //import static 문을 사용한 경우.
		log.debug("커넥션 반환.");
	}
}
