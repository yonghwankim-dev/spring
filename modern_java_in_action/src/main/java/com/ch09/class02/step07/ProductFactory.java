package com.ch09.class02.step07;


public class ProductFactory {
	public static Product createProduct(String name){
		switch (name){
			case "loan": return new Loan();
			case "stock": return new Stock();
			case "bond": return new Bond();
			default: throw new RuntimeException("No such product " + name);
		}
	}
}
