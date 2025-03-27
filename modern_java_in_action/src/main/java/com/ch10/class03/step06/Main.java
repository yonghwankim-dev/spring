package com.ch10.class03.step06;

public class Main {
	public static void main(String[] args) {
		double value = Tax.calculateTax(new Order(1000.0), true, false, true);
		System.out.println(value);
	}
}
