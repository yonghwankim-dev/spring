package com.ch09.class02.step05.observer;

public class LeMonde implements Observer{
	@Override
	public void notify(String tweet) {
		if (tweet != null && tweet.contains("wine")) {
			System.out.println("Today cheese, wine and news! " + tweet);
		}
	}
}
