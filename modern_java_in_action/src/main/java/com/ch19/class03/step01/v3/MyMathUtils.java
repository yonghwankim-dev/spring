package com.ch19.class03.step01.v3;

public class MyMathUtils {

	public static void main(String[] args) {
		LazyList<Integer> numbers = from(2);
		int two = primes(numbers).head();
		int three = primes(numbers).tail().head();
		int five = primes(numbers).tail().tail().head();
		System.out.println(two + " " + three + " " + five); // 2 3 5
	}

	public static LazyList<Integer> from(int n){
		return new LazyList<>(n, () -> from(n + 1));
	}

	public static MyList<Integer> primes(MyList<Integer> numbers){
		return new LazyList<>(
			numbers.head(),
			()->primes(numbers.tail().filter(n-> n% numbers.head() != 0)
			)
		);
	}
}
