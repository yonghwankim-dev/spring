package com.example.sinks;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

/**
 * Sinks.One 예제
 * - 두건의 데이터만 emit하는 예제
 */
@Slf4j
public class SinkOneExample02 {
	public static void main(String[] args) {
		// emit된 데이터 중에서 단 하나의 데이터만 Subscriber에게 전달한. 나머지 데이터는 Drop 처리된다
		Sinks.One<Object> sinkOne = Sinks.one();
		Mono<Object> mono = sinkOne.asMono();

		sinkOne.emitValue("Hello Reactor", Sinks.EmitFailureHandler.FAIL_FAST);
		// Sink.One은 단 한개의 데이터를 emit할 수 있기 때문에 두번째 emit한 데이터는 drop 된다
		sinkOne.emitValue("Hi Reactor", Sinks.EmitFailureHandler.FAIL_FAST);

		mono.subscribe(data -> log.info("Subscriber1 : {}", data));
		mono.subscribe(data -> log.info("Subscriber2 : {}", data));
	}
}
