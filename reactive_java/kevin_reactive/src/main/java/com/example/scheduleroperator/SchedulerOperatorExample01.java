package com.example.scheduleroperator;

import com.example.utils.Logger;

import reactor.core.publisher.Flux;

public class SchedulerOperatorExample01 {
	public static void main(String[] args) {
		Flux.fromArray(new Integer[]{1, 3, 5, 7})
			.filter(data -> data > 3)
			.map(data -> data * 10)
			.subscribe(Logger::onNext);
	}
}
