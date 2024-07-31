package com.ch08.class03.step02;

import java.util.Map;

public class Main {
	public static void main(String[] args) {
		Map<String, String> favouriteMovies = Map.ofEntries(
			Map.entry("Raphael", "Star Wars"),
			Map.entry("Cristina", "Matrix"),
			Map.entry("Olivia", "James Bond")
		);

		favouriteMovies
			.entrySet()
			.stream()
			.sorted(Map.Entry.comparingByKey())
			.forEachOrdered(System.out::println); // 사람 이름을 알파벳 순으로 스트림 요소를 처리
		
	}
}
