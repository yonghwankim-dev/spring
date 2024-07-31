package com.ch08.class01.step04;

import java.util.Map;

public class Main {
	public static void main(String[] args) {
		Map<String, Integer> ageOfFriends = Map.of("Raphael", 30, "Olivia", 25, "Thibaut", 26);

		try{
			ageOfFriends.put("yong", 20);
		}catch (UnsupportedOperationException e){
			System.out.println("Exception : " + e);
		}

		try{
			ageOfFriends.remove("Raphael");
		}catch (UnsupportedOperationException e){
			System.out.println("Exception : " + e);
		}
		System.out.println(ageOfFriends);

		// 가변 인수 사용
		Map<String, Integer> ageOfFriends2 = Map.ofEntries(
			Map.entry("Raphael", 30),
			Map.entry("Olivia", 25),
			Map.entry("Thibaut", 26)
		);
		try{
			ageOfFriends2.put("yong", 20);
		}catch (UnsupportedOperationException e){
			System.out.println("Exception : " + e);
		}
		try{
			ageOfFriends.remove("Raphael");
		}catch (UnsupportedOperationException e){
			System.out.println("Exception : " + e);
		}
		System.out.println(ageOfFriends2);
	}
}
