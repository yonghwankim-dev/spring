package com.nemo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

class GzipRedisSerializerTest {
	@DisplayName("객체를 gzip으로 압축하여 직렬화한다")
	@Test
	void serialize() {
		// given
		ObjectMapper objectMapper = new ObjectMapper();
		TypeReference<Member> typeRef = new TypeReference<>() {
		};
		RedisSerializer<Member> serializer = new GzipRedisSerializer<>(objectMapper, typeRef);
		Member kim = new Member(1L, "kim", 10);
		// when
		byte[] bytes = serializer.serialize(kim);
		// then
		Member actual = serializer.deserialize(bytes);
		Assertions.assertThat(actual).isEqualTo(kim);
	}
}
