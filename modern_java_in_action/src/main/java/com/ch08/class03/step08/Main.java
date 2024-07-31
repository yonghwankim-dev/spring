package com.ch08.class03.step08;

import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		testPutAll();
		testMerge();
	}

	private static void testPutAll() {
		Map<String, String> family = Map.ofEntries(
				Map.entry("tey", "Tey"),
				Map.entry("sanghyun", "Sanghyun"),
				Map.entry("hyunjin", "Hyunjin")
		);
		Map<String, String> friends = Map.ofEntries(
				Map.entry("chulsoo", "Chulsoo"),
				Map.entry("Teo", "Star Wards"),
			Map.entry("hyunjin", "Hyunjin2")
		);
		HashMap<String, String> everyone = new HashMap<>(family);
		// 중복된 키가 존재하면 인수로 전달된 키로 덮어씌워진다
		everyone.putAll(friends);
		System.out.println(everyone);
	}

	private static void testMerge() {
		Map<String, String> family = Map.ofEntries(
			Map.entry("tey", "Tey"),
			Map.entry("sanghyun", "Sanghyun"),
			Map.entry("hyunjin", "Hyunjin")
		);
		Map<String, String> friends = Map.ofEntries(
			Map.entry("chulsoo", "Chulsoo"),
			Map.entry("Teo", "Star Wards"),
			Map.entry("hyunjin", "Hyunjin2")
		);
		HashMap<String, String> everyone = new HashMap<>(family);
		// 중복된 키가 존재하면 값을 연결한다
		friends.forEach((k, v)->everyone.merge(k, v, (movie1, movie2)-> movie1 + "&" + movie2));
		System.out.println(everyone);

	}
}
