package com.ch08.class03.step07;

import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		Map<String, String> favouriteMovies = new HashMap<>();
		favouriteMovies.put("Ridley Scott", "Gladiator");
		favouriteMovies.put("Steven Spielberg", "Jaws");
		favouriteMovies.put("Quentin Tarantino", "Reservoir Dogs");
		favouriteMovies.replaceAll((friend, movie) -> movie.toUpperCase());
		System.out.println(favouriteMovies);

		favouriteMovies = new HashMap<>();
		favouriteMovies.put("Ridley Scott", "Gladiator");
		favouriteMovies.put("Steven Spielberg", "Jaws");
		favouriteMovies.put("Quentin Tarantino", "Reservoir Dogs");
		boolean replace = favouriteMovies.replace("Ridley Scott", "Gladiator", "Gladiator 2");
		System.out.println(favouriteMovies);
		System.out.println(replace);
	}
}
