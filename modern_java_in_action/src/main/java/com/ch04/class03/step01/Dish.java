package com.ch04.class03.step01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ch13.class04.step01.A;

public class Dish {

	enum Type {
		MEAT, FISH, OTHER
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

		testForEach(menu);
		testIterator(menu);
		testStream(menu);
		testHighCaloricDishes(menu);
		testThreeHighCaloricDishNames(menu);
	}

	// 컬렉션 for-each 루프를 이용한 외부 반복
	// 요리들의 이름만을 추출하여 계산
	private static void testForEach(List<Dish> menu) {
		List<String> names = new ArrayList<>();
		for (Dish dish : menu) {
			names.add(dish.getName());
		}
		System.out.println("요리 이름 목록 " + names);
	}

	// 컬렉션을 내부적으로 숨겨졌던 반복자(iterator)를 사용한 외부 반복
	private static void testIterator(List<Dish> menu) {
		List<String> names = new ArrayList<>();
		Iterator<Dish> iterator = menu.iterator();
		while (iterator.hasNext()) {
			Dish dish = iterator.next();
			names.add(dish.getName());
		}
		System.out.println("요리 이름 목록 " + names);
	}

	private static void testStream(List<Dish> menu) {
		List<String> names = menu.stream()
			.map(Dish::getName)
			.collect(Collectors.toList());
		System.out.println("요리 이름 목록 " + names);
	}

	// 칼로리가 300보다 큰 요리들의 이름 목록을 계산
	private static void testHighCaloricDishes(List<Dish> menu) {
		List<String> names = menu.stream()
			.filter(dish -> dish.getCalories() > 300)
			.map(Dish::getName)
			.collect(Collectors.toList());
		System.out.println("칼로리가 300보다 큰 요리들의 이름 목록 " + names);
	}

	private static void testThreeHighCaloricDishNames(List<Dish> menu) {
		List<String> names = menu.stream()
			.filter(dish -> {
				System.out.println("filtering " + dish.getName());
				return dish.getCalories() > 300;
			})
			.map(dish->{
				System.out.println("mapping " + dish.getName());
				return dish.getName();
			})
			.limit(3)
			.collect(Collectors.toList());
		System.out.println("칼로리가 300보다 큰 요리들의 이름 목록 " + names);
	}
}
