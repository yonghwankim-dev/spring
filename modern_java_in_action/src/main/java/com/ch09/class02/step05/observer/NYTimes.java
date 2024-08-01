package com.ch09.class02.step05.observer;

public class NYTimes implements Observer {
	@Override
	public void notify(String tweet) {
		if (tweet != null && tweet.contains("money")) {
			System.out.println("Breaking news in NY! " + tweet);
		}
	}
}
