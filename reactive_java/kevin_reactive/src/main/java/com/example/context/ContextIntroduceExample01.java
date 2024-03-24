package com.example.context;

import com.example.utils.Logger;
import com.example.utils.TimeUtils;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Context 개념 설명 예제 코드
 * - contextWrite()로 Context에 값을 쓸수 있고, ContextView.get()을 통해서 Context로부터 값을 읽을 수 있다
 * - ContextView는 deferContextual() 또는 transformDeferredContextual()을 통해 제공된다
 */
public class ContextIntroduceExample01 {
	public static void main(String[] args) {
		String key = "message";
		Mono<String> mono = Mono.deferContextual(
				ctx -> Mono.just("Hello " + ctx.get(key)).doOnNext(Logger::doOnNext))
			.subscribeOn(Schedulers.boundedElastic())
			.publishOn(Schedulers.parallel())
			.transformDeferredContextual((mono2, ctx) -> mono2.map(data -> data + " " + ctx.get(key)))
			.contextWrite(context -> context.put(key, "Reactor"));

		mono.subscribe(Logger::onNext);
		TimeUtils.sleep(200L);
	}
}
