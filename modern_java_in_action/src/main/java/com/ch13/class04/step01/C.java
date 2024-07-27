package com.ch13.class04.step01;

public class C implements B, A{
	public static void main(String[] args) {
		new C().hello(); // Hello from B
	}
}
