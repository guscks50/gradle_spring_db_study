package gradle_spring_db_study.spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class MemberDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/* 결과가 1인경우 */
	public Member selectByEmail(String email) {
		String sql = "SELECT ID, EMAIL, PASSWORD, NAME, REGDATE FROM MEMBER WHERE email = ?";
		return jdbcTemplate.queryForObject(sql, new MemberRowMapper(), email);
	}

	public void insert(Member member) {
		String sql = "INSERT INTO MEMBER(EMAIL,PASSWORD,NAME,REGDATE) VALUES(?,?,?,?)";
		PreparedStatementCreator psc = new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql, new String[] { "id" });
				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
				pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
				return pstmt;
			}
		};

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(psc, keyHolder);
		Number keyValue = keyHolder.getKey();
		member.setId(keyValue.longValue());
	}

	public void update(Member member) {
		String sql = "UPDATE MEMBER SET NAME=?, PASSWORD=? WHERE EMAIL=?";
		jdbcTemplate.update(sql, member.getName(), member.getPassword(), member.getEmail());
	}

	public void delete(Member member) {
		String sql = "DELETE FROM MEMBER WHERE EMAIL=?";
		PreparedStatementCreator psc = new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member.getEmail());
				return pstmt;
			}
		};
	}

	public Collection<Member> selectAll() {
		String sql = "SELECT ID, EMAIL, PASSWORD, NAME, REGDATE FROM MEMBER";
		return jdbcTemplate.query(sql, new MemberRowMapper());
	}

	public int count() {
		String sql = "SELECT COUNT(*) FROM MEMBER";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	/*
	 * private static long nextid = 0;
	 * 
	 * private Map<String, Member> map = new HashMap<>();
	 * 
	 * public Member selectByEmail(String email) { return map.get(email); }
	 * 
	 * public void insert(Member member) { member.setId(++nextid);
	 * map.put(member.getEmail(), member); }
	 * 
	 * public void update(Member member) { map.put(member.getEmail(), member); }
	 * 
	 * public Collection<Member> selectAll(){ return map.values(); }
	 */
}
