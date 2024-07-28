package com.ch06.class05.step03;

import java.util.stream.Collectors;

public class CollectorHarness {
	public static void main(String[] args) {
		test1();
		test2();
	}

	private static void test1() {
		long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			Prime.partitionPrimes(10_000_000);
			long duration = (System.nanoTime() - start) / 1_000_000;
			if (duration < fastest) fastest = duration;
		}
		// Fastest execution done in 4170 msecs
		System.out.println("Fastest execution done in " + fastest + " msecs");
	}

	private static void test2() {
		long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			Prime.partitionPrimesWithCustomCollector(10_000_000);
			long duration = (System.nanoTime() - start) / 1_000_000;
			if (duration < fastest) fastest = duration;
		}
		// Fastest execution done in 1907 msecs
		System.out.println("Fastest execution done in " + fastest + " msecs");
	}
}
