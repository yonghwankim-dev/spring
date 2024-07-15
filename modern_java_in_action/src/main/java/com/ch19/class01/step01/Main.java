package com.ch19.class01.step01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<Student> students = new ArrayList<>();
		students.add(new Student("lee", 50));
		students.add(new Student("kim", 50));
		students.add(new Student("park", 70));

		Comparator<Student> comp = Comparator.comparing(Student::getScore)
			.thenComparing(Student::getName);
		students.sort(comp);
		System.out.println(students); // output: kim, lee, park
	}
}
