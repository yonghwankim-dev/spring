package com.example.stepverifier.class02;

import org.junit.jupiter.api.Test;

import reactor.test.StepVerifier;

/**
 * Backpressure 전략에 따라 Exception이 발생하는 예제
 * - request 데이터 개수보다 많은 데이터가 emit되어 OverflowException 발생
 * - OverflowException이 발생하게 된 데이터는 discord된다
 * - 나머지 emit된 데이터들은 Hooks.onNextDropped()에 의해서 drop된다
 */
public class StepVerifierBackpressureTestExample01 {

	@Test
	void generateNumberTest(){
		StepVerifier
			.create(BackPressureExample.generateNumberByErrorStrategy(), 1L)
			.thenConsumeWhile(num -> num >= 1)
			.verifyComplete();
	}
}
