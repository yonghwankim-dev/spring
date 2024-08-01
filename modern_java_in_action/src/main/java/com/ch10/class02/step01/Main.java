package com.ch10.class02.step01;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

public class Main {
	public static void main(String[] args) {
		// 브랜드로 1차 그룹화 후에 컬러를 기준으로 2차 그룹화 수행
		Collector<? super Car, ?, Map<String, Map<String, List<Car>>>> carGroupingCollector = GroupingBuilder.groupOn(
			Car::getColor).after(Car::getBrand).get();

		Map<String, Map<String, List<Car>>> result = List.of(new Car("red", "audi"), new Car("blue", "bmw"),
				new Car("red", "ford"))
			.stream()
			.collect(carGroupingCollector);
		System.out.println(result);
	}
}
