package com.ch08.class03.step09;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		test1();
		test2();
		test3();
		test4();
	}

	private static void test1() {
		Map<String, Long> moviesToCount = new HashMap<>();
		String movieName = "James Bond";
		Long count = moviesToCount.get(movieName);
		if (count == null){
			moviesToCount.put(movieName, 1L);
		}else{
			moviesToCount.put(movieName, count + 1L);
		}
		System.out.println(moviesToCount);
	}

	private static void test2() {
		Map<String, Long> moviesToCount = new HashMap<>();
		String movieName = "James Bond";
		moviesToCount.merge(movieName, 1L, (count, increment)->count + 1L);
		System.out.println(moviesToCount);
	}

	private static void test3() {
		Map<String, Integer> movies = new HashMap<>();
		movies.put("JamesBond", 20);
		movies.put("Matrix", 15);
		movies.put("Harry Potter", 5);
		Iterator<Map.Entry<String, Integer>> iterator = movies.entrySet().iterator();
		while (iterator.hasNext()){
			Map.Entry<String, Integer> entry = iterator.next();
			if (entry.getValue() < 10){
				iterator.remove();
			}
		}
		System.out.println(movies);
	}

	private static void test4() {
		Map<String, Integer> movies = new HashMap<>();
		movies.put("JamesBond", 20);
		movies.put("Matrix", 15);
		movies.put("Harry Potter", 5);
		movies.entrySet().removeIf(entry -> entry.getValue() < 10);
		System.out.println(movies);
	}
}
