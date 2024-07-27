package com.ch13.class02.step01;

public interface Sized {
	int size();
	default boolean isEmpty(){
		return size() == 0;
	}
}
