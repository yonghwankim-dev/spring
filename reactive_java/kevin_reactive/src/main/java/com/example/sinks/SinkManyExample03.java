package com.example.sinks;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * Sinks.Many 예제
 * - reply()를 사용하여 이미 emit된 데이터 중에서 특정 개수의 최신 데이터만 전달하는 예제
 */
@Slf4j
public class SinkManyExample03 {
	public static void main(String[] args) {
		Sinks.Many<Object> replySink = Sinks.many().replay().limit(2);
		Flux<Object> fluxView = replySink.asFlux();

		replySink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
		replySink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);
		replySink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);

		fluxView.subscribe(data -> log.info("Subscriber 1 : {}", data));
		fluxView.subscribe(data -> log.info("Subscriber 2 : {}", data));
	}
}
