package com.ch08.class01.step01;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		List<String> friends = Arrays.asList("Raphael", "Olivia", "Thibaut");
		friends.set(0, "Richard"); // modifiable
		try{
			friends.add("Thibaut"); // unsupportedOperation
		}catch (UnsupportedOperationException e){
			System.out.println("set() is not supported");
		}
		System.out.println(friends);

		Set<String> friendSet = new HashSet<>(Arrays.asList("Raphael", "Olivia", "Thibaut"));
		friendSet.add("Thibaut"); // addable
		System.out.println(friendSet);

		Set<String> friendSet2 = Stream.of("Raphael", "Olivia", "Thibaut")
			.collect(Collectors.toSet());
		friendSet2.add("Thibaut");
		System.out.println(friendSet2);
	}
}
