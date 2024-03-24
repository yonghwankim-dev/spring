package com.example.sinks;

import com.example.utils.TimeUtils;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * Sinks.Many 예제
 * - reply().all()를 사용하여 구독 시점과 상관없이 emit된 모든 데이터를 reply합니다.
 */
@Slf4j
public class SinkManyExample05 {
	public static void main(String[] args) {
		Sinks.Many<Object> replySink = Sinks.many().replay().all();
		Flux<Object> fluxView = replySink.asFlux();

		replySink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
		replySink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);
		replySink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);

		fluxView.subscribe(data -> log.info("Subscriber 1 : {}", data));
		fluxView.subscribe(data -> log.info("Subscriber 2 : {}", data));

		TimeUtils.sleep(1000L);
	}
}
