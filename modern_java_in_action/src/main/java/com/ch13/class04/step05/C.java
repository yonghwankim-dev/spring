package com.ch13.class04.step05;

public class C implements B, A{
	@Override
	public void hello() {
		B.super.hello();
	}

	public static void main(String[] args) {
		new C().hello(); // Hello from B
	}
}
