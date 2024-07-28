package com.ch06.class05.step02;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Prime {

	public Map<Boolean, List<Integer>> partitionPrimes(int n) {
		return IntStream.rangeClosed(2, n).boxed()
			.collect(Collectors.partitioningBy(this::isPrime));
	}

	public boolean isPrime(int candidate){
		int candidateRoot = (int)Math.sqrt(candidate);
		return IntStream.rangeClosed(2, candidateRoot)
			.noneMatch(i->candidate%i==0);
	}

	public static void main(String[] args) {
		Prime prime = new Prime();
		Map<Boolean, List<Integer>> partitionPrimes = prime.partitionPrimes(10);
		System.out.println(partitionPrimes.get(true));
		System.out.println(partitionPrimes.get(false));

	}
}
