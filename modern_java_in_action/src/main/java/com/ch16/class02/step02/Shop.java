package com.ch16.class02.step02;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Shop {

	private static final Random random = new Random();

	public Future<Double> getPriceAsync(String product){
		CompletableFuture<Double> future = new CompletableFuture<>();
		new Thread(() -> {
			double price = calculatePrice(product);
			future.complete(price);
		}).start();
		return future;
	}

	private double calculatePrice(String product) {
		delay();
		return random.nextDouble() * product.charAt(0) + product.charAt(1);
	}

	private static void delay() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		Shop shop = new Shop();
		long start = System.nanoTime();
		Future<Double> future = shop.getPriceAsync("book");
		long invocationTime = ((System.nanoTime() - start) / 1_000_000);
		System.out.println("Invocation returned after " + invocationTime + " msecs");

		doSomethingElse();

		try {
			Double price = future.get();
			System.out.printf("Price is %.2f%n", price);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
		System.out.println("Price returned after " + retrievalTime + " msecs");
	}

	private static void doSomethingElse() {
		System.out.println("call doSomethingElse");
	}
}
