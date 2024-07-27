package com.ch06.class02.step04;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
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
		String shortMenu = menus.stream().map(Dish::getName).collect(Collectors.joining());
		System.out.println(shortMenu);

		shortMenu = menus.stream().map(Dish::getName).collect(Collectors.joining(", "));
		System.out.println(shortMenu);

	}
}
