package com.example.mono;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Mono 기본 예제
 * - 원본 데이터의 emit 없이 onComplete siginal만 emit한다
 */
@Slf4j
public class MonoExample02 {
	public static void main(String[] args) {
		Mono.empty()
			.subscribe(data -> log.info("# emitted data : {}", data),
				error -> {},
				()-> log.info("# emitted onComplete signal"));
	}
}
