package com.ch13.class04.step02;

public interface A {
	default void hello(){
		System.out.println("Hello from A");
	}
}
