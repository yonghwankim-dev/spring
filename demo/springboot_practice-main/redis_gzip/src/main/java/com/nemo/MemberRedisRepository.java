package com.nemo;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRedisRepository {
	private final RedisTemplate<String, Member> redisTemplate;

	public MemberRedisRepository(RedisTemplate<String, Member> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void save(Member member) {
		redisTemplate.opsForValue().set(member.getId().toString(), member);
	}

	public Member findByIdOrThrow(Long memberId) throws IllegalArgumentException {
		Member member = redisTemplate.opsForValue().get(memberId.toString());
		if (member == null) {
			throw new IllegalArgumentException("Member not found");
		}
		return member;
	}
}
