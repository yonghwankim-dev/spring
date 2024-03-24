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
public class BackpressureExample01 {
	public static void main(String[] args) {
		Flux.range(1, 5)
			.doOnNext(Logger::doOnNext) // Subscriber에게 다음 데이터를 전달하는 경우 실행
			.doOnRequest(Logger::doOnRequest) // Subscriber로부터 Request가 온경우 실행
			.subscribe(new BaseSubscriber<Integer>() {
				@Override
				protected void hookOnSubscribe(Subscription subscription) {
					request(1); // 구독시 데이터 1개 요청
				}

				@Override
				protected void hookOnNext(Integer value) {
					TimeUtils.sleep(2000L); // 2초 대기
					Logger.onNext(value); // 데이터 처리
					request(1); // 다음 데이터 1개 요청
				}
			});
	}
}
