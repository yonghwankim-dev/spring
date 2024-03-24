package com.ch17.class02.step04;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

public class TempObservable {
	public static Observable<TempInfo> getCelsiusTemperature(String town){
		return getTemperature(town)
			.map(temp->new TempInfo(temp.getTown(), (temp.getTemp() - 32) * 5 / 9));
	}

	public static Observable<TempInfo> getTemperature(String town){
		return Observable.create(emitter -> {
			Observable.interval(1, TimeUnit.SECONDS)
				.subscribe(i->{
					if (!emitter.isDisposed()){
						if (i >= 5){
							emitter.onComplete();
						}else{
							try{
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
