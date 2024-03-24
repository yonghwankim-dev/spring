package com.ch17.class01.step04;

import java.util.Observable;
import java.util.concurrent.Flow.Publisher;

/**
 * TempProcessor를 구현하여 화씨를 섭씨로 변환하여 출력하도록 함
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		// 뉴욕의 섭씨 온도를 전송할 Publisher를 만듬
		getCelsiusTemperatures("New York")
			.subscribe(new TempSubscriber()); // TempSubscriber를 Publisher로 구독
		// Error가 일부로 발생할때까지 2초간 대기하고 프로그램을 종료함
		Thread.currentThread().join(2000L);

	}

	public static Publisher<TempInfo> getCelsiusTemperatures(String town){
		return subscriber -> {
			// Tempprocessor를 만들고 Subscriber와 반환된 Publisher 사이로 연결
			TempProcessor processor = new TempProcessor();
			processor.subscribe(subscriber);
			processor.onSubscribe(new TempSubscription(processor, town));
		};
	}
}
