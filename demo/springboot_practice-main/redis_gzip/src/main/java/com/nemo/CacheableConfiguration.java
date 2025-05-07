package com.nemo;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Caffeine;

@EnableCaching
@Configuration(proxyBeanMethods = false)
class CacheableConfiguration {
	private final RedisConnectionFactory redisConnectionFactory;
	private final ObjectMapper objectMapper;

	public CacheableConfiguration(RedisConnectionFactory redisConnectionFactory, ObjectMapper objectMapper) {
		this.redisConnectionFactory = redisConnectionFactory;
		this.objectMapper = objectMapper;
	}

	@Bean
	@Primary
	public CacheManager cacheManager() {
		return new CompositeCacheManager(localCacheManager(), redisCacheManager());
	}

	private CacheManager localCacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(
			Arrays.stream(LocalCache.values())
				.map(cache -> new CaffeineCache(
					cache.getCacheName(),
					Caffeine.newBuilder()
						.expireAfterWrite(cache.getExpiredAfterWrite(), TimeUnit.SECONDS)
						.maximumSize(cache.getMaximumSize())
						.recordStats()
						.build()
				))
				.toList()
		);
		return cacheManager;
	}

	private CacheManager redisCacheManager() {

		Map<String, RedisCacheConfiguration> cacheConfigurationMap = Arrays.stream(RedisCache.values())
			.collect(Collectors.toMap(
				RedisCache::getCacheName,
				redisCache -> {
					GzipRedisSerializer<?> serializer = new GzipRedisSerializer<>(objectMapper,
						redisCache.getTypeRef(), -1, 4096);
					return RedisCacheConfiguration.defaultCacheConfig()
						.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer))
						.disableCachingNullValues()
						.entryTtl(redisCache.getExpiredAfterWrite())
						.prefixCacheNameWith("nemo:");
				}
			));
		return RedisCacheManager.RedisCacheManagerBuilder
			.fromConnectionFactory(redisConnectionFactory)
			.withInitialCacheConfigurations(cacheConfigurationMap)
			.build();
	}
}
