package com.ch16.class04.step05;

import java.util.Random;

import lombok.Getter;

@Getter
public class Shop {

	private static final Random random = new Random();
	private final String name;

	public Shop(String name) {
		this.name = name;
	}

	public double getPrice(String product){
		return calculatePrice(product);
	}

	private double calculatePrice(String product) {
		delay(1000L);
		return 1000;
	}

	private static void delay(Long value) {
		try {
			Thread.sleep(value);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private static void doSomethingElse() {
		System.out.println("call doSomethingElse");
	}
}
