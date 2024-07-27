package com.ch12.class02.step04;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class Main {
	public static void main(String[] args) {
		LocalDate date1 = LocalDate.of(2024, 7, 19);
		LocalDate date2 = date1.with(new NextWorkingDay());
		System.out.println(date1); // 2024-07-19
		System.out.println(date2); // 2024-07-22

		LocalDate date3 = date1.with(temporal -> {
			DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
			int dayToAdd = 1;
			if (dow == DayOfWeek.FRIDAY){
				dayToAdd = 3;
			}else if (dow == DayOfWeek.SATURDAY){
				dayToAdd = 2;
			}
			return temporal.plus(dayToAdd, ChronoUnit.DAYS);
		});
		System.out.println(date3); // 2024-07-22

		TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(temporal -> {
			DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
			int dayToAdd = 1;
			if (dow == DayOfWeek.FRIDAY) {
				dayToAdd = 3;
			} else if (dow == DayOfWeek.SATURDAY) {
				dayToAdd = 2;
			}
			return temporal.plus(dayToAdd, ChronoUnit.DAYS);
		});
		LocalDate date4 = date1.with(nextWorkingDay);
		System.out.println(date4); // 2024-07-22
	}
}

class NextWorkingDay implements TemporalAdjuster {
	@Override
	public Temporal adjustInto(Temporal temporal) {
		DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
		int dayToAdd = 1;
		if (dow == DayOfWeek.FRIDAY){
			dayToAdd = 3;
		}else if (dow == DayOfWeek.SATURDAY){
			dayToAdd = 2;
		}
		return temporal.plus(dayToAdd, ChronoUnit.DAYS);
	}
}
