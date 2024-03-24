package com.ch17.class02.step04;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Observable;

/**
 * 사용자가 한 도시만이 아닌 여러 도시에서 온도를 방출하는 Observable를 가질수 있도록함
 * Observable.merge 함수를 이용해서 하나로 합쳐서 출력합니다.
 */
public class Main {

	public static void main(String[] args) {
		Observable<TempInfo> observable = getCelsiusTemperature("New York", "Chicago", "San Francisco");
		observable.blockingSubscribe(new TempObserver());
	}

	public static Observable<TempInfo> getCelsiusTemperature(String... towns){
		return Observable.merge(
			Arrays.stream(towns)
			.map(TempObservable::getCelsiusTemperature)
				.collect(Collectors.toList())
		);
	}
}
