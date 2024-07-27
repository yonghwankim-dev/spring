package com.ch12.class01.step04;

import java.time.Duration;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Main {
	public static void main(String[] args) {
		Duration threeMinutes1 = Duration.ofMinutes(3);
		Duration threeMinutes2 = Duration.of(3, ChronoUnit.MINUTES);

		Period tenDays = Period.ofDays(10);
		Period threeWeeks = Period.ofWeeks(3);
		Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
		System.out.println(threeMinutes1); // PT3M
		System.out.println(threeMinutes2); // PT3M
		System.out.println(tenDays); 		// P10D
		System.out.println(threeWeeks);		// P21D
		System.out.println(twoYearsSixMonthsOneDay); // P2Y6M1D
	}
}
