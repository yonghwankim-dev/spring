package com.ch06.class04.step01;

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
	private boolean isVegetarian;

	public Dish(String name, int calories, Type type, boolean isVegetarian) {
		this.name = name;
		this.calories = calories;
		this.type = type;
		this.isVegetarian = isVegetarian;
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

	public boolean isVegetarian() {
		return isVegetarian;
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
			new Dish("pork", 800, Type.MEAT, false),
			new Dish("beef", 700, Type.MEAT, false),
			new Dish("chicken", 400, Type.MEAT, false),
			new Dish("french fries", 530, Type.OTHER, false),
			new Dish("rice", 350, Type.OTHER, true),
			new Dish("season fruit", 120, Type.OTHER, true),
			new Dish("pizza", 550, Type.OTHER, false),
			new Dish("prawns", 400, Type.FISH, false),
			new Dish("salmon", 450, Type.FISH, false));

		Map<Boolean, List<Dish>> partitionedMenu = menu.stream()
			.collect(Collectors.partitioningBy(Dish::isVegetarian));
		System.out.println(partitionedMenu.get(true));
		System.out.println(partitionedMenu.get(false));
		System.out.println();

		Map<Boolean, Map<Type, List<Dish>>> vegetarianDishesByType = menu.stream()
			.collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.groupingBy(Dish::getType)));
		System.out.println(vegetarianDishesByType);
		System.out.println();

		Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = menu.stream()
			.collect(Collectors.partitioningBy(Dish::isVegetarian,
				Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
					Optional::get)));
		System.out.println(mostCaloricPartitionedByVegetarian);

		Map<Boolean, Map<Boolean, List<Dish>>> caloricPartitionByVegetarianFor500Calories = menu.stream()
			.collect(
				Collectors.partitioningBy(Dish::isVegetarian, Collectors.partitioningBy(d -> d.getCalories() > 500)));
		System.out.println(caloricPartitionByVegetarianFor500Calories);

		Map<Boolean, Long> countingByVegetarian = menu.stream()
			.collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.counting()));
		System.out.println(countingByVegetarian);
	}
}
