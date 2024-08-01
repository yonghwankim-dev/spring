package com.ch09.class02.step05.observer;

public class Guardian implements Observer{
	@Override
	public void notify(String tweet) {
		if (tweet != null && tweet.contains("queen")) {
			System.out.println("Yet another news in London... " + tweet);
		}
	}
}
