package com.ch13.class04.step07;

public interface A {
	default void hello(){
		System.out.println("Hello from A");
	}
}
