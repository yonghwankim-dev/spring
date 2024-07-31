package com.ch08.class03.step03;

import java.util.Map;

public class Main {
	public static void main(String[] args) {
		Map<String, String> favouriteMovies = Map.ofEntries(
			Map.entry("Raphael", "Star Wars"),
			Map.entry("Cristina", "Matrix"),
			Map.entry("Olivia", "James Bond")
		);
		System.out.println(favouriteMovies.getOrDefault("Olivia", "Matrix")); // James Bond
		System.out.println(favouriteMovies.getOrDefault("Thibaut", "Matrix")); // Matrix
	}
}
