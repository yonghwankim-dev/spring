package com.ch09.class01.step03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Apple {
	private Integer weight;

	public Apple(Integer weight) {
		this.weight = weight;
	}

	public Integer getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return "Apple{" +
			"weight=" + weight +
			'}';
	}

	public static void main(String[] args) {
		List<Apple> inventory = Arrays.asList(new Apple(30), new Apple(20), new Apple(10));

		inventory.sort((Apple a1, Apple a2)->a1.getWeight().compareTo(a2.getWeight()));
		System.out.println(inventory);

		inventory = Arrays.asList(new Apple(30), new Apple(20), new Apple(10));
		inventory.sort(Comparator.comparing(Apple::getWeight));
		System.out.println(inventory);
	}
}
