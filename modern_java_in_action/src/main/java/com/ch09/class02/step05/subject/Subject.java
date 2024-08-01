package com.ch09.class02.step05.subject;

import com.ch09.class02.step05.observer.Observer;

public interface Subject {
	void registerObserver(Observer observer);
	void notifyObservers(String tweet);
}
