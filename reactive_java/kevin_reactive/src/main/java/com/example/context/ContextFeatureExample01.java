package com.example.context;

import com.example.utils.Logger;
import com.example.utils.TimeUtils;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

/**
 * Context 특징
 * - Context는 각각의 구독을 통해서 Reactor Sequence에 연결되며, 체인의 각 연산자가 연결된 Context에 접근할 수 있다.
 */
public class ContextFeatureExample01 {
	public static void main(String[] args) {
		String key1 = "id";

		Mono<String> mono = Mono.deferContextual(
				ctx -> Mono.just(String.format("ID: %s", (String)ctx.get(key1)))
			)
			.publishOn(Schedulers.parallel());

		mono.contextWrite(context -> context.put(key1, "nemo"))
			.subscribe(data -> Logger.onNext("Subscriber 1 : " + data));

		mono.contextWrite(context -> context.put(key1, "nemo2"))
			.subscribe(data -> Logger.onNext("Subscriber 2 : " + data));

		TimeUtils.sleep(200L);
	}
}
