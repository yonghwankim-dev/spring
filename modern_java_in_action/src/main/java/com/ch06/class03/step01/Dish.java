package com.ch06.class03.step01;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Dish {

	enum Type {
		MEAT, FISH, OTHER
	}

	enum CaloricLevel {
		DIET, NORMAL, FAT
	}

	private String name;
	private int calories;
	private Type type;

	public Dish(String name, int calories, Type type) {
		this.name = name;
		this.calories = calories;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public int getCalories() {
		return calories;
	}

	public Type getType() {
		return type;
	}

	@Override
	public String toString() {
		return name;
	}

	public static void main(String[] args) {
		List<Dish> menus = Arrays.asList(
			new Dish("prawns", 50, Type.FISH),
			new Dish("salmon", 100, Type.FISH),
			new Dish("pork", 200, Type.MEAT),
			new Dish("beef", 250, Type.MEAT),
			new Dish("chicken", 275, Type.MEAT),
			new Dish("pizza", 300, Type.OTHER),
			new Dish("rice", 350, Type.OTHER),
			new Dish("french fries", 400, Type.OTHER),
			new Dish("hambugger", 500, Type.MEAT),
			new Dish("cookie", 750, Type.OTHER)
		);

		Map<Type, List<Dish>> dishesByType = menus.stream().collect(Collectors.groupingBy(Dish::getType));
		dishesByType.entrySet()
				.forEach(entry->System.out.println("type=" + entry.getKey() + ", dish=" + entry.getValue()));
		System.out.println();

		Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menus.stream().collect(
			Collectors.groupingBy(dish -> {
					if (dish.getCalories() <= 400)
						return CaloricLevel.DIET;
					else if (dish.getCalories() <= 700)
						return CaloricLevel.NORMAL;
					else
						return CaloricLevel.FAT;
				}
			)
		);
		dishesByCaloricLevel.entrySet()
			.forEach(entry->System.out.println("caloricLevel="+entry.getKey()+", dish="+entry.getValue()));
		System.out.println();

		// wrong
		Map<Type, List<Dish>> caloricDishesByType = menus.stream()
			.filter(dish -> dish.getCalories() > 500)
			.collect(Collectors.groupingBy(Dish::getType));
		System.out.println(caloricDishesByType); // no FISH key

		caloricDishesByType = menus.stream()
			.collect(Collectors.groupingBy(Dish::getType, Collectors.filtering(dish -> dish.getCalories() > 500,
				Collectors.toList())));
		System.out.println(caloricDishesByType);
		System.out.println();

		Map<Type, List<String>> dishNamesByType = menus.stream()
			.collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(Dish::getName, Collectors.toList())));
		System.out.println(dishNamesByType);
	}
}
