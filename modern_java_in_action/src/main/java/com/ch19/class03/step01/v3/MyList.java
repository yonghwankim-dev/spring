package com.ch19.class03.step01.v3;

import java.util.function.Predicate;

public interface MyList<T> {
	T head();

	MyList<T> tail();

	default MyList<T> filter(Predicate<T> p){
		return this;
	}

	default boolean isEmpty(){
		return true;
	}
}
