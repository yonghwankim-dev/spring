package com.ch13.class02.step02;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		MyCollection<Integer> collection = new MyArrayList<>(new ArrayList<>());
		collection.add(1);
		collection.add(2);
		collection.add(3);

		boolean result = collection.removeIf(i -> i % 2 == 1);
		System.out.println(result);
		collection.forEach(i->System.out.print(i + " "));
	}
}
