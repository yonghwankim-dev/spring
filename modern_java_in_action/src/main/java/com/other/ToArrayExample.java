package com.other;

import java.util.List;

public class ToArrayExample {

	public static int[] testListToArray(List<Integer> list) {
		return list.stream()
			.mapToInt(Integer::intValue)
			.toArray();
	}

	public static int[] testListToArrayUsingForLoop(List<Integer> list) {
		int[] array = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		}
		return array;
	}
}
