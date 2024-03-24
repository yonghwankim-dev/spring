package com.example.stepverifier.class01;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * expectNext()를 사용하여 emit된 n개의 데이터를 검증하는 예제
 */
public class StepVerifierGeneralTestExample02 {

	@Test
	void sayHelloReactorTest(){
		StepVerifier
			.create(GeneralExample.sayHelloReactor())
			.expectSubscription()
			.expectNext("Hello")
			.expectNext("Reactor")
			.expectComplete()
			.verify();
	}
}
