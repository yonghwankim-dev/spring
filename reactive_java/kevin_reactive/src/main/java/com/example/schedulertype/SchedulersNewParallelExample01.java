package com.example.schedulertype;

import com.example.utils.TimeUtils;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * Schedulers.newParallel()을 적용
 */
@Slf4j
public class SchedulersNewParallelExample01 {
	public static void main(String[] args) {
		Mono<Integer> mono = Mono.just(1)
			.publishOn(Schedulers.newParallel("parallel thread", 4, true));

		mono.subscribe(data ->{
			TimeUtils.sleep(5000L);
			log.info("subscribe1 : {}", data);
		});

		mono.subscribe(data ->{
			TimeUtils.sleep(4000L);
			log.info("subscribe2 : {}", data);
		});

		mono.subscribe(data ->{
			TimeUtils.sleep(3000L);
			log.info("subscribe3 : {}", data);
		});

		mono.subscribe(data ->{
			TimeUtils.sleep(2000L);
			log.info("subscribe4 : {}", data);
		});

		TimeUtils.sleep(6000L);
	}
}
