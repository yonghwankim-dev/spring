package com.ch09.class04.step03;

import java.util.Arrays;
import java.util.List;

import com.ch09.class04.step02.Point;

public class Debugging {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3);
		// 메서드 참조(Debugging::divideByZero)를 사용하는 클래스와 같은 곳에 선언되어 있는 메서드를 참조할 때는 메서드 참조 이름이 스택 트레이스에 나타난다
		numbers.stream()
				.map(Debugging::divideByZero)
				.forEach(System.out::println);
	}

	private static int divideByZero(int n) {
		return n / 0;
	}
}
