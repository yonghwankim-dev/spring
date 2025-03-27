package com.ch02.class01.step01;

import java.util.ArrayList;
import java.util.List;


public class AppleService {

	// 사과의 색상이 초록색인 사과만을 필터링하여 반환
	public List<Apple> filterGreenApples(List<Apple> inventory){
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory){
			if (Color.GREEN.equals(apple.getColor())){
				result.add(apple);
			}
		}
		return result;
	}
}
