package com.ch08.class01.step02;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<String> friends = List.of("Raphael", "Olivia", "Thibaut");
		try{
			// not add the element
			friends.add("Chih-Chun");
		}catch (UnsupportedOperationException e){
			System.out.println("Exception: " + e);
		}

		try{
			// not set the element
			friends.set(0, "aa");
		}catch (UnsupportedOperationException e){
			System.out.println("Exception: " + e);
		}

		try{
			// not add null value
			friends.add(null);
		}catch (UnsupportedOperationException e){
			System.out.println("Exception: " + e);
		}
		System.out.println(friends);
	}
}
