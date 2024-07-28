package com.ch06.class02.step02;

import java.util.Arrays;
import java.util.Comparator;
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
		Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);

		List<Dish> menus = Arrays.asList(new Dish("pizza", 100), new Dish("pasta", 200));
		// maximum calorie dish
		Optional<Dish> mostCalorieDish = menus.stream().collect(Collectors.maxBy(dishCaloriesComparator));
		System.out.println(mostCalorieDish.get().getName());

		// minimum calorie dish
		Optional<Dish> minCalorieDish = menus.stream().collect(Collectors.minBy(dishCaloriesComparator));
		System.out.println(minCalorieDish.get().getName());
	}
}
