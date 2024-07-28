package com.ch06.class05.step03;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Prime {

	public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
		return IntStream.rangeClosed(2, n).boxed()
			.collect(new PrimeNumbersCollector());
	}

	public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
		return IntStream.rangeClosed(2, n).boxed()
			.collect(Collectors.partitioningBy(Prime::isPrime));
	}

	private static boolean isPrime(int candidate){
		int candidateRoot = (int)Math.sqrt(candidate);
		return IntStream.rangeClosed(2, candidateRoot)
			.noneMatch(i->candidate%i==0);
	}
}
