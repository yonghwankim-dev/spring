package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class MemberDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public MemberDao(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Member selectByEmail(String email) {
		List<Member> results = jdbcTemplate.query("select * from member where email=?",
//			방법 1 : RowMapper<Mmeber> 인터페이스의 구현을 바로 작성 				
//			new RowMapper<Member>() {
//			@Override
//			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Member member = new Member(rs.getString("email"),
//											rs.getString("password"),
//											rs.getString("name"),
//											rs.getTimestamp("regdate").toLocalDateTime());
//				member.setId(rs.getLong("id"));
//				return member;
//			}
//		}
				// 방법2 : 방법1에서 RowMapper<Member> 인터페이스 구현에서 람다를 적용한 방법
				(ResultSet rs, int rowNum) ->{
					Member member = new Member(
												rs.getString("email"),
												rs.getString("password"),
												rs.getString("name"),
												rs.getTimestamp("regdate").toLocalDateTime()
												);
					member.setId(rs.getLong("id"));
					return member;
				}
		, email);
		
//		방법3 : RowMapper 인터페이스를 구현하여 객체를 생성
//		List<Member> results = jdbcTemplate.query(
//													"select * from member where email=? and name = ?",
//													new MemberRowMapper()
//													, email);
		
		return results.isEmpty() ? null : results.get(0);
	}

	public void insert(Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		// 자동 생성된 키 값을 KeyHolder에 보관한다.
		// 방법 1 : 직접 오버라이드 구현
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
												"insert into member(email, password, name, regdate) values(?,?,?,?)"
											, new String[] {"ID"});
				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
				pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
				
				return pstmt;
			}
		},keyHolder);
		
//		방법 2 : 방법1에 람다 8을 적용
//		jdbcTemplate.update((Connection con)->{
//			PreparedStatement pstmt = con.prepareStatement(
//					"insert into member(email, password, name, regdate) values(?,?,?,?)"
//				, new String[] {"ID"});
//			pstmt.setString(1, member.getEmail());
//			pstmt.setString(2, member.getPassword());
//			pstmt.setString(3, member.getName());
//			pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
//			
//			return pstmt;
//		},keyHolder);
		
		Number keyValue = keyHolder.getKey();
		member.setId(keyValue.longValue());
	} 
	
	// int update(String sql, Object... args)
	// update() 메서드를 이용하여 insert, update, delete 쿼리 연산을 수행할 수 있다.
	public void update(Member member) {
		jdbcTemplate.update("update member set name = ?, password = ? where email = ?",
							member.getName(), member.getPassword(), member.getEmail());
	}

	public List<Member> selectAll() {
		// 모든 Member 조회, MemberRowMapper : RowMapper<T> 인터페이스의 구현 클래스
		List<Member> results = jdbcTemplate.query("select * from member", new MemberRowMapper());
		
		return results;
	}
	
	/**
	 * queryForObject() 메서드는 쿼리 실행 결과 행이 한 개인 경우에 사용할 수 있는 메서드이다.
	 * 두번째 파라미터는 칼럼을 읽어올때 사용할 타입을 지정한다.
	 */
	public int count() {
		Integer count = jdbcTemplate.queryForObject("select count(*) from member", Integer.class);
		return count;
	}
	
	/**
	 * queryForObject() 메서드는 인자를 전달할 수 있다.
	 */
	public int count(int id){
		Integer count = jdbcTemplate.queryForObject("select count(*) from member where id = ?", Integer.class, id);
		return count;
		
	}
}
