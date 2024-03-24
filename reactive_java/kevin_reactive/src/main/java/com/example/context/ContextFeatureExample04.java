package com.example.context;

import com.example.utils.Logger;
import com.example.utils.TimeUtils;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Context 특징
 * - inner sequence 내부에서는 외부 Context에 저장된 데이터를 읽을 수 있다
 * - inner sequence 내부에서 Context에 저장된 데이터는 inner sequence 외부에서 읽을 수 없다
 */
public class ContextFeatureExample04 {
	public static void main(String[] args) {
		String key1 = "id";

		Mono.just("yonghwan")
				// .transformDeferredContextual((mono, ctx)->ctx.get("job"))
				.flatMap(name -> {
						// inner sequence
						return Mono.deferContextual(ctx->
							Mono.just(String.format("%s, %s", ctx.get(key1), name))
								.transformDeferredContextual((mono, innerCtx)->
									mono.map(data-> String.format("%s, %s", data, innerCtx.get("job"))))
								.contextWrite(context -> context.put("job", "Software Enginner")));
					}
				)
				.publishOn(Schedulers.parallel())
				.contextWrite(context -> context.put(key1, "nemo"))
				.subscribe(Logger::onNext); // null, yonghwan, Software Enginner

		TimeUtils.sleep(200L);
	}
}
