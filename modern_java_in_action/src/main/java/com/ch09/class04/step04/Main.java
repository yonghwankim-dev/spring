package com.ch09.class04.step04;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(2, 3, 4, 5);
		List<Integer> result = numbers.stream()
			.peek(n -> System.out.println("from stream: " + n))
			.map(n -> n + 17)
			.peek(n -> System.out.println("after map: " + n))
			.filter(n -> n % 2 == 0)
			.peek(n -> System.out.println("after filter: " + n))
			.limit(3)
			.peek(n -> System.out.println("after limit: " + n))
			.collect(Collectors.toList());
		System.out.println(result);
	}
}
