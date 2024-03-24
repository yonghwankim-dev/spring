package com.spring.temperature.step2;

import java.io.IOException;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import rx.Subscriber;

public class RxSseEmitter extends SseEmitter {
	static final long SSE_SESSION_TIMEOUT = 30 * 60 * 1000L; // 30 minute
	private final Subscriber<Temperature> subscriber;

	public RxSseEmitter(){
		super(SSE_SESSION_TIMEOUT);
		this.subscriber = new Subscriber<Temperature>() {
			@Override
			public void onCompleted() {

			}

			@Override
			public void onError(Throwable e) {

			}

			@Override
			public void onNext(Temperature temperature) {
				try {
					RxSseEmitter.this.send(temperature);
				} catch (IOException e) {
					unsubscribe();
				}
			}
		};

		onCompletion(subscriber::unsubscribe);
		onTimeout(subscriber::unsubscribe);
	}

	public Subscriber<Temperature> getSubscriber() {
		return subscriber;
	}
}
