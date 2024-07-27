package com.ch06.class03.step02;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
		return "Dish{" +
			"name='" + name + '\'' +
			", calories=" + calories +
			", type=" + type +
			'}';
	}

	public static void main(String[] args) {
		Map<String, List<String>> dishTags = new HashMap<>();
		dishTags.put("pork", Arrays.asList("greasy", "salty"));
		dishTags.put("beef", Arrays.asList("salty", "roasted"));
		dishTags.put("chicken", Arrays.asList("fried", "crisp"));
		dishTags.put("french fries", Arrays.asList("greasy", "fried"));
		dishTags.put("rice", Arrays.asList("light", "natural"));
		dishTags.put("season fruit", Arrays.asList("fresh", "natural"));
		dishTags.put("pizza", Arrays.asList("tasty", "salty"));
		dishTags.put("prawns", Arrays.asList("tasty", "roasted"));
		dishTags.put("salmon", Arrays.asList("delicious", "fresh"));

		List<Dish> menu = Arrays.asList(
			new Dish("pork", 800, Dish.Type.MEAT),
			new Dish("beef", 700, Dish.Type.MEAT),
			new Dish("chicken", 400, Dish.Type.MEAT),
			new Dish("french fries", 530, Dish.Type.OTHER),
			new Dish("rice", 350, Dish.Type.OTHER),
			new Dish("season fruit", 120, Dish.Type.OTHER),
			new Dish("pizza", 550, Dish.Type.OTHER),
			new Dish("prawns", 400, Dish.Type.FISH),
			new Dish("salmon", 450, Dish.Type.FISH));

		Map<Type, Set<String>> dishNamesByType = menu.stream()
			.collect(Collectors.groupingBy(Dish::getType,
				Collectors.flatMapping(dish -> dishTags.get(dish.getName()).stream(), Collectors.toSet())));
		System.out.println(dishNamesByType);
	}

}
