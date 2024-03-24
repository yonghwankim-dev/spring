package com.example.stepverifier.class02;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;

/**
 * 실제 시간을 가상 시간으로 대체하는 테스트 예제
 * - thenAwait(Duration)을 통해 특정 시간만큼 가상으로 기다린다
 * - 즉, 특정 시간을 기다린 것처럼 시간을 당긴다
 */
public class StepVerifierTimeBasedExample02 {
	@Test
	void getCOVID19CountTest(){
		StepVerifier
			.withVirtualTime(()->TimeBasedExample.getCOVID19Count(
				Flux.interval(Duration.ofHours(12)).take(1)
				)
			)
			.expectSubscription()
			.thenAwait(Duration.ofHours(12))
			.expectNextCount(11)
			.expectComplete()
			.verify();
	}
}
