package com.ch2.class02.step02;

public class AppleFancyFormatter implements AppleFormatter{
	@Override
	public String accept(Apple apple) {
		String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
		return String.format("A %s %s apple",characteristic, apple.getColor());
	}
}
