package com.ch17.class01.step03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscriber;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TempSubscription implements Flow.Subscription {
	private static final ExecutorService executor = Executors.newSingleThreadExecutor();

	private final Subscriber<? super TempInfo> subscriber;
	private final String town;

	@Override
	public void request(long n) {
		executor.submit(() -> { // 다른 스레드에서 다음 요소를 구독자에게 보낸다
			for (long i = 0; i < n; i++) {
				try {
					subscriber.onNext(TempInfo.fetch(town));
				} catch (Exception e) {
					subscriber.onError(e);
					break;
				}
			}
		});
	}

	@Override
	public void cancel() {
		subscriber.onComplete(); // 구독이 취소되면 완료(onComplete) 신호를 Subscriber로 전달
	}
}
