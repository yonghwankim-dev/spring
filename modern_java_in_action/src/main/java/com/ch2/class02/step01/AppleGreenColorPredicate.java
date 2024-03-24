package com.ch2.class02.step01;

public class AppleGreenColorPredicate implements ApplePredicate{

	@Override
	public boolean test(Apple apple) {
		return Color.GREEN.equals(apple.getColor());
	}
}
