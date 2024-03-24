package com.ch16.class04.step03;

import static com.ch16.class04.step03.ExchangeService.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * 독립 CompletableFuture와 비독립 CompletableFuture 합치기
 * thenCombine 합치기
 * 1. 가격을 구하는 메소드
 * 2. 환율을 구하는 메소드
 * 1,2의 결과를 합쳐서 환율을 적용한 가격 구하기
 */
public class ShopService {
	private List<Shop> shops = Arrays.asList(
		new Shop("Bestprice"),
		new Shop("letSaveBig"),
		new Shop("MyFavoriteSho"),
		new Shop("BuyItAll"),
		new Shop("letSale")
	);

	private final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), r -> {
		Thread t = new Thread(r);
		t.setDaemon(true); // 프로그램 종료를 방해하지 않는 데몬 스레드를 사용한다
		return t;
	});
	private final ExchangeService exchangeService;

	public ShopService() {
		this.exchangeService = new ExchangeService();
	}

	public Future<Double> findPriceInUSD(Shop shop, String product){
		return CompletableFuture.supplyAsync(() -> shop.getPrice(product))
			.thenCombine(
				CompletableFuture.supplyAsync(() -> exchangeService.getRate(Money.EUR, Money.USD)),
				(price, rate) -> price * rate);
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
