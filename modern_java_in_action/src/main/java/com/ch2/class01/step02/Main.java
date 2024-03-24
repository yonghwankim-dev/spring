package com.ch2.class01.step02;

import java.util.List;

/**
 * 두번째 시도 : 색을 파라미터화
 * 색을 파라미터화하여 전달하면 여러 색상에도 대응할 수 있음
 * 그러나, 만약에 농부가 가벼운 사과와 무거운 사과를 구분할 수 있다면 좋다는 요구사항이 있는 경우가 있음
 * 이러한 경우 새로운 메소드를 정의하고 weight 매개변수를 전달하여 생성할 수 밖에 없음
 *
 * 문제점
 * - 두 메소드에서 inventory의 Apple 요소를 순회하고 필터링 조건을 적용하는 부분이 중복됩니다.
 */
public class Main {
	public static void main(String[] args) {
		Apple apple1 = new Apple(Color.GREEN, 160);
		Apple apple2 = new Apple(Color.RED, 150);
		Apple apple3 = new Apple(Color.GREEN, 140);
		List<Apple> inventory = List.of(apple1, apple2, apple3);
		AppleService service = new AppleService();
		List<Apple> apples = service.filterApplesByColor(inventory, Color.RED);
		System.out.println(apples);

		List<Apple> apples2 = service.filterApplesByColor(inventory, Color.GREEN);
		System.out.println(apples2);

		List<Apple> apples3 = service.filterApplesByWeight(inventory, 150);
		System.out.println(apples3);
	}
}
