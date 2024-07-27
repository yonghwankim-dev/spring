package com.ch12.class02.step02;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Main {
	public static void main(String[] args) {
		LocalDate date1 = LocalDate.of(2017, 9, 21);
		LocalDate date2 = date1.plusWeeks(1);
		LocalDate date3 = date2.minusYears(6);
		LocalDate date4 = date3.plus(6, ChronoUnit.MONTHS);
		System.out.println(date1); // 2017-09-21
		System.out.println(date2); // 2017-09-28
		System.out.println(date3); // 2011-09-28
		System.out.println(date4); // 2012-03-28
	}
}
