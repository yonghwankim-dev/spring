package com.example.sinks;

import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class ProgrammaticCreateExample01 {
	public static void main(String[] args) {
		int tasks = 6;
		Flux
			.create((FluxSink<String> sink)->{
				IntStream
					.range(1, tasks)
					.forEach(n -> sink.next(doTask(n)));
			})
			// .subscribeOn(Schedulers.boundedElastic())
			// .doOnNext(n -> log.info("# create(): {}", n))
			// .publishOn(Schedulers.parallel())
			// .map(result -> result + " success!")
			// .doOnNext(n -> log.info("# map(): {}", n))
			// .publishOn(Schedulers.parallel())
			.subscribe(data -> log.info("# onNext: {}", data));
	}

	private static String doTask(int taskNumber) {
		// now tasking
		// complete to task.
		return "task" + taskNumber + " result";
	}

}
