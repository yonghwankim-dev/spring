package com.ch16.class04.step02;

import java.util.Random;

import lombok.Getter;

@Getter
public class Shop {

	private static final Random random = new Random();
	private final String name;

	public Shop(String name) {
		this.name = name;
	}

	public String getPrice(String product){
		double price = calculatePrice(product);
		Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
		return String.format("%s:%.2f:%s", name, price, code);
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

	private static void doSomethingElse() {
		System.out.println("call doSomethingElse");
	}
}
