package com.nemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration(proxyBeanMethods = false)
public class CacheConfiguration {

	private final RedisConnectionFactory redisConnectionFactory;
	private final ObjectMapper objectMapper;

	public CacheConfiguration(RedisConnectionFactory redisConnectionFactory, ObjectMapper objectMapper) {
		this.redisConnectionFactory = redisConnectionFactory;
		this.objectMapper = objectMapper;
	}

	@Bean
	public RedisTemplate<String, Member> memberRedisTemplate() {
		return createGzipJsonRedisTemplate(objectMapper, new TypeReference<>() {
		});
	}

	private <V> RedisTemplate<String, V> createGzipJsonRedisTemplate(
		ObjectMapper objectMapper,
		TypeReference<V> typeReference
	) {
		RedisTemplate<String, V> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new GzipRedisSerializer<>(objectMapper, typeReference));
		return redisTemplate;
	}
}
