package com.ch02.class03.step03;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * 일곱번째 시도 : 리스트 형식으로 추상화
 * 사과만이 아닌 바나나, 오렌지, 문자열 등의 다른 리스트에서도 필터 메소드를 사용하기를 원한다.
 * 따라서 필터 메소드에서 리스트의 타입을 추상화한다
 *
 */
public class AppleService {

	public static void main(String[] args) {
		Apple apple1 = new Apple(Color.GREEN, 160);
		Apple apple2 = new Apple(Color.RED, 179);
		Apple apple3 = new Apple(Color.GREEN, 140);
		List<Apple> inventory = List.of(apple1, apple2, apple3);
		AppleService service = new AppleService();
		
		List<Apple> apples = service.filter(inventory, apple -> Color.RED.equals(apple.getColor()));
		System.out.println(apples);

		List<Integer> numbers = List.of(1, 2, 3, 4, 5);
		List<Integer> evens = service.filter(numbers, num -> num % 2 == 0);
		System.out.println(evens);
	}

	public <T> List<T> filter(List<T> list, Predicate<T> p){
		List<T> result = new ArrayList<>();
		for (T e : list){
			if (p.test(e)){
				result.add(e);
			}
		}
		return result;
	}
}
