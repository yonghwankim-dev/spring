package com.ch17.class01.step03;

import java.util.concurrent.Flow.Publisher;

/**
 * stackoverflow를 해결하기 위해서 TempSubscription에 쓰레드풀을 추가하여 쓰레드풀이 해결하도록 수행하여 해결함
 */
public class Main {
	public static void main(String[] args) {
		Publisher<TempInfo> publisher = subscriber ->
			subscriber.onSubscribe(new TempSubscription(subscriber, "New York"));
		publisher.subscribe(new TempSubscriber());
	}
}
