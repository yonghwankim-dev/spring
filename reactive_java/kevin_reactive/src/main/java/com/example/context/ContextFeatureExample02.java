package com.example.context;

import com.example.utils.Logger;
import com.example.utils.TimeUtils;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Context 특징
 * - Context는 체인의 맨 아래에서부터 위로 전파된다
 * 	- 따라서 operator 체인에서 Context read 읽는 동작이 Context write 동작 밑에 있을 경우에는 write된 값을 read할 수 없
 */
public class ContextFeatureExample02 {
	public static void main(String[] args) {
		String key1 = "id";
		String key2 = "name";

		Mono.deferContextual(ctx-> Mono.just(ctx.get(key1)))
				.publishOn(Schedulers.parallel())
				.contextWrite(context -> context.put(key2, "yonghwan"))
				.transformDeferredContextual((mono, ctx)-> mono.map(data -> String.format("%s, %s", data, ctx.getOrDefault(key2, "Tom"))))
				.contextWrite(context -> context.put(key1, "nemo"))
				.subscribe(Logger::onNext);

		TimeUtils.sleep(200L);
	}
}
