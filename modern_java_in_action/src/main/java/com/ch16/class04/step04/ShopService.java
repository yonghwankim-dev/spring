package com.ch16.class04.step04;

import static com.ch16.class04.step03.ExchangeService.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Future의 리플렉션과 CompletableFuture의 리플렉션
 * class04/step03에서 환율을 적용한 가격을 구하는 thenCombine 예제(자바 8로 구현)를
 * 자바 7으로 구현하면서 CompletableFuture를 이용했을 때 얻을 수 있는 코드 가독성 이점이 무엇인지 확인한다
 *
 * 자바 7으로 구현한 것을 자바8 thenCombine 대신 thenCombineAsync로 사용하면 동일한 효과를 낸다.
 * 즉, 자바 7으로 두 Future를 따로따로 작성하지 않고 thenCombineAsync를 사용하면 한번에 작성이 가능하다.
 *
 *
 */
public class ShopService {
	
	private List<Shop> shops = Arrays.asList(
		new Shop("Bestprice"),
		new Shop("letSaveBig"),
		new Shop("MyFavoriteSho"),
		new Shop("BuyItAll"),
		new Shop("letSale")
	);

	private final ExchangeService exchangeService;
	private final ExecutorService executor = Executors.newCachedThreadPool();


	public ShopService() {
		this.exchangeService = new ExchangeService();
	}

	public Future<Double> findPriceInUSD(Shop shop, String product){
		Future<Double> futureRate = executor.submit(new Callable<Double>() {
			@Override
			public Double call() throws Exception {
				return exchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD);
			}
		});
		return executor.submit(new Callable<Double>() {
			@Override
			public Double call() throws Exception {
				double priceInEUR = shop.getPrice(product);
				return priceInEUR * futureRate.get();
			}
		});
	}

	public static void main(String[] args) {
		System.out.println("processor : " + Runtime.getRuntime().availableProcessors());
		ShopService shopService = new ShopService();
		long start = System.nanoTime();
		try {
			System.out.println(shopService.findPriceInUSD(shopService.shops.get(0), "myPhone27S").get());
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in " + duration + " msecs");
	}
}
