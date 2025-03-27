package com.ch02.class01.step02;

import java.util.ArrayList;
import java.util.List;


public class AppleService {

	// color를 매개변화하여 여러 색상을 대상으로 필터링할 수 있도록 함
	public List<Apple> filterApplesByColor(List<Apple> inventory, Color color){
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory){
			if (apple.getColor().equals(color)){
				result.add(apple);
			}
		}
		return result;
	}

	// weight보다 큰 사과를 필터링하여 반환함
	public List<Apple> filterApplesByWeight(List<Apple> inventory, int weight){
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory){
			if (apple.getWeight() > weight){
				result.add(apple);
			}
		}
		return result;
	}
}
