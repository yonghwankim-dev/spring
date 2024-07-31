package com.ch08.class03.step05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		example1();
		example2();
	}

	private static void example1() {
		Map<String, List<String>> favouriteMovies = new HashMap<>();
		String friend = "Raphael";
		List<String> movies = favouriteMovies.get(friend);
		if(movies == null) {
			movies = new ArrayList<>();
			favouriteMovies.put(friend, movies);
		}
		movies.add("Star Wars");
		System.out.println(favouriteMovies);
	}

	private static void example2() {
		Map<String, List<String>> favouriteMovies = new HashMap<>();
		String friend = "Raphael";
		favouriteMovies.computeIfAbsent(friend, name->new ArrayList<>()).add("Star Wars");
		System.out.println(favouriteMovies);

		favouriteMovies.computeIfAbsent(friend, name->new ArrayList<>()).add("Star Wars2");
		System.out.println(favouriteMovies);
	}
}
