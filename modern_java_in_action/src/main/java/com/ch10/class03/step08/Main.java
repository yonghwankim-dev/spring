package com.ch10.class03.step08;

public class Main {
	public static void main(String[] args) {
		double value = new TaxCalculator().with(Tax::regional)
			.with(Tax::surcharge)
			.calculate(new Order(1000));
		System.out.println(value);
	}
}
