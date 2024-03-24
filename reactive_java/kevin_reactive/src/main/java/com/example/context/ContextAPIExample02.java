package com.example.context;

import com.example.utils.Logger;
import com.example.utils.TimeUtils;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

/**
 * Context API 예제코드
 * - putAll(ContextView) API 사용
 */
public class ContextAPIExample02 {
	public static void main(String[] args) {
		String key1 = "id";
		String key2 = "name";
		String key3 = "country";

		Mono.deferContextual(
				ctx -> Mono.just(String.format("ID: %s, NAME: %s, COUNTRY : %s", ctx.get(key1), ctx.get(key2), ctx.get(key3)))
			)
			.publishOn(Schedulers.parallel())
			.contextWrite(context-> context.putAll(Context.of(key2, "yonghwan", key3, "korea").readOnly()))
			.contextWrite(context -> context.put(key1, "nemo"))
			.subscribe(Logger::onNext);

		TimeUtils.sleep(200L);
	}
}
