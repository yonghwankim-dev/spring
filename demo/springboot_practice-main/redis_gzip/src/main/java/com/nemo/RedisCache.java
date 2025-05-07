package com.nemo;

import java.time.Duration;

import com.fasterxml.jackson.core.type.TypeReference;

import lombok.Getter;

@Getter
enum RedisCache {
	MEMBER_CACHE_NAME(RedisCacheName.MEMBER, Duration.ofMinutes(30), Member.class),
	;

	private final String cacheName;
	private final Duration expiredAfterWrite;
	private final TypeReference<?> typeRef;

	RedisCache(String cacheName, Duration expiredAfterWrite, Class<?> clazz) {
		this.cacheName = cacheName;
		this.expiredAfterWrite = expiredAfterWrite;
		this.typeRef = new TypeReference<>() {
			@Override
			public String toString() {
				return clazz.getName();
			}
		};
	}
}
