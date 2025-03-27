package com.ch02.class01.step03;

import java.util.List;

/**
 * 세번째 시도: 가능한 모든 속성으로 필터링
 * 문제점
 * - 모든것을 처리하는 거대한 하나의 필터 메소드가 생깁니다.
 */
public class Main {
	public static void main(String[] args) {
		Apple apple1 = new Apple(Color.GREEN, 160);
		Apple apple2 = new Apple(Color.RED, 150);
		Apple apple3 = new Apple(Color.GREEN, 140);
		List<Apple> inventory = List.of(apple1, apple2, apple3);
		AppleService service = new AppleService();
		List<Apple> apples = service.filterApples(inventory, Color.RED, 150, false);
		System.out.println(apples);
	}
}
