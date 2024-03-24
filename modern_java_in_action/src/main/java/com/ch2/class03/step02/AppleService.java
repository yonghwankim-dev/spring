package com.ch2.class03.step02;

import java.util.ArrayList;
import java.util.List;

/**
 * 여섯번째 시도 : 람다 표현식 사용
 * 동작 파라미터화 사용시 익명 클래스를 사용하면 불필요한 클래스 생성을 막을 수 있다.
 * 하지만 익명 클래스 생성시 지저분한 코드가 생성된다.
 *
 * 위와 같은 문제점을 해결하기 위해서 람다 표현식을 사용한다
 *
 */
public class AppleService {

	public static void main(String[] args) {
		Apple apple1 = new Apple(Color.GREEN, 160);
		Apple apple2 = new Apple(Color.RED, 179);
		Apple apple3 = new Apple(Color.GREEN, 140);
		List<Apple> inventory = List.of(apple1, apple2, apple3);
		AppleService service = new AppleService();
		
		List<Apple> apples = service.filterApples(inventory, apple -> Color.RED.equals(apple.getColor()));
		System.out.println(apples);
	}

	public List<Apple> filterApples(List<Apple> inventory, ApplePredicate p){
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory){
			if (p.test(apple)){
				result.add(apple);
			}
		}
		return result;
	}
}
