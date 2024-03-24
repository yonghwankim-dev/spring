package com.example.backpressure;

import java.time.Duration;

import com.example.utils.Logger;
import com.example.utils.TimeUtils;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Unbounded request일 경우, Downstream에 Backpressure Buffer DROP_LATEST 전략을 적용하는 예제
 * - Downstream으로 전달할 데이터가 버퍼에 가득찰 경우, 버퍼 안에 있는 데이터 중에서 가장 최근에(나중에) 버퍼로 들어온 데이터부터 Drop 시키는 전략
 */
@Slf4j
public class BackpressureStrategyBufferDropLatestExample {
	public static void main(String[] args) {
		Flux.interval(Duration.ofMillis(300L))
			.doOnNext(data -> Logger.info("# emitted by original Flux : {}", data))
			.onBackpressureBuffer(2,
				dropped -> Logger.info("# Overflow & dropped: {}", dropped),
				BufferOverflowStrategy.DROP_LATEST)
			.doOnNext(data -> Logger.info("# emitted by Buffer: {}", data))
			.publishOn(Schedulers.parallel(), false, 1)
			.subscribe(data ->{
				TimeUtils.sleep(1000L);
				Logger.onNext(data);
			}, Logger::onError);
		TimeUtils.sleep(3000L);
	}
}
