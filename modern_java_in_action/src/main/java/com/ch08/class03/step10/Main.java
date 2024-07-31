package com.ch08.class03.step10;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
	public static void main(String[] args) {
		test1();
		test2();
	}

	private static void test1() {
		ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
		map.put("a", 1L);
		map.put("b", 2L);
		map.put("c", 3L);
		long parallelismThreshold = 1;
		Optional<Long> maxValue = Optional.ofNullable(map.reduceValues(parallelismThreshold, Long::max));
		System.out.println(maxValue.orElse(0L));
	}

	private static void test2(){
		ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
		map.put("a", 1L);
		map.put("b", 2L);
		map.put("c", 3L);

		map.forEach((k, v)-> System.out.println("key : " + k + ", value : " + v));
		System.out.println();

		long parallelismThreshold = 1;
		Long sum = map.reduce(parallelismThreshold, (key, value) -> value + 1, Long::sum);
		System.out.println("sum is " + sum);

		Long search = map.search(parallelismThreshold, (key, value) -> value + 1);
		System.out.println("search is " + search);

		map.forEachKey(parallelismThreshold, String::toUpperCase, System.out::print);
		System.out.println();

		String reduceKeyResult = map.reduceKeys(parallelismThreshold, String::toUpperCase,
			(key1, key2) -> String.join(",", key1, key2));
		System.out.println("reduceKeyResult is " + reduceKeyResult);

		Long searchValues = map.searchValues(parallelismThreshold, value -> value + 1);
		System.out.println("searchValues is " + searchValues);

		long mappingCount = map.mappingCount();
		System.out.println("mappingCount is " + mappingCount);

		ConcurrentHashMap.KeySetView<String, Long> keySetView = map.keySet();
		System.out.println("keySetView is " + keySetView);
		map.put("d", 4L);
		System.out.println("keySetView is " + keySetView);

		ConcurrentHashMap.KeySetView<String, Boolean> newKeySetView = map.newKeySet();
		newKeySetView.add("a");
		System.out.println("newKeySetView is " + newKeySetView);

	}
}
