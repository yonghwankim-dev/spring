package com.ch06.class02.step05;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
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

		int totalCalories = menus.stream().collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));
		System.out.println(totalCalories);

		Optional<Dish> mostCalorieDish = menus.stream()
			.collect(Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
		System.out.println(mostCalorieDish.get().getName());

		totalCalories = menus.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
		System.out.println(totalCalories);

		int totalDishes = menus.stream().collect(Collectors.reducing(0, o -> 1, Integer::sum));
		System.out.println(totalDishes);

		totalCalories = menus.stream().map(Dish::getCalories).reduce(Integer::sum).get();
		System.out.println(totalCalories);

		totalCalories = menus.stream().mapToInt(Dish::getCalories).sum();
		System.out.println(totalCalories);
		
	}
}
