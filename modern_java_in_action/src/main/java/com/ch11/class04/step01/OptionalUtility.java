package com.ch11.class04.step01;

import java.util.Optional;

public class OptionalUtility {
	public static Optional<Integer> stringToInt(String s) {
		try {
			return Optional.of(Integer.parseInt(s));
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}

	public static void main(String[] args) {
		System.out.println(OptionalUtility.stringToInt("1"));
		System.out.println(OptionalUtility.stringToInt("a"));
	}
}
