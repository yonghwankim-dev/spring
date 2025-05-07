package com.nemo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	private final MemberRedisRepository repository;

	public MemberService(MemberRedisRepository repository) {
		this.repository = repository;
	}

	public void save(Member member) {
		repository.save(member);
	}

	@Cacheable(
		value = RedisCacheName.MEMBER,
		key = "#memberId",
		unless = "#result == false"
	)
	public Member getMember(Long memberId) {
		return repository.findByIdOrThrow(memberId);
	}
}
