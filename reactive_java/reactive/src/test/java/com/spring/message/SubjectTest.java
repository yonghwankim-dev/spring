package com.spring.message;

import static org.mockito.Mockito.*;

import java.util.Observable;

import org.junit.jupiter.api.Test;

import com.spring.message.ConcreteObserverA;
import com.spring.message.ConcreteObserverB;
import com.spring.message.ConcreteSubject;
import com.spring.message.Observer;
import com.spring.message.Subject;

class SubjectTest {

	@Test
	void observersHandleEventsFromSubject(){
	    // given
		Subject<String> subject = new ConcreteSubject();
		Observer<String> observerA = spy(new ConcreteObserverA());
		Observer<String> observerB = spy(new ConcreteObserverB());

		// when
		subject.notifyObservers("No listeners");

		subject.registerObserver(observerA);
		subject.notifyObservers("Message for A");

		subject.registerObserver(observerB);
		subject.notifyObservers("Message for A & B");

		subject.unregisterObserver(observerA);
		subject.notifyObservers("Message for B");

		subject.unregisterObserver(observerB);
		subject.notifyObservers("No listeners");
	    // then
		verify(observerA, times(1)).observe("Message for A");
		verify(observerA, times(1)).observe("Message for A & B");
		verifyNoMoreInteractions(observerA);

		verify(observerB, times(1)).observe("Message for A & B");
		verify(observerB, times(1)).observe("Message for B");
		verifyNoMoreInteractions(observerB);
	}
}
