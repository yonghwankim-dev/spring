package com.example.schedulertype;

import com.example.utils.Logger;
import com.example.utils.TimeUtils;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Schedulers.immeidate() 적용후
 * 현재 쓰레드가 할당된다
 */
public class SchedulersImmediateExample02 {
	public static void main(String[] args) {
		Flux.fromArray(new Integer[]{1, 3, 5, 7})
			.publishOn(Schedulers.parallel())
			.filter(data -> data > 3)
			.doOnNext(data -> Logger.doOnNext("filter", data))
			.publishOn(Schedulers.immediate())
			.map(data -> data * 10)
			.doOnNext(data -> Logger.doOnNext("map", data))
			.subscribe(Logger::onNext);

		TimeUtils.sleep(200L);
	}
}
