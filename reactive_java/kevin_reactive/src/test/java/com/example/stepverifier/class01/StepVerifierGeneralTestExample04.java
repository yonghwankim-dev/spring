package com.example.stepverifier.class01;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * onError signal 발생 여부를 검증
 */
public class StepVerifierGeneralTestExample04 {

	@Test
	void sayHelloReactorTest(){
		Flux<Integer> source = Flux.just(2, 4, 6, 8, 10);
		StepVerifier
			.create(GeneralExample.occurError(source))
			.expectSubscription()
			.expectNext(1)
			.expectNext(2)
			.expectNext(3)
			.expectNext(4)
			.expectError()
			.verify();
	}
}
