package com.ch11.class04.step04;

import java.util.Optional;
import java.util.Properties;

public class Main {

	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.setProperty("a", "5");
		properties.setProperty("b", "true");
		properties.setProperty("c", "-3");
		System.out.println(readDuration(properties, "a"));
		System.out.println(readDuration(properties, "b"));
		System.out.println(readDuration(properties, "c"));
		System.out.println(readDuration(properties, "d"));
	}

	public static int readDuration(Properties properties, String name){
		return Optional.ofNullable(properties.getProperty(name))
			.flatMap(OptionalUtility::stringToInt)
			.filter(i->i>0)
			.orElse(0);
	}
}

class OptionalUtility{
	public static Optional<Integer> stringToInt(String value){
		try{
			return Optional.of(Integer.parseInt(value));
		}catch (NumberFormatException e){
			return Optional.empty();
		}
	}
}
