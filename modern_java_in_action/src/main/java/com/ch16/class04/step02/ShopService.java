package com.ch16.class04.step02;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * 동기 작업과 비동기 작업 조합하기
 */
public class ShopService {
	List<Shop> shops = Arrays.asList(
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

	public List<String> findPrices(String product){
		List<CompletableFuture<String>> futures = shops.stream()
			.map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
			.map(future -> future.thenApply(Quote::parse))
			.map(future ->
				future.thenCompose(quote ->
					CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)
				)
			)
			.collect(Collectors.toList());

		return futures.stream()
			.map(CompletableFuture::join)
			.collect(Collectors.toList());
	}

	public static void main(String[] args) {
		System.out.println("processor : " + Runtime.getRuntime().availableProcessors());
		ShopService shopService = new ShopService();
		long start = System.nanoTime();
		System.out.println(shopService.findPrices("myPhone27S"));
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in " + duration + " msecs");
	}
}
