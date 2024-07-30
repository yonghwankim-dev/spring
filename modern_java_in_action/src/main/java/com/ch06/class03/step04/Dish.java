package com.ch06.class03.step04;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
		return name;
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
			new Dish("pork", 800, Type.MEAT),
			new Dish("beef", 700, Type.MEAT),
			new Dish("chicken", 400, Type.MEAT),
			new Dish("french fries", 530, Type.OTHER),
			new Dish("rice", 350, Type.OTHER),
			new Dish("season fruit", 120, Type.OTHER),
			new Dish("pizza", 550, Type.OTHER),
			new Dish("prawns", 400, Type.FISH),
			new Dish("salmon", 450, Type.FISH));

		Map<Type, Long> typesCount = menu.stream()
			.collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
		System.out.println(typesCount);

		Map<Type, Optional<Dish>> mostCaloricByType = menu.stream()
			.collect(Collectors.groupingBy(
				Dish::getType,
				Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));
		System.out.println(mostCaloricByType);

		Map<Type, Dish> mostCaloricByType2 = menu.stream()
			.collect(Collectors.groupingBy(Dish::getType,
				Collectors.collectingAndThen(
					Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
					Optional::get
				)));
		System.out.println(mostCaloricByType2);

		Map<Type, Integer> totalCaloriesByType = menu.stream()
			.collect(Collectors.groupingBy(Dish::getType, Collectors.summingInt(Dish::getCalories)));
		System.out.println(totalCaloriesByType);

		Map<Type, Set<CaloricLevel>> caloricLevelsByType = menu.stream()
			.collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(dish -> {
				if (dish.getCalories() <= 400)
					return CaloricLevel.DIET;
				else if (dish.getCalories() <= 700)
					return CaloricLevel.NORMAL;
				else
					return CaloricLevel.FAT;
			}, Collectors.toSet())));
		System.out.println(caloricLevelsByType);

		Map<Type, Set<CaloricLevel>> caloricLevelsByType2 = menu.stream()
			.collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(dish -> {
				if (dish.getCalories() <= 400)
					return CaloricLevel.DIET;
				else if (dish.getCalories() <= 700)
					return CaloricLevel.NORMAL;
				else
					return CaloricLevel.FAT;
			}, Collectors.toCollection(HashSet::new))));
		System.out.println(caloricLevelsByType2);
	}

}
