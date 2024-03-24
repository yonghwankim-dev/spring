package com.example.context;

import com.example.utils.Logger;
import com.example.utils.TimeUtils;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

/**
 * ContextView  API 예제코드
 * getOrDefault(key, defaultValue) : key 값에 따른 value값이 없다면 defaultValue를 가져옵니다.
 */
public class ContextAPIExample03 {
	public static void main(String[] args) {
		String key1 = "id";
		String key2 = "name";

		Mono.deferContextual(
				ctx -> Mono.just(String.format("ID: %s, NAME: %s, JOB : %s", ctx.get(key1), ctx.get(key2), ctx.getOrDefault("job", "Software Engineer")))
			)
			.publishOn(Schedulers.parallel())
			.contextWrite(Context.of(key1, "nemo", key2, "yonghwan"))
			.subscribe(Logger::onNext);

		TimeUtils.sleep(200L);
	}
}
