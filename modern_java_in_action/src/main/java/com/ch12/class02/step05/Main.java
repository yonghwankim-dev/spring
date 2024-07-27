package com.ch12.class02.step05;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class Main {
	public static void main(String[] args) {
		LocalDate date = LocalDate.of(2014, 3, 18);
		String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
		String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
		System.out.println(s1); // 20140318
		System.out.println(s2); // 2014-03-18

		LocalDate date1 = LocalDate.parse(s1, DateTimeFormatter.BASIC_ISO_DATE);
		LocalDate date2 = LocalDate.parse(s2, DateTimeFormatter.ISO_LOCAL_DATE);
		System.out.println(date1); // 2014-03-18
		System.out.println(date2); // 2014-03-18

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		date1 = LocalDate.of(2014, 3, 18);
		String formattedDate = date1.format(formatter);
		date2 = LocalDate.parse(formattedDate, formatter);
		System.out.println(formattedDate); // 18/03/2014
		System.out.println(date1); // 2014-03-18
		System.out.println(date2); // 2014-03-18

		DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
		date1 = LocalDate.of(2014, 3, 18);
		formattedDate = date.format(italianFormatter);
		date2 = LocalDate.parse(formattedDate, italianFormatter);
		System.out.println(formattedDate); // 18. marzo 2014
		System.out.println(date1); // 2014-03-18
		System.out.println(date2); // 2014-03-18

		italianFormatter = new DateTimeFormatterBuilder()
			.appendText(ChronoField.DAY_OF_MONTH)
			.appendLiteral(". ")
			.appendText(ChronoField.MONTH_OF_YEAR)
			.appendLiteral(" ")
			.appendText(ChronoField.YEAR)
			.parseCaseInsensitive()
			.toFormatter(Locale.ITALIAN);
		date1 = LocalDate.of(2014, 3, 18);
		formattedDate = date.format(italianFormatter);
		date2 = LocalDate.parse(formattedDate, italianFormatter);
		System.out.println(formattedDate); // 18. marzo 2014
		System.out.println(date1); // 2014-03-18
		System.out.println(date2); // 2014-03-18
	}
}
