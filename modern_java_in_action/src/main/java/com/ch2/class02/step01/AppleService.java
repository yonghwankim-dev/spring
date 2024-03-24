package com.ch2.class02.step01;

import java.util.ArrayList;
import java.util.List;

/**
 * 추상적 조건으로 필터링
 */
public class AppleService {
	public List<Apple> filterApples(List<Apple> inventory, ApplePredicate p){
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory){
			if (p.test(apple)){
				result.add(apple);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		Apple apple1 = new Apple(Color.GREEN, 160);
		Apple apple2 = new Apple(Color.RED, 179);
		Apple apple3 = new Apple(Color.GREEN, 140);
		List<Apple> inventory = List.of(apple1, apple2, apple3);
		AppleService service = new AppleService();
		List<Apple> greenApples = service.filterApples(inventory, new AppleGreenColorPredicate());
		System.out.println(greenApples);

		List<Apple> heavyApples = service.filterApples(inventory, new AppleHeavyWeightPredicate());
		System.out.println(heavyApples);

		List<Apple> redAndHeavyApples = service.filterApples(inventory, new AppleRedAndHeavyPredicate());
		System.out.println(redAndHeavyApples);
	}
}
