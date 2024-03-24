package com.example.scheudler;

import com.example.utils.Logger;
import com.example.utils.TimeUtils;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * subscriberOn()은 구독 직후에 실행 될 쓰레드를 지정합니다.
 * 즉, 원본 Publisher의 실행 쓰레드를 subscribeOn()에서 지정한 쓰레드로 변경한다
 */
public class SchedulerOperatorExample04 {
	public static void main(String[] args) {
		Flux.fromArray(new Integer[]{1, 3, 5, 7})
			.subscribeOn(Schedulers.boundedElastic())
			.doOnNext(data -> Logger.doOnNext("fromArray", data))
			.filter(data -> data > 3)
			.doOnNext(data -> Logger.doOnNext("filter", data))
			.publishOn(Schedulers.parallel())
			.map(data -> data * 10)
			.doOnNext(data -> Logger.doOnNext("map", data))
			.subscribe(Logger::onNext);

		TimeUtils.sleep(500L);
	}
}
