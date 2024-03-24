package com.example.backpressure;

import java.time.Duration;

import com.example.utils.Logger;
import com.example.utils.TimeUtils;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Unbounded request일 경우, Downstream에 Backpressure Latest 전략을 적용하는 예제
 * - Downstream으로 전달할 데이터가 버퍼에 가득찰 경우, 버퍼 밖에서 폐기되지 않고 대기하는 가장 나중에(최근에) emit된 데이터부터 버퍼에 채우는 전략
 */
@Slf4j
public class BackpressureStrategyLatestExample {
	public static void main(String[] args) {
		Flux.interval(Duration.ofMillis(1L))
			.onBackpressureLatest()
			.publishOn(Schedulers.parallel())
			.subscribe(data ->{
				TimeUtils.sleep(5L);
				Logger.onNext(data);
			}, Logger::onError);
		TimeUtils.sleep(2000L);
	}
}
