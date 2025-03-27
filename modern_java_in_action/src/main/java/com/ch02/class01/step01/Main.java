package com.ch02.class01.step01;

import java.util.List;

/**
 * 첫번째 시도 : 녹색 사과 필터링
 * 만약 녹색 사과가 아닌 빨간 사과, 다른 색상의 사과를 요구한다면 동일한 메소드가 여러개 생긱는 문제가 발생함
 */
public class Main {
	public static void main(String[] args) {
		Apple apple1 = new Apple(Color.GREEN);
		Apple apple2 = new Apple(Color.RED);
		Apple apple3 = new Apple(Color.GREEN);
		AppleService service = new AppleService();
		List<Apple> apples = service.filterGreenApples(List.of(apple1, apple2, apple3));
		System.out.println(apples);
	}
}
