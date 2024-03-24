package com.example.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Logger {
	public static void doOnNext(long value){
		log.info("doOnNext {}", value);
	}

	public static void doOnNext(String value) {
		log.info("doOnNext {}", value);
	}

	public static void doOnNext(String message, long value){
		log.info("doOnNext message={}, value={}", message, value);
	}

	public static void doOnRequest(long value){
		log.info("doOnRequest {}", value);
	}

	public static void onNext(long value){
		log.info("onNext {}", value);
	}

	public static void onNext(String data) {
		log.info("onNext {}", data);
	}

	public static void onError(Throwable error) {
		log.error("onError", error);
	}

	public static void info(String format, Object ... data) {
		log.info(format, data);
	}
}
