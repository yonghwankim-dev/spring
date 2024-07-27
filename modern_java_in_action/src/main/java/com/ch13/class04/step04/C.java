package com.ch13.class04.step04;

public class C extends D implements B, A {
	@Override
	public void hello() {
		System.out.println("Hello from C");
	}

	public static void main(String[] args) {
		new C().hello(); // Hello from C
	}
}
