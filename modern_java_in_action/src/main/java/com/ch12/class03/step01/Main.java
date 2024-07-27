package com.ch12.class03.step01;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Main {
	public static void main(String[] args) {
		LocalDate date = LocalDate.of(2014, Month.MARCH, 18);
		ZoneId romeZone = ZoneId.of("Europe/Rome");
		ZonedDateTime zdt1 = date.atStartOfDay(romeZone);
		LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
		ZonedDateTime zdt2 = dateTime.atZone(romeZone);
		Instant instant = Instant.now();
		ZonedDateTime zdt3 = instant.atZone(romeZone);

		System.out.println(zdt1); // 2014-03-18T00:00+01:00[Europe/Rome]
		System.out.println(zdt2); // 2014-03-18T13:45+01:00[Europe/Rome]
		System.out.println(zdt3); // 2024-07-19T06:46:43.974049+02:00[Europe/Rome]

		date = LocalDate.now();
		ZoneId seoulZone = ZoneId.of("Asia/Seoul");
		zdt1 = date.atStartOfDay(seoulZone);
		dateTime = LocalDateTime.now();
		zdt2 = dateTime.atZone(seoulZone);
		instant = Instant.now();
		zdt3 = instant.atZone(seoulZone);
		System.out.println(zdt1); // 2024-07-19T00:00+09:00[Asia/Seoul]
		System.out.println(zdt2); // 2024-07-19T13:52:22.404593+09:00[Asia/Seoul]
		System.out.println(zdt3); // 2024-07-19T13:52:22.404593+09:00[Asia/Seoul]
	}
}
