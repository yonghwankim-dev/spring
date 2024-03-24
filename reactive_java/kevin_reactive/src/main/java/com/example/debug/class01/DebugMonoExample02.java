package com.example.debug.class01;

import com.example.utils.Logger;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;

/**
 * onOperatorDebug() Hook 메소드를 이용한 Debug mode
 * - 애플리케이션 전체에서 global하게 동작한다
 */
public class DebugMonoExample02 {
	public static void main(String[] args) {
		Hooks.onOperatorDebug();
		Flux.just(2, 4, 6, 8)
			.zipWith(Flux.just(1, 2, 3, 0), (x, y)-> x/y)
			.subscribe(Logger::onNext, Logger::onError);
	}
}
