package com.ch19.class03.step01.v1;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MyMathUtils {

	public static Stream<Integer> primes(int n){
		return Stream.iterate(2, i->i+1)
			.filter(MyMathUtils::isPrime)
			.limit(n);
	}

	public static boolean isPrime(int candidate){
		int candidateRoot = (int)Math.sqrt(candidate);
		return IntStream.rangeClosed(2, candidateRoot)
			.noneMatch(i->candidate % i == 0);
	}

	public static void main(String[] args) {
		Stream<Integer> primes = MyMathUtils.primes(10);
		primes.forEach(p->System.out.printf("%d ", p)); // 2 3 5 7 11 13 17 19 23 29
	}
}
