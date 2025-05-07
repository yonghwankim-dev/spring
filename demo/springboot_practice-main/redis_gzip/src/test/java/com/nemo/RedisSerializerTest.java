package com.nemo;

import java.util.Objects;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

class RedisSerializerTest {
	
	@DisplayName("문자열을 byte 데이터로 직렬화한다")
	@Test
	void stringRedisSerializerTest() {
		// given
		StringRedisSerializer serializer = new StringRedisSerializer();
		// when
		byte[] actual = serializer.serialize("kim");
		// then
		Assertions.assertThat(actual).isNotEmpty();
	}

	@DisplayName("자바 객체를 byte 데이터로 직렬화한다")
	@Test
	void jdkSerializationRedisSerializerTest() {
		// given
		JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();
		// when
		byte[] actual = serializer.serialize(new TestObject("kim", 10));
		// then
		Assertions.assertThat(actual).isNotEmpty();
	}

	@DisplayName("객체를 클래스 정보가 있는 json 데이터로 직렬화한다")
	@Test
	void genericJackson2JsonRedisSerializerTest() {
		// given
		GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();
		// when
		byte[] actual = serializer.serialize(new TestObject("kim", 10));
		// then
		Assertions.assertThat(actual).isNotEmpty();
		System.out.println("Serialized JSON: " + new String(actual));
	}

	@DisplayName("객체를 json 데이터로 직렬화한다")
	@Test
	void jackson2JsonRedisSerializerTest() {
		// given
		Jackson2JsonRedisSerializer<TestObject> serializer = new Jackson2JsonRedisSerializer<>(TestObject.class);
		// when
		byte[] bytes = serializer.serialize(new TestObject("kim", 10));
		// then
		Object deserializeTestObject = serializer.deserialize(bytes);
		Assertions.assertThat(bytes).isNotEmpty();
		System.out.println("Serialized JSON: " + new String(bytes));
		System.out.println("Deserialized Object: " + Objects.requireNonNull(deserializeTestObject).getClass());
	}
}
