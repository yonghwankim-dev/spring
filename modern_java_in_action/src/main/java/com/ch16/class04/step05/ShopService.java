package com.ch16.class04.step05;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 타임아웃 효과적으로 사용하기
 * - orTimeout을 이용해서 타임아웃 경과시 TimeoutException 발생하도록 함
 * - completeOnTimeout 이용해서 타임아웃 경과시 기본값 제공
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
		return CompletableFuture.supplyAsync(()->shop.getPrice(product))
			.thenCombine(
				CompletableFuture.supplyAsync(()-> exchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD))
					// 1. 환전 서비스가 일 초안에 결과를 제공하지 않으면 기본 환율값 사용
					.completeOnTimeout(ExchangeService.DEFAULT_RATE, 1, TimeUnit.SECONDS),
				(price, rate)-> price * rate
			)
			// 2. 3초안에 서비스가 완료되지 않으면 TimeoutException 발생
			.orTimeout(3, TimeUnit.SECONDS);
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
