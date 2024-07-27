package com.ch12.class01.step02;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class Main {
	public static void main(String[] args) {
		LocalDate date = LocalDate.of(2017, 9, 21);
		LocalTime time = LocalTime.of(13, 45, 20);
		// 2017-09-21T13:45:20
		LocalDateTime dt1 = LocalDateTime.of(2017, Month.SEPTEMBER, 21, 13, 45, 20);
		LocalDateTime dt2 = LocalDateTime.of(date, time);
		LocalDateTime dt3 = date.atTime(13, 45, 20);
		LocalDateTime dt4 = date.atTime(time);
		LocalDateTime dt5 = time.atDate(date);
		System.out.println(dt1); // 2017-09-21T13:45:20
		System.out.println(dt2); // 2017-09-21T13:45:20
		System.out.println(dt3); // 2017-09-21T13:45:20
		System.out.println(dt4); // 2017-09-21T13:45:20
		System.out.println(dt5); // 2017-09-21T13:45:20
	}
}
