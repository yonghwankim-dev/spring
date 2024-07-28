package com.ch06.class05.step03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>>{
	@Override
	public Supplier<Map<Boolean, List<Integer>>> supplier() {
		return ()-> new HashMap<>() {{
			put(true, new ArrayList<>());
			put(false, new ArrayList<>());
		}};
	}

	@Override
	public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
		return (Map<Boolean, List<Integer>> acc, Integer candidate)->{
			acc.get(isPrime(acc.get(true), candidate)).add(candidate);
		};
	}

	public static boolean isPrime(List<Integer> primes, int candidate){
		int candidateRoot = (int)Math.sqrt(candidate);
		return primes.stream()
			.takeWhile(i -> i <= candidateRoot)
			.noneMatch(i -> candidate % i == 0);
	}


	// 알고리즘 자체가 순차적이어서 컬렉터를 실제 병렬로 사용할 수 없음. UnsupportedOperationException 발생시켜도 상관없음
	@Override
	public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
		return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2)->{
			map1.get(true).addAll(map2.get(true));
			map1.get(false).addAll(map2.get(false));
			return map1;
		};
	}

	@Override
	public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
		return Function.identity();
	}

	@Override
	public Set<Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
	}
}
