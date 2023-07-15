package com.oauth;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

	private final NamedParameterJdbcTemplate template;

	public MemberRepository(NamedParameterJdbcTemplate template) {
		this.template = template;
	}

	public Member save(Member member) {
		if (findByOauthId(member.getOauthId()).isPresent()) {
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("email", member.getEmail());
			param.addValue("name", member.getName());
			param.addValue("imageUrl", member.getImageUrl());
			param.addValue("oauthId", member.getOauthId());
			template.update(
				"UPDATE member SET email = :email, name = :name, imageUrl = :imageUrl WHERE oauthId = :oauthId", param);
			return findByOauthId(member.getOauthId()).orElseThrow();
		}
		KeyHolder keyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("oauthId", member.getOauthId());
		param.addValue("email", member.getEmail());
		param.addValue("name", member.getName());
		param.addValue("imageUrl", member.getImageUrl());
		param.addValue("role", member.getRole().getKey());
		template.update(
			"INSERT INTO member(oauthId, email, name, imageUrl, role) VALUES(:oauthId, :email, :name, :imageUrl, :role)",
			param,
			keyHolder);
		return findById(Objects.requireNonNull(keyHolder.getKey()).longValue()).orElseThrow();
	}

	public Optional<Member> findById(Long id) {
		return template.query("SELECT * FROM member WHERE id = :id", new MapSqlParameterSource("id", id),
				memberMapper())
			.stream()
			.findAny();
	}

	public Optional<Member> findByOauthId(String oauthId) {
		return template.query("SELECT * FROM member WHERE oauthId = :oauthId",
				new MapSqlParameterSource("oauthId", oauthId), memberMapper())
			.stream()
			.findAny();
	}

	private RowMapper<Member> memberMapper() {
		return (rs, rowNum) -> Member.builder()
			.id(rs.getLong("id"))
			.oauthId(rs.getString("oauthId"))
			.email(rs.getString("email"))
			.name(rs.getString("name"))
			.imageUrl(rs.getString("imageUrl"))
			.role(Role.from(rs.getString("role")))
			.build();
	}

}
