package com.ch16.class02.step01;

import java.util.Random;

public class Shop {

	private static final Random random = new Random();

	public double getPrice(String product){
		return calculatePrice(product);
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
		double price = shop.getPrice("book");
		System.out.printf("%.2f원%n", price);
	}
}
