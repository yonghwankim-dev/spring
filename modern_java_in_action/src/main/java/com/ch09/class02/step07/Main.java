package com.ch09.class02.step07;

public class Main {
	public static void main(String[] args) {
		Product p = ProductFactory.createProduct("loan");
		if (p instanceof Loan){
			System.out.println("Product is loan");
		}
	}
}
