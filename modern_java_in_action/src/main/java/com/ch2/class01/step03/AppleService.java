package com.ch2.class01.step03;

import java.util.ArrayList;
import java.util.List;

public class AppleService {
	public List<Apple> filterApples(List<Apple> inventory, Color color, int weight, boolean flag){
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory){
			if ((flag && apple.getColor().equals(color)) ||
				(!flag && apple.getWeight() > weight)){
				result.add(apple);
			}
		}
		return result;
	}
}
