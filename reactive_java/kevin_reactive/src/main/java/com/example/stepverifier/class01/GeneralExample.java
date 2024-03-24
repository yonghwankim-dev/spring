package com.example.stepverifier.class01;

import org.reactivestreams.Publisher;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class GeneralExample {
	public static Flux<String> sayHelloReactor(){
		return Flux.fromArray(new String[]{"Hello", "Reactor"});
	}

	public static Flux<Integer> occurError(Flux<Integer> source) {
		return source.zipWith(Flux.just(2, 2, 2, 2, 0), (x, y) -> x/y);
	}

	public static Flux<Integer> divideByTwo(Flux<Integer> source) {
		return source.zipWith(Flux.just(2, 2, 2, 2, 2), (x, y) -> x/y);
	}

	public static Flux<Integer> takeNumber(Flux<Integer> source, long n) {
		return source.take(n);
	}
}
