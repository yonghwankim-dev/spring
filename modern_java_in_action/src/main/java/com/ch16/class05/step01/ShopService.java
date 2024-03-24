package com.ch16.class05.step01;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;


/**
 * 최저 가격 검색 애플리케이션 리팩토링
 * 모든 상점에서 가격정보를 제공할때까지 기다리지 않고 각 상점에서 가격 정보를 제공할때마다 즉시 보여줄수 있는
 * 최저가격 검색 애플리케이션을 구현한다
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

	public Stream<CompletableFuture<String>> findPricesStream(String product){
		return shops.stream()
			.map(shop -> CompletableFuture.supplyAsync(()->shop.getPrice(product), executor))
			.map(future->future.thenApply(Quote::parse))
			.map(future->future.thenCompose(quote ->
				CompletableFuture.supplyAsync(()->
					Discount.applyDiscount(quote), executor)));
	}

	public static void main(String[] args) {
		System.out.println("processor : " + Runtime.getRuntime().availableProcessors());
		ShopService shopService = new ShopService();
		long start = System.nanoTime();

		CompletableFuture[] futures = shopService.findPricesStream("myPhone27S")
			.map(f -> f.thenAccept(s-> System.out.printf("%s (done in %d msecs)%n", s, ((System.nanoTime() - start) / 1_000_000))))
			.toArray(CompletableFuture[]::new);
		CompletableFuture.allOf(futures).join();
		System.out.println("All shops have now responsed in " + ((System.nanoTime() - start) / 1_000_000) + " msecs");
	}
}
