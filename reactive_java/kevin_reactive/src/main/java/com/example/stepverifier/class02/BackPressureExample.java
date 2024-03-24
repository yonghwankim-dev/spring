package com.example.stepverifier.class02;

import java.util.logging.Level;

import com.example.utils.Logger;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

public class BackPressureExample {
	public static Flux<Integer> generateNumberByErrorStrategy(){
		return Flux
			.create(emitter ->{
				for (int i = 1; i <= 100; i++){
					emitter.next(i);
				}
				emitter.complete();
			}, FluxSink.OverflowStrategy.ERROR);
	}

	public static Flux<Integer> generateNumberByDropStrategy(){
		return Flux
			.create(emitter ->{
				for (int i = 1; i <= 100; i++){
					emitter.next(i);
				}
				emitter.complete();
			}, FluxSink.OverflowStrategy.DROP);
	}
}
