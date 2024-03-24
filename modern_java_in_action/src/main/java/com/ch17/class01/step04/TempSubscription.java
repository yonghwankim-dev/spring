package com.ch17.class01.step04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.ThreadFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TempSubscription implements Flow.Subscription {

	// Thread.setDeamon을 설정한 이유는 메인 스레드가 종료될때 같이 종료되도록 하기 위해서
	// setDeamon(false)로 설정하면 메인 스레드 종료시 해당 쓰레드풀은 종료되지 않고 계속 대기하기 때문에 메인 프로그램이 종료되지 않음
	private static final ExecutorService executor = Executors.newSingleThreadExecutor(r -> {
		Thread t = new Thread(r);
		t.setDaemon(true);
		return t;
	});

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
