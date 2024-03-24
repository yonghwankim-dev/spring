package com.example.backpressure;

import org.reactivestreams.Subscription;

import com.example.utils.Logger;
import com.example.utils.TimeUtils;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

/**
 * Subscriber가 처리 가능한 만큼의 request 개수를 조절하는 Backpressure 예제
 */
@Slf4j
public class BackpressureExample02 {
	private static int count = 0;

	public static void main(String[] args) {
		Flux.range(1, 5)
			.doOnNext(Logger::doOnNext)
			.doOnRequest(Logger::doOnRequest)
			.subscribe(new BaseSubscriber<Integer>() {
				@Override
				protected void hookOnSubscribe(Subscription subscription) {
					request(2);
				}

				@Override
				protected void hookOnNext(Integer value) {
					count++;
					Logger.onNext(value);
					if (count == 2){
						TimeUtils.sleep(2000L);
						request(2);
						count = 0;
					}
				}
			});
	}
}
