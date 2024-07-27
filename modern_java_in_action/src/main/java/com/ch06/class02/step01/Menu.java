package com.ch06.class02.step01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Menu {

	private String name;

	public Menu(String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		List<Menu> menu = Arrays.asList(new Menu("pizza"), new Menu("pasta"));
		long howManyDishes = menu.stream().collect(Collectors.counting());
		System.out.println(howManyDishes);
		howManyDishes = menu.stream().count();
		System.out.println(howManyDishes);
	}
}
