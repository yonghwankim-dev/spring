package com.example.parallel;

import com.example.utils.Logger;
import com.example.utils.TimeUtils;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * parallel()만 사용할 경우에는 병렬로 작업을 수행하지 않는다
 * runOn()을 사용해서 Scheduler를 할당해주어야 병렬로 작업을 수행한다
 * CPU 코어 개수에 의존하지 않고, workload htread를 강제 할당한다
 *
 */
public class ParallelExample04 {
	public static void main(String[] args) {
		Flux.fromArray(new Integer[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19})
			.parallel(4) // 4개의 쓰레드만 할당한다
			.runOn(Schedulers.parallel())
			.subscribe(Logger::onNext);

		TimeUtils.sleep(100L);
	}
}
