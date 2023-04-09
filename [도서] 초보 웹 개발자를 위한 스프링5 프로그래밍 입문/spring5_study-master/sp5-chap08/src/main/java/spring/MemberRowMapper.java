package spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 동일한 RowMapper 구현을 여러 곳에서 사용한다면 아래 코드처럼
 * RowMapper 인터페이스를 구현한 클래스를 만들어서
 * 코드의 중복을 막을 수 있다.
 *
 */
public class MemberRowMapper implements RowMapper<Member>{

	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member member = new Member(rs.getString("email"),
									rs.getString("password"),
									rs.getString("name"),
									rs.getTimestamp("regdate").toLocalDateTime());
									member.setId(rs.getLong("id"));
		return member;
	}
	
}
