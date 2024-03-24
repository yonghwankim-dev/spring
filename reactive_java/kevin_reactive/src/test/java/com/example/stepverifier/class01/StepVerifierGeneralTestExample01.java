package com.example.stepverifier.class01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * StepVerifier 기본 사용 예제
 */
public class StepVerifierGeneralTestExample01 {

	@Test
	void sayHelloReactorTest(){
		StepVerifier
			.create(Mono.just("Hello Reactor")) // 테스트 대상 Sequence 생성
			.expectNext("Hello Reactor")	// onNext Signal에 대한 emit된 데이터 검증
			.expectComplete() // onComplete Signal 검증
			.verify();	// 검증 실행
	}
}
