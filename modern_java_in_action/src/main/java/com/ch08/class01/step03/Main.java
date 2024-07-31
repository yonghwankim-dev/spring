package com.ch08.class01.step03;

import java.util.Set;

public class Main {
	public static void main(String[] args) {
		Set<String> friends = Set.of("Raphael", "Olivia", "Thibaut");
		try{
			// not add the element
			friends.add("Chih-Chun");
		}catch (UnsupportedOperationException e){
			System.out.println("Exception: " + e);
		}

		try{
			// not add unique friend
			friends.add("Raphael");
		}catch (UnsupportedOperationException e){
			System.out.println("Exception: " + e);
		}

		try{
			// not add null value
			friends.add(null);
		}catch (UnsupportedOperationException e){
			System.out.println("Exception: " + e);
		}
	}
}
