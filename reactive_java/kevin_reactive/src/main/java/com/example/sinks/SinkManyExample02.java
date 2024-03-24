package com.example.sinks;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * Sinks.Many 예제
 * - multicast()를 사용해서 여러 Subscriber에게 데이터를 emit하는 예
 */
@Slf4j
public class SinkManyExample02 {
	public static void main(String[] args) {
		Sinks.Many<Object> multicastSink = Sinks.many().multicast().onBackpressureBuffer();
		Flux<Object> fluxView = multicastSink.asFlux();

		multicastSink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
		multicastSink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);

		fluxView.subscribe(data -> log.info("Subscriber 1 : {}", data));
		fluxView.subscribe(data -> log.info("Subscriber 2 : {}", data));

		multicastSink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);
	}
}
