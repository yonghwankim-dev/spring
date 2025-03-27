package com.ch04.class01.step01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Dish {

	enum Type {
		MEAT, FISH, OTHER
	}

	private String name;
	private int calories;
	private Dish.Type type;
	private boolean isVegetarian;

	public Dish(String name, int calories, Dish.Type type, boolean isVegetarian) {
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

	public Dish.Type getType() {
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
			new Dish("pork", 800, Dish.Type.MEAT, false),
			new Dish("beef", 700, Dish.Type.MEAT, false),
			new Dish("chicken", 400, Dish.Type.MEAT, false),
			new Dish("french fries", 530, Dish.Type.OTHER, false),
			new Dish("rice", 350, Dish.Type.OTHER, true),
			new Dish("season fruit", 120, Dish.Type.OTHER, true),
			new Dish("pizza", 550, Dish.Type.OTHER, false),
			new Dish("prawns", 400, Dish.Type.FISH, false),
			new Dish("salmon", 450, Dish.Type.FISH, false));

		test1(menu);
		test2(menu);
	}

	// 400 칼로리 미만의 요리의 이름을 오름차순으로 정렬하여 출력
	private static void test1(List<Dish> menu) {
		List<Dish> lowCaloricDishes = new ArrayList<>();
		for (Dish dish : menu){
			if (dish.getCalories() < 400){
				lowCaloricDishes.add(dish);
			}
		}
		Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
			@Override
			public int compare(Dish d1, Dish d2) {
				return Integer.compare(d1.getCalories(), d2.getCalories());
			}
		});
		List<String> lowCaloricDishName = new ArrayList<>();
		for (Dish dish : lowCaloricDishes){
			lowCaloricDishName.add(dish.getName());
		}
		System.out.println("400 칼로리 미만의 요리 이름 목록 " + lowCaloricDishName);
	}

	// 요리들중 400 칼로리 미마인 요리를 칼로리 기준으로 오름차순으로 정렬한 다음에 이름 목록을 계산
	private static void test2(List<Dish> menu){
		List<String> lowCaloricDishName = menu.stream()
			.filter(d -> d.getCalories() < 400)
			.sorted(Comparator.comparing(Dish::getCalories))
			.map(Dish::getName)
			.collect(Collectors.toList());
		System.out.println("400 칼로리 미만 요리 목록 : " + lowCaloricDishName);
	}
}
