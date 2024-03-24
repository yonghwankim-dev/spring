package com.example.flux;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 여러개의 Flux를 연결해서 하나의 Flux로 결합하는 예제
 */
@Slf4j
public class FluxExample04 {
	public static void main(String[] args) {
		Flux.concat(
			Flux.just("Venus"),
			Flux.just("Earth"),
			Flux.just("Mars"))
			.collectList()
			.subscribe(planetList -> log.info("# Solar System: {}", planetList));
	}
}
