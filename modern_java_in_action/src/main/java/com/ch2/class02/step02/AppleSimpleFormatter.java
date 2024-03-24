package com.ch2.class02.step02;

public class AppleSimpleFormatter implements AppleFormatter{
	@Override
	public String accept(Apple apple) {
		return String.format("An apple of %dg", apple.getWeight());
	}
}
