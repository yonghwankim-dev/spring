package com.example.stepverifier.class03;

import org.junit.jupiter.api.Test;

import com.example.stepverifier.class01.GeneralExample;

import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

/**
 * TestPublisher를 사용해서 서비스 로직의 메소드에 대한 Unit Test를 실시하는 예제
 * - Reactive Streams 사용을 위반해도 Publisher가 정상 동작하게 함으로써 서비스 로직을 검증하는 예제
 */
public class TestPublisherTestExample04 {

	@Test
	void divideByTwoTest(){
		// TestPublisher<Integer> source = TestPublisher.createNoncompliant(TestPublisher.Violation.ALLOW_NULL);
		TestPublisher<Integer> source = TestPublisher.create();

		StepVerifier
			.create(GeneralExample.divideByTwo(source.flux()))
			.expectSubscription()
			.then(()-> source.emit(2, 4, 6, 8, null))
			.expectNext(1)
			.expectNext(2)
			.expectNext(3)
			.expectNext(4)
			.expectComplete()
			.verify();
	}
}
