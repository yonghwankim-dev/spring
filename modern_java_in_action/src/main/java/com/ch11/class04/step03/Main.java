package com.ch11.class04.step03;

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
		String value = properties.getProperty(name);
		if (value != null){
			try{
				int i = Integer.parseInt(value);
				if (i > 0){
					return i;
				}
			} catch (NumberFormatException e) {

			}
		}
		return 0;
	}
}
