package com.nemo;

import java.time.Duration;

import lombok.Getter;

@Getter
enum LocalCache {
	MEMBER(LocalCacheName.MEMBER, Duration.ofMinutes(2).getSeconds(), 200000),
	;
	private final String cacheName;
	private final long expiredAfterWrite;
	private final long maximumSize;

	LocalCache(String cacheName, long expiredAfterWrite, long maximumSize) {
		this.cacheName = cacheName;
		this.expiredAfterWrite = expiredAfterWrite;
		this.maximumSize = maximumSize;
	}
}
