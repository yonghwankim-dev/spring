package com.ch12.class02.step03;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class Main {
	public static void main(String[] args) {
		LocalDate date1 = LocalDate.of(2014, 3, 18);
		LocalDate date2 = date1.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
		LocalDate date3 = date2.with(TemporalAdjusters.lastDayOfMonth());
		System.out.println(date1); // 2014-03-18
		System.out.println(date2); // 2014-03-23
		System.out.println(date3); // 2014-03-31
	}
}
