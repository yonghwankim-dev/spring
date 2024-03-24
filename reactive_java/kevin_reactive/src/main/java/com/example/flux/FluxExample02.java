package com.example.flux;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * Flux 에서의 operator 사용 체인 예제
 */
@Slf4j
public class FluxExample02 {
	public static void main(String[] args) {
		Flux.fromArray(new Integer[]{3, 6, 7, 9})
			.filter(num -> num > 6)
			.map(num -> num * 2)
			.subscribe(multiply -> log.info("# multiply : {}", multiply));
	}
}
