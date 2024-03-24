package com.example.sinks;

import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class ProgrammaticSinksExample01 {
	public static void main(String[] args) {
		int tasks = 6;

		Sinks.Many<String> unicastSink = Sinks.many().unicast().onBackpressureBuffer();
		Flux<String> fluxView = unicastSink.asFlux();
		IntStream
			.range(1, tasks)
			.forEach(n -> {

				try {
					new Thread(()->{
						unicastSink.emitNext(doTask(n), Sinks.EmitFailureHandler.FAIL_FAST);
						log.info("# emitted: {}", n);
					}).run();
					Thread.sleep(100L);
				} catch (InterruptedException e) {
				}
			});
		fluxView
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
