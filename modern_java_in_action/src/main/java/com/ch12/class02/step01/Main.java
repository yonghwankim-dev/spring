package com.ch12.class02.step01;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class Main {
	public static void main(String[] args) {
		LocalDate date1 = LocalDate.of(2017, 9, 21);
		LocalDate date2 = date1.withYear(2011);
		LocalDate date3 = date2.withDayOfMonth(25);
		LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 2);
		System.out.println(date1); // 2017-09-21
		System.out.println(date2); // 2011-09-21
		System.out.println(date3); // 2011-09-25
		System.out.println(date4); // 2011-02-25
	}
}
