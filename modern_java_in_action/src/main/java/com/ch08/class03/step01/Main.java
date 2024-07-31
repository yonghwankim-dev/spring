package com.ch08.class03.step01;

import java.util.Map;

public class Main {
	public static void main(String[] args) {
		Map<String, Integer> ageOfFriends = Map.of("Raphael", 30, "Olivia", 25, "Thibaut", 26);

		for (Map.Entry<String, Integer> entry : ageOfFriends.entrySet()) {
			String friend = entry.getKey();
			Integer age = entry.getValue();
			System.out.println(friend + " is " + age + " years old");
		}

		ageOfFriends.forEach((friend, age)->System.out.println(friend + " is " + age + " years old"));
	}
}
