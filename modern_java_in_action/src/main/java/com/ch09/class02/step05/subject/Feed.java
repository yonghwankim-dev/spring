package com.ch09.class02.step05.subject;

import java.util.ArrayList;
import java.util.List;

import com.ch09.class02.step05.observer.Observer;

public class Feed implements Subject {
	private final List<Observer> observers = new ArrayList<>();

	@Override
	public void registerObserver(Observer observer) {
		this.observers.add(observer);
	}

	@Override
	public void notifyObservers(String tweet) {
		observers.forEach(o->o.notify(tweet));
	}
}
