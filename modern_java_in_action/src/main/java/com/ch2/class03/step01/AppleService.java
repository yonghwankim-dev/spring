package com.ch2.class03.step01;

import java.util.ArrayList;
import java.util.List;

import com.ch2.class02.step02.AppleFancyFormatter;
import com.ch2.class02.step02.AppleFormatter;
import com.ch2.class02.step02.AppleSimpleFormatter;

/**
 * 다섯번째 시도 : 익명 클래스 사용
 * 익명 클래스를 사용하여 불필요한 구현 클래스(AppleFancyFormatter, AppleSimpleFomratter)를 만들지 않고
 * 클래스 생성 및 인스턴스 생성을 같이 가능함
 *
 */
public class AppleService {

	public static void main(String[] args) {
		Apple apple1 = new Apple(Color.GREEN, 160);
		Apple apple2 = new Apple(Color.RED, 179);
		Apple apple3 = new Apple(Color.GREEN, 140);
		List<Apple> inventory = List.of(apple1, apple2, apple3);
		AppleService service = new AppleService();

		List<Apple> apples = service.filterApples(inventory, new ApplePredicate() {
			@Override
			public boolean test(Apple apple) {
				return Color.RED.equals(apple.getColor());
			}
		});
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
