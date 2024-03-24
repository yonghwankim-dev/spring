package com.ch2.class04.step03;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Callable을 결과로 반환하기
 * Callable 인터페이스를 구현하고 쓰레드풀(ExecutorService)로 제출하면 Future<T>를 반환값으로 받을 수 있다
 */
public class Main {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		// 익명 클래스
		Future<String> threadName = executorService.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return Thread.currentThread().getName();
			}
		});
		String result = threadName.get();
		System.out.println(result);

		// 람다 표현식
		Future<String> threadName2 = executorService.submit(() -> Thread.currentThread().getName());
		String result2 = threadName2.get();
		System.out.println(result2);
		executorService.shutdown();
	}
}
