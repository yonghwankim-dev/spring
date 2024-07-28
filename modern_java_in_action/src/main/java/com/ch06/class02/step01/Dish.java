package com.ch06.class02.step01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Dish {

	private String name;

	public Dish(String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		List<Dish> dishes = Arrays.asList(new Dish("pizza"), new Dish("pasta"));
		// Count the number of menus
		long howManyDishes = dishes.stream().collect(Collectors.counting());
		System.out.println(howManyDishes); // 2
		howManyDishes = dishes.stream().count();
		System.out.println(howManyDishes); // 2
	}
}
