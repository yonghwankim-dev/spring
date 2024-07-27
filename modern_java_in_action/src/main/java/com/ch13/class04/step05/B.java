package com.ch13.class04.step05;

public interface B {
	default void hello(){
		System.out.println("Hello from B");
	}
}
