package com.ch09.class01.step02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ch06.class05.step01.ToListCollector;

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

	private CaloricLevel getCaloricLevel() {
		if (calories <= 400){
			return CaloricLevel.DIET;
		}else if (calories <= 700){
			return CaloricLevel.NORMAL;
		}else{
			return CaloricLevel.FAT;
		}
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

		Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream()
			.collect(Collectors.groupingBy(dish -> {
				if (dish.getCalories() <= 400)
					return CaloricLevel.DIET;
				else if (dish.getCalories() <= 700)
					return CaloricLevel.NORMAL;
				return CaloricLevel.FAT;
			}));
		System.out.println(dishesByCaloricLevel);

		dishesByCaloricLevel = menu.stream()
			.collect(Collectors.groupingBy(Dish::getCaloricLevel));
		System.out.println(dishesByCaloricLevel);
	}

}
