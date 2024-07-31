package com.ch08.class03.step06;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main {
	public static void main(String[] args) {
		System.out.println(testRemove());
		testRemove2();
	}

	private static boolean testRemove() {
		Map<String, String> favouriteMovies = new HashMap<>();
		favouriteMovies.putIfAbsent("Raphael", "Jack Reacher 2");

		String key = "Raphael";
		String value = "Jack Reacher 2";
		if (favouriteMovies.containsKey(key) && Objects.equals(favouriteMovies.get(key), value)){
			favouriteMovies.remove(key);
			return true;
		}
		return false;
	}

	private static void testRemove2() {
		Map<String, String> favouriteMovies = new HashMap<>();
		favouriteMovies.putIfAbsent("Raphael", "Jack Reacher 2");

		favouriteMovies.remove("Raphael", "Jack Reacher 2");
		System.out.println(favouriteMovies);
	}
}
