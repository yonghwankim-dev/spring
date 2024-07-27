package com.ch20.class01.step01;

import static java.util.Map.*;

import java.util.Map;
import java.util.stream.IntStream;

public class Foo {
	public static void main(String[] args) {
		IntStream.rangeClosed(2, 6)
			.forEach(n->System.out.println("Hello " + n + " bottles of beer"));

		Map<String, Integer> authorsToAge =
			Map.ofEntries(entry("Raoul", 23),
				entry("Mario", 40),
				entry("Alan", 53));
		System.out.println(authorsToAge);
	}
}
