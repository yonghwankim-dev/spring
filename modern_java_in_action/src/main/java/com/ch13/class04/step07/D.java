package com.ch13.class04.step07;

public class D implements B, C {
	@Override
	public void hello() {
		System.out.println("Hello from D");
	}

	public static void main(String[] args) {
		new D().hello(); // Hello from D
	}
}
