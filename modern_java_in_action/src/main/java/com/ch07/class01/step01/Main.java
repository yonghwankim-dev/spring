package com.ch07.class01.step01;

import java.util.stream.Stream;

public class Main {
	public long iterativeSum(long n){
		long result = 0;
		for (long i = 1L; i <= n; i++){
			result += i;
		}
		return result;
	}

	public long sequentialSum(long n){
		return Stream.iterate(1L, i->i+1)
			.limit(n)
			.reduce(0L, Long::sum);
	}

	public long parallelSum(long n){
		return Stream.iterate(1L, i->i+1)
			.limit(n)
			.parallel()
			.reduce(0L, Long::sum);
	}

	public static void main(String[] args) {
		System.out.println(new Main().iterativeSum(100));
		System.out.println(new Main().sequentialSum(100));
		System.out.println(new Main().parallelSum(100));
	}
}
