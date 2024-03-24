package com.example.context;

import com.example.utils.Logger;
import com.example.utils.TimeUtils;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Context 특징
 * - 동일한 키에 대하여 write하는 경우 값을 덮어쓴다
 */
public class ContextFeatureExample03 {
	public static void main(String[] args) {
		String key1 = "id";

		Mono.deferContextual(ctx->Mono.just(String.format("ID: %s", (String)ctx.get(key1))))
			.publishOn(Schedulers.parallel())
			.contextWrite(context -> context.put(key1, "nemo")) // 마지막에 저장한 nemo가 저장됨
			.contextWrite(context -> context.put(key1, "nemo2"))
			.subscribe(Logger::onNext);

		TimeUtils.sleep(200L);
	}
}
