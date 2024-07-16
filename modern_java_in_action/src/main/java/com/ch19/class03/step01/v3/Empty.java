package com.ch19.class03.step01.v3;

public class Empty<T> implements MyList<T>{
	@Override
	public T head() {
		throw new UnsupportedOperationException();
	}

	@Override
	public MyList<T> tail() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return "Empty{}";
	}
}
