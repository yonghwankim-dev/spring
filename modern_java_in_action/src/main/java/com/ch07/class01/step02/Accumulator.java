package com.ch07.class01.step02;

import java.util.stream.LongStream;

public class Accumulator {
	public long total = 0;

	public void add(long value) {
		total += value;
	}

	public static void main(String[] args) {
		int n = 100_000_000;
		Accumulator accumulator = new Accumulator();
		LongStream.rangeClosed(1, n).forEach(accumulator::add);
		System.out.println(accumulator.total);
	}
}
