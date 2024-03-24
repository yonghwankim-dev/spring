package com.ch16.class01.step01;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		long start = System.currentTimeMillis();
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<Double> future = executor.submit(new Callable<Double>() {
			@Override
			public Double call() throws Exception {
				return doSomeLongComputation();
			}
		});
		doSomethingElse();

		try {
			Double result = future.get(5, TimeUnit.SECONDS);
			System.out.println(result);
			long end = System.currentTimeMillis();
			System.out.println("소요 시간 : " + (end - start) / 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		} catch (TimeoutException e) {
			throw new RuntimeException(e);
		}
	}

	private static void doSomethingElse() throws InterruptedException {
		Thread.sleep(1000L);
		System.out.println("hello world");
	}

	private static Double doSomeLongComputation() throws InterruptedException {
		Thread.sleep(1000L);
		return new Random(100).nextDouble() * 100;
	}

}
