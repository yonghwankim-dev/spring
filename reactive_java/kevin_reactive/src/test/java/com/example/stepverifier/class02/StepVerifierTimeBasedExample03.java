package com.example.stepverifier.class02;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * 검증에 소요되는 시간을 제한하는 예제
 * - verify(Duration)을 통해 설정한 시간내에 검증이 끝나는 확인할 수 있다.
 */
public class StepVerifierTimeBasedExample03 {
	@Test
	void getCOVID19CountTest(){
		StepVerifier
			.withVirtualTime(()->TimeBasedExample.getCOVID19Count(
				Flux.interval(Duration.ofMinutes(1)).take(1)
				)
			)
			.expectSubscription()
			.expectNextCount(11)
			.expectComplete()
			.verify(Duration.ofSeconds(3));
	}
}
