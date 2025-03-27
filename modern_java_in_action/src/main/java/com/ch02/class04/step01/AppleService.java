package com.ch02.class04.step01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * Comparator로 정렬하기
 */
public class AppleService {

	public static void main(String[] args) {
		Apple apple1 = new Apple(Color.GREEN, 160);
		Apple apple2 = new Apple(Color.RED, 179);
		Apple apple3 = new Apple(Color.GREEN, 140);
		List<Apple> inventory = new ArrayList<>(List.of(apple1, apple2, apple3));

		// 무게를 기준으로 오름차순 정렬
		// 익명 클래스
		inventory.sort(new Comparator<>() {
			@Override
			public int compare(Apple o1, Apple o2) {
				return Integer.compare(o1.getWeight(), o2.getWeight());
			}
		});
		System.out.println(inventory);

		// 람다 표현식
		inventory.sort((o1, o2) -> Integer.compare(o1.getWeight(), o2.getWeight()));
		System.out.println(inventory);

		// 메소드 참조로 표현
		inventory.sort(Comparator.comparingInt(Apple::getWeight));
		System.out.println(inventory);
	}

}
