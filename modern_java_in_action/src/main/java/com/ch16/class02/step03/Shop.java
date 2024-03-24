package com.ch16.class02.step03;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {

	private static final Random random = new Random();

	public Future<Double> getPriceAsync(String product){
		CompletableFuture<Double> future = new CompletableFuture<>();
		new Thread(() -> {
			try{
				double price = calculatePrice(product);
				future.complete(price);
			}catch (Exception e){
				// 도중에 문제가 발생하면 발생항 에러를 포함시켜서 Future를 종료한다. (클라이언트에게 전달)
				future.completeExceptionally(e);
			}
		}).start();
		return future;
	}

	private double calculatePrice(String product) {
		delay();
		throw new RuntimeException("product not available");
		// return random.nextDouble() * product.charAt(0) + product.charAt(1);
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
