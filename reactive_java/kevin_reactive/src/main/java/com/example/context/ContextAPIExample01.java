package com.example.context;

import com.example.utils.Logger;
import com.example.utils.TimeUtils;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

/**
 * Context API중에서 write API 예제 코드
 * - Context.of(...) 사
 */
public class ContextAPIExample01 {
	public static void main(String[] args) {
		String key1 = "id";
		String key2 = "name";

		Mono<String> mono = Mono.deferContextual(
				ctx -> Mono.just(String.format("ID: %s, NAME: %s", ctx.get(key1), ctx.get(key2)))
			)
			.publishOn(Schedulers.parallel())
			.contextWrite(Context.of(key1, "nemo", key2, "yonghwan"));

		mono.subscribe(Logger::onNext);
		TimeUtils.sleep(200L);
	}
}
