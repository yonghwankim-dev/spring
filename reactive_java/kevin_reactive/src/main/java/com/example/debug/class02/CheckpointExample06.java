package com.example.debug.class02;

import com.example.utils.Logger;

import reactor.core.publisher.Flux;

/**
 * chkecpoint(description, true/false) operator를 이용한 예제
 * - description도 사용하고, 에러가 발생한 assembly 지점 또는 에러가 전파된 assembly 지점의 traceback도 추가한다
 */
public class CheckpointExample06 {
	public static void main(String[] args) {
		Flux.just(2, 4, 6, 8)
			.zipWith(Flux.just(1, 2, 3, 0), (x,y) -> x/y)
			.checkpoint("Chceckpoint Exception04.zipWith.checkpoint", true)
			.map(num -> num + 2)
			.checkpoint("Chceckpoint Exception04.map.checkpoint", true)
			.subscribe(Logger::onNext, Logger::onError);
	}
}
