package com.ch12.class01.step01;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoField;

public class Main {
	public static void main(String[] args) {
		LocalDate date = LocalDate.of(2017, 9, 21);
		int year = date.getYear();
		Month month = date.getMonth();
		int dayOfMonth = date.getDayOfMonth();
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		int len = date.lengthOfMonth();
		boolean leap = date.isLeapYear();
		System.out.println(year); 		// 2017
		System.out.println(month);		// SEPTEMBER
		System.out.println(dayOfMonth); // 21
		System.out.println(dayOfWeek); 	// THURSDAY
		System.out.println(len);		// 30
		System.out.println(leap);		// false

		year = date.get(ChronoField.YEAR);
		int monthValue = date.get(ChronoField.MONTH_OF_YEAR);
		dayOfMonth = date.get(ChronoField.DAY_OF_MONTH);
		System.out.println(year);		// 2017
		System.out.println(monthValue);	// 9
		System.out.println(dayOfMonth);	// 21

		LocalTime time = LocalTime.of(13, 45, 20);
		int hour = time.getHour();
		int minute = time.getMinute();
		int second = time.getSecond();
		System.out.println(hour);	// 13
		System.out.println(minute); // 45
		System.out.println(second); // 20
	}
}
