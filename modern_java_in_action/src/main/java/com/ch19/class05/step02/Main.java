package com.ch19.class05.step02;

import java.util.function.Function;

public class Main {
	public static void main(String[] args) {
		Function<Integer, Integer> function = repeat(3, (Integer x)-> 2*x);
		Integer result = function.apply(10);
		System.out.println(result); // 80
	}

	private static <A> Function<A, A> repeat(int n, Function<A, A> f) {
		return n == 0 ? x -> x : compose(f, repeat(n-1, f));
	}

	public static <A,B,C> Function<A, C> compose(Function<B, C> g, Function<A, B> f){
		return x -> g.apply(f.apply(x));
	}
}
