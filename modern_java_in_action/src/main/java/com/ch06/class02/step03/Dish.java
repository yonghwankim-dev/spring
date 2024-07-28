package com.ch06.class02.step03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Dish {

	private String name;
	private int calories;

	public Dish(String name, int calories) {
		this.name = name;
		this.calories = calories;
	}

	public String getName() {
		return name;
	}

	public int getCalories() {
		return calories;
	}

	public static void main(String[] args) {
		List<Dish> menus = Arrays.asList(new Dish("pizza", 100), new Dish("pasta", 200));
		// Menu Calories Total
		int totalCalories = menus.stream().collect(Collectors.summingInt(Dish::getCalories));
		System.out.println(totalCalories);

		// Menu Calories Average
		double avgCalories = menus.stream().collect(Collectors.averagingDouble(Dish::getCalories));
		System.out.println(avgCalories);

		// Menu Calories Statistics(count, sum, min, average, max)
		IntSummaryStatistics menuStatistics = menus.stream().collect(Collectors.summarizingInt(Dish::getCalories));
		System.out.println(menuStatistics);
	}
}
