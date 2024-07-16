package com.ch19.class03.step01.v2;

import java.util.stream.IntStream;

public class MyMathUtils {
	public static IntStream numbers(){
		return IntStream.iterate(2, n->n+1);
	}

	public static int head(IntStream numbers){
		return numbers.findFirst().getAsInt();
	}

	public static IntStream tail(IntStream numbers){
		return numbers.skip(1);
	}

	public static IntStream primes(IntStream numbers){
		int head = head(numbers);
		return IntStream.concat(
			IntStream.of(head),
			primes(tail(numbers).filter(n -> n % head != 0))
		);
	}

	public static void main(String[] args) {
		IntStream primes = primes(numbers());
		primes.forEach(p->System.out.print(p + " "));
	}
}
