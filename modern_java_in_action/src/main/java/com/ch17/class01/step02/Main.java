package com.ch17.class01.step02;

import java.util.concurrent.Flow.Publisher;

/**
 * stackoverflow 발생
 * 발생 원인 : TempSubscriber가 계속 1개씩 TempSubscription에 요청을 보내게 되면서 요청이 많이 쌓이게 되면서 stackoverflow가 발생한다
 */
public class Main {
	public static void main(String[] args) {
		Publisher<TempInfo> publisher = subscriber ->
			subscriber.onSubscribe(new TempSubscription(subscriber, "New York"));
		publisher.subscribe(new TempSubscriber());
	}
}
