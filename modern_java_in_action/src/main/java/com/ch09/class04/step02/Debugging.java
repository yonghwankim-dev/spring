package com.ch09.class04.step02;

import java.util.Arrays;
import java.util.List;

public class Debugging {
	public static void main(String[] args) {
		List<Point> points = Arrays.asList(new Point(12, 2), null);
		points.stream()
				.map(Point::getX)
				.forEach(System.out::println);
	}
}
