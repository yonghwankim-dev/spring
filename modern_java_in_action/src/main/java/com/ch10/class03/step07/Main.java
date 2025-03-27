package com.ch10.class03.step07;

public class Main {
	public static void main(String[] args) {
		double value = new TaxCalculator().withTaxRegional()
			.withTaxSurcharge()
			.calculate(new Order(1000));
		System.out.println(value);
	}
}
