package com.itwill.blog.repository;

import static com.itwill.blog.datasourceutil.DataSourceUtil.close;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.blog.datasourceutil.DataSourceUtil;
import com.itwill.blog.domain.Member;
import com.zaxxer.hikari.HikariDataSource;

public enum MemberDao {
	INSTANCE;
	
	// 디버그 로그를 출력하기 위한 객체 생성.
	private static final Logger log = LoggerFactory.getLogger(MemberDao.class);
	// HikariCP를 사용한 데이터베이스 커넥션 풀 객체 생성
	private final HikariDataSource ds = DataSourceUtil.INSTANCE.getDataSource();
	
	// 회원 정보의 아이디과 패스워드를 선택하는 SQL 쿼리문
	private static final String SQL_SELECT_BY_USERNAME_AND_PASSWORD =
			"select * from members where username = ? and password = ?";
	
	// 회원 정보 1개의 아이디와 패스워드의 데이터를 선택 반환
	public Member select(String username, String password) {

		log.debug(SQL_SELECT_BY_USERNAME_AND_PASSWORD);
		log.debug("select(username = {}, password={})", username, password);

		Member member = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_BY_USERNAME_AND_PASSWORD);
			stmt.setString(1, username);
			stmt.setString(2, password);

			rs = stmt.executeQuery();

			if (rs.next()) {
				member = toMemberFromResultSet(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt, rs);
		}

		return member;
	}
	
	// 유저 정보를 삽입하는 SQL 쿼리문
	private static final String SQL_INSERT =
			"insert into members (username, name, phone, birthday, password, email, created_time, modified_time) "
			+ "values (?, ?, ?, ?, ?, ?, systimestamp, systimestamp)";
	
	// 회원 정보를 삽입하여 성공 값을 반환
	public int insert(Member member) {
		
		log.debug("member({})",member); // 조회한 데이터 로그 출력
		
		log.debug(SQL_INSERT);
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);
			stmt.setString(1, member.getUsername());
			stmt.setString(2, member.getName());
			stmt.setString(3, member.getPhone());
			stmt.setString(4, member.getBirthday());
			stmt.setString(5, member.getPassword());
			stmt.setString(6, member.getEmail());
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt);
		}
		return result;
	}
	
	private static final String SQL_UPDATE_BY_POINT = 
			"update members "
			+ "set points = points + ? "
			+ "where id = ?";
	
	public boolean updatePoint(Integer id, Integer points) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE_BY_POINT);
			
			stmt.setInt(1, points);
			stmt.setInt(2, id);
		
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			close(conn, stmt);
		}
		return result > 0;
		
	}
	
	// 회원 정보의 아이디과 패스워드를 선택하는 SQL 쿼리문
		private static final String SQL_SELECT_BY_ID_AND_POINTS =
				"select * from members where id = ?";
		
		// 회원 정보 1개의 아이디와 패스워드의 데이터를 선택 반환
		public int select(Integer id) {

			log.debug(SQL_SELECT_BY_USERNAME_AND_PASSWORD);
			log.debug("select(username = {}, password={})", id);

			int result = 0;
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;

			try {
				conn = ds.getConnection();
				stmt = conn.prepareStatement(SQL_SELECT_BY_ID_AND_POINTS);
				stmt.setInt(1, id);

				rs = stmt.executeQuery();

				if (rs.next()) {
					result = rs.getInt("points");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(conn, stmt, rs);
			}

			return result;
		}
	
		
	// 회원정보를 담은 Member 객체를 생성하는 메서드
	public Member toMemberFromResultSet(ResultSet rs) throws SQLException {
	    Integer id = rs.getInt("id"); // 회원 ID
	    String username = rs.getString("username"); // 사용자 아이디
	    String password = rs.getString("password"); // 비밀번호
	    String name = rs.getString("name"); // 사용자 이름
	    String phone = rs.getString("phone"); // 핸드폰
	    String birthday = rs.getString("birthday"); //생일
	    String email = rs.getString("email"); // 이메일
	    Integer points = rs.getInt("points"); // 포인트
	    Timestamp createdTime = rs.getTimestamp("created_time"); // 생성 시간
	    Timestamp modifiedTime = rs.getTimestamp("modified_time"); // 수정 시간
	    
	    // 멤버 객체 빌드패턴으로 사용 생성
		Member member = Member.bulider()
				.id(id)
				.username(username)
				.password(password)
				.email(email)
				.name(name)
				.phone(phone)
				.birthday(birthday)
				.points(points)
				.createdTime(createdTime)
				.modifiedTime(modifiedTime)
				.build();
		
		return member;
	}
}
