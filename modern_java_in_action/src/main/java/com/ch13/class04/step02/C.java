package com.ch13.class04.step02;

public class C extends D implements B, A {
	public static void main(String[] args) {
		new C().hello(); // Hello from B
	}
}
