package com.example.stepverifier.class02;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * emit되는 모든 데이터들을 캡처하여 컬렉션에 기록한 후, 기록된 데이터들을 검증하는 예제
 * - recordWith()을 사용하여 emit된 데이터들을 기록하는 세션을 시작한다
 * - thenComsumeWhile()을 사용하여 조건에 맞는 데이터만 소비한다. 여기서 조건에 맞는 데이터들이 ArrayList에 추가(기록)된다
 * - consumeRecordedWith()을 사용하여 기록된 데이터들을 소비한다. 여기서는 assertThat()을 사용하여 검증한다
 */
public class StepVerifierRecordTestExample02 {

	@Test
	void getCountryTest(){
		StepVerifier
			.create(RecordExample.getCountry(Flux.just("france", "russia", "greece", "paland")))
			.expectSubscription()
			.recordWith(ArrayList::new)
			.thenConsumeWhile(country -> !country.isEmpty())
			.expectRecordedMatches(countries ->
				countries
					.stream()
					.allMatch(country -> Character.isUpperCase(country.charAt(0)))
			)
			.expectComplete()
			.verify();
	}
}
