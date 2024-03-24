package com.ch2.class02.step01;

public class AppleHeavyWeightPredicate implements ApplePredicate{
	@Override
	public boolean test(Apple apple) {
		return apple.getWeight() > 150;
	}
}
