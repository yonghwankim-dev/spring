package com.example.utils;

public class TimeUtils {
	public static void sleep(long value){
		try {
			Thread.sleep(value);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
