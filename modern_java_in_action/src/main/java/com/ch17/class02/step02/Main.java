package com.ch17.class02.step02;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

/**
 * Observable.map을 이용하여 화씨 온도를 섭시 온도로 매핑하여 출력합니다.
 */
public class Main {

	public static void main(String[] args) {
		Observable<TempInfo> observable = getCelsiusTemperature("New York");
		observable.blockingSubscribe(new TempObserver());
	}

	public static Observable<TempInfo> getCelsiusTemperature(String town){
		return getTemperature(town)
			.map(temp->new TempInfo(temp.getTown(), (temp.getTemp() - 32) * 5 / 9));
	}

	public static Observable<TempInfo> getTemperature(String town){
		return Observable.create(emitter -> {
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
