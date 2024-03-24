package com.example.hello;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class HelloReactor {
	public static void main(String[] args) {
		Flux<String> sequence = Flux.just("Hello", "Reactor");
		sequence.map(String::toLowerCase)
			.subscribe(log::info);
	}
}
