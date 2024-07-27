package com.ch12.class01.step03;

import java.time.Instant;
import java.time.temporal.ChronoField;
import java.time.temporal.UnsupportedTemporalTypeException;

public class Main {
	public static void main(String[] args) {
		Instant instant1 = Instant.ofEpochSecond(3);
		Instant instant2 = Instant.ofEpochSecond(3, 0);
		Instant instant3 = Instant.ofEpochSecond(2, 1_000_000_000);
		Instant instant4 = Instant.ofEpochSecond(4, -1_000_000_000);
		System.out.println(instant1); // 1970-01-01T00:00:03Z
		System.out.println(instant2); // 1970-01-01T00:00:03Z
		System.out.println(instant3); // 1970-01-01T00:00:03ZR
		System.out.println(instant4); // 1970-01-01T00:00:03Z

		try{
			int day = Instant.now().get(ChronoField.DAY_OF_MONTH);
		}catch (UnsupportedTemporalTypeException e) {
			System.out.println(e.getMessage());
		}
	}
}
