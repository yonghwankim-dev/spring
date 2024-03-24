package com.ch17.class02.step01;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

/**
 * RxJava를 이용하여 Observable 생성하기
 * RxJava는 Flow 표준 인터페이스를 구현한 구현체 라이브러리
 * Observable.interval을 이용하여 특정 시간 간격으로 데이터를 방출할수 있음
 *
 */
public class Main {

	public static void main(String[] args) {
		Observable<TempInfo> observable = getTemperature("New York");
		observable.blockingSubscribe(new TempObserver());
	}

	public static Observable<TempInfo> getTemperature(String town){
		// Observable.create 메소드에 ObservableOnSubscribe를 전달한다
		// ObservableOnSubscribe 인터페이스는 Observer가 Oservable에 구독할때 호출되는 방출자(emitter)입니다.
		// 즉, 구독자가 생산자(publisher)에 구독하는 시점에 발생하는 콜백 함수입니다.

		// 아래 예제는 구독자가 생산자에게 구독하면 1초 간격으로 구독자에게 온도를 방출합니다.
		// 5초가 넘어가면 방출자(emitter)를 Complete하여 폐기합니다.
		// emitter를 complete하면 isDisposed 메소드가 true를 반환합니다.
		return Observable.create(emitter -> {
			// 매 초마다 무한으로 증가하는 일련의 long 값을 방출하는 Observerable
			Observable.interval(1, TimeUnit.SECONDS)
				.subscribe(i->{
					// 소비된 Observer(Subscriber)가 아직 폐기되지 않았으면 어떤 작업을 수행
					if (!emitter.isDisposed()){
						if (i >= 5){
							emitter.onComplete();
						}else{
							try{
								// 아니면 온도를 Observer로 보고
								emitter.onNext(TempInfo.fetch(town));
							}catch (Exception e){
								emitter.onError(e); // 에러가 발생하면 Observer에 알림
							}

						}
					}
				});
		});
	}
}
