package com.example.mono;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Mono 기본 예제
 * - 1개의 데이터를 생성해서 emit한다
 */
@Slf4j
public class MonoExample01 {
	public static void main(String[] args) {
		Mono.just("Hello Reactor!")
			.subscribe(data -> log.info("# emitted data : {}", data));
	}
}
