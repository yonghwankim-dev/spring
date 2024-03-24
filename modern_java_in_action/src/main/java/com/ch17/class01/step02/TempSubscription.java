package com.ch17.class01.step02;

import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscriber;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TempSubscription implements Flow.Subscription {

	private final Subscriber<? super TempInfo> subscriber;
	private final String town;

	@Override
	public void request(long n) {
		for (long i = 0; i < n; i++) {
			try {
				subscriber.onNext(TempInfo.fetch(town)); // 현재 온도를 Subscriber로 전달
			} catch (Exception e) {
				subscriber.onError(e); // 온도 가져오기를 실패하면 Subscriber로 에러를 전달
				break;
			}
		}
	}

	@Override
	public void cancel() {
		subscriber.onComplete(); // 구독이 취소되면 완료(onComplete) 신호를 Subscriber로 전달
	}
}
