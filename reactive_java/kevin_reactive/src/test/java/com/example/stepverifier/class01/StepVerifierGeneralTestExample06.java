package com.example.stepverifier.class01;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

/**
 * onNext signal을 통해 emit된 데이터의 개수를 검증하는 예제
 * - 검증에 실패할 경우에는 StepVerifierOptions에서 지정한 Seenario Name이 표시됩니다.
 */
public class StepVerifierGeneralTestExample06 {

	@Test
	void rangeNumberTest(){
		Flux<Integer> source = Flux.range(0, 1000);
		StepVerifier
			.create(GeneralExample.takeNumber(source, 500),
				StepVerifierOptions.create().scenarioName("verify from 0 to 499"))
			.expectSubscription()
			.expectNext(0)
			.expectNextCount(498)
			.expectNext(500)
			.expectComplete()
			.verify();
	}
}
