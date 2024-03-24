package com.ch2.class02.step02;

import java.util.ArrayList;
import java.util.List;

/**
 * 유연한 prettyPrintApple 메소드 구현하기
 * AppleFomratter 인터페이스를 이용하여 다양한 형식의 문자열로 변환해서 반환할 수 있다
 *
 * 문제점
 * 여러 클래스를 구현해서 인스턴스화하는 과정이 조금 거추장스러움
 *
 */
public class AppleService {


	public static void main(String[] args) {
		Apple apple1 = new Apple(Color.GREEN, 160);
		Apple apple2 = new Apple(Color.RED, 179);
		Apple apple3 = new Apple(Color.GREEN, 140);
		List<Apple> inventory = List.of(apple1, apple2, apple3);
		AppleService service = new AppleService();
		service.prettyPrintApple(inventory, new AppleFancyFormatter());
		service.prettyPrintApple(inventory, new AppleSimpleFormatter());
	}

	private void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
		for (Apple apple : inventory){
			String output = formatter.accept(apple);
			System.out.println(output);
		}
	}
}
