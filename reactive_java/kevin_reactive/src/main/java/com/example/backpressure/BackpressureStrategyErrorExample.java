package com.example.backpressure;

import java.sql.Time;
import java.time.Duration;

import com.example.utils.Logger;
import com.example.utils.TimeUtils;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Unbounded request일 경우, Downstream에 Backpressure Error 전략을 적용하는 예제
 * - Downstream으로 전달할 데이터가 버퍼에 가득찰 경우, Exception을 발생 시키는 전략
 */
public class BackpressureStrategyErrorExample {
	public static void main(String[] args) {
		Flux.interval(Duration.ofMillis(1L))
			.onBackpressureError()
			.doOnNext(Logger::doOnNext)
			.publishOn(Schedulers.parallel())
			.subscribe(data ->{
				TimeUtils.sleep(5L);
				Logger.onNext(data);
			}, Logger::onError);
		TimeUtils.sleep(2000L);
	}
}
