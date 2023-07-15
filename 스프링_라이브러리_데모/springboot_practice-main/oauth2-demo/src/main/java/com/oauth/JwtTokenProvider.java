package com.oauth;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
	@Value("${jwt.access-token.expire-length:10000}")
	private long accessTokenValidityInMilliseconds;
	@Value("${jwt.refresh-token.expire-length:10000}")
	private long refreshTokenValidityInMilliseconds;
	@Value("${jwt.token.secret-key:secret-key}")
	private String secretKey;

	public String createAccessToken(String payload) {
		return createToken(payload, accessTokenValidityInMilliseconds);
	}

	public String createRefreshToken() {
		byte[] array = new byte[7];
		new Random().nextBytes(array);
		String generatedString = new String(array, StandardCharsets.UTF_8);
		return createToken(generatedString, refreshTokenValidityInMilliseconds);
	}

	private String createToken(String payload, long expireLength) {
		Claims claims = Jwts.claims().setSubject(payload);
		Date now = new Date();
		Date validity = new Date(now.getTime() + expireLength);
		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(now)
			.setExpiration(validity)
			.signWith(SignatureAlgorithm.HS256, secretKey)
			.compact();
	}
}
