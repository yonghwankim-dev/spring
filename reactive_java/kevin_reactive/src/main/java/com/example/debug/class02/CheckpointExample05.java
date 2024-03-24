package com.example.debug.class02;

import com.example.utils.Logger;

import reactor.core.publisher.Flux;

/**
 * chkecpoint(description) operator를 2개 사용한 예제
 * - 식별자를 추가해서 에러가 발생한 지점을 구분할 수 있다
 * - 식별자를 지정할 경우에 에러가 발생한 assembly 지점의 traceback을 추가하지 않는다
 */
public class CheckpointExample05 {
	public static void main(String[] args) {
		Flux.just(2, 4, 6, 8)
			.zipWith(Flux.just(1, 2, 3, 0), (x,y) -> x/y)
			.checkpoint("Chceckpoint Exception04.zipWith.checkpoint")
			.map(num -> num + 2)
			.checkpoint("Chceckpoint Exception04.map.checkpoint")
			.subscribe(Logger::onNext, Logger::onError);
	}
}
