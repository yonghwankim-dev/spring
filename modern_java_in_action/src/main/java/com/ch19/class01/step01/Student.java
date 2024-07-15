package com.ch19.class01.step01;

public class Student {
	
	private String name;
	private int score;

	public Student(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	@Override
	public String toString() {
		return "Student{" +
			"name='" + name + '\'' +
			", score=" + score +
			'}';
	}
}
