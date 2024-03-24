package com.spring.message;

public interface Observer<T> {
	void observe(T event);
}
