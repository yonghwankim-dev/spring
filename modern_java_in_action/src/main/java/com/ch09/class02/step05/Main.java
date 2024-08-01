package com.ch09.class02.step05;

import com.ch09.class02.step05.observer.Guardian;
import com.ch09.class02.step05.observer.LeMonde;
import com.ch09.class02.step05.observer.NYTimes;
import com.ch09.class02.step05.subject.Feed;

public class Main {
	public static void main(String[] args) {
		Feed f = new Feed();
		f.registerObserver(new NYTimes());
		f.registerObserver(new Guardian());
		f.registerObserver(new LeMonde());
		f.notifyObservers("The queen said her favourite book is Modern Java in Action");

		// 람다 표현식 사용하기
		f = new Feed();
		f.registerObserver(tweet -> {
			if (tweet != null && tweet.contains("money")) {
				System.out.println("Breaking news in NY! " + tweet);
			}
		});
		f.registerObserver(tweet -> {
			if (tweet != null && tweet.contains("queen")){
				System.out.println("Yet another news in London... " + tweet);
			}
		});
		f.registerObserver(tweet -> {
			if (tweet != null && tweet.contains("wine")) {
				System.out.println("Today cheese, wine and news! " + tweet);
			}
		});
		f.notifyObservers("The queen said her favourite book is Modern Java in Action");
	}
}
