package com.other.step01;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import com.other.ToArrayExample;

@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 1, jvmArgs = {"-Xms4G", "-Xmx4G"})
@Warmup(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 2, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
public class ToArrayBenchmark {

	@Benchmark
	public int[] testListToArray(){
		List<Integer> list = IntStream.rangeClosed(0, 100_000_000)
			.boxed()
			.collect(Collectors.toList());
		return ToArrayExample.testListToArray(list);
	}

	@Benchmark
	public int[] testListToArrayUsingForLoop(){
		List<Integer> list = IntStream.rangeClosed(0, 100000000)
			.boxed()
			.collect(Collectors.toList());
		return ToArrayExample.testListToArrayUsingForLoop(list);
	}
}
