package com.example.schedulertype;

import com.example.utils.Logger;
import com.example.utils.TimeUtils;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * Schedulers.newBoundedElastic()을 적용
 */
@Slf4j
public class SchedulersNewBoundedElasticExample01 {
	public static void main(String[] args) {
		// threadCap : 생성할 쓰레드 개수, queuedTaskCap : 각 쓰레드가 가지고 있는 대기큐의 사이즈
		Scheduler scheduler = Schedulers.newBoundedElastic(2, 2, "I/O-Thread");
		Mono<Integer> mono = Mono.just(1)
			.subscribeOn(scheduler);
		log.info("# Start");

		mono.subscribe(data ->{
			log.info("subscribe1 doing {}", data);
			TimeUtils.sleep(3000L);
			log.info("subscribe1 done {}", data);
		});

		mono.subscribe(data ->{
			log.info("subscribe2 doing {}", data);
			TimeUtils.sleep(3000L);
			log.info("subscribe2 done {}", data);
		});

		mono.subscribe(data ->{
			log.info("subscribe3 doing {}", data);
		});

		mono.subscribe(data ->{
			log.info("subscribe4 doing {}", data);
		});

		mono.subscribe(data ->{
			log.info("subscribe5 doing {}", data);
		});

		mono.subscribe(data ->{
			log.info("subscribe6 doing {}", data);
		});

		TimeUtils.sleep(200L);
	}
}
