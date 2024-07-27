package com.ch13.class04.step04;

public interface B extends A {
	default void hello(){
		System.out.println("Hello from B");
	}
}
