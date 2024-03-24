package com.ch17.class01.step01;

import java.util.concurrent.Flow.Publisher;

public class Main {
	public static void main(String[] args) {
		Publisher<TempInfo> publisher = subscriber ->
			subscriber.onSubscribe(new TempSubscription(subscriber, "New York"));
		publisher.subscribe(new TempSubscriber());
	}
}
