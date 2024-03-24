package com.ch16.class03.step04;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * CompletableFuture로 비동기 호출 구현하기
 * CompletableFuture.supplyAsync를 사용하여 비동기 호출로 변경
 *
 * 프로세서 수가 8개이기 때문에 한번의 8개의 상점씩 처리함(1초 소모)
 * 12개를 처리하기 위해서 총 2초가 소요됨
 * 이는 프로세서수가 8개이기 때문이다.
 *
 * 위와 같은 문제를 해결하기 위해서는 쓰레드풀을 이용하여 해결해야함
 */
public class ShopService {
	List<Shop> shops = Arrays.asList(
		new Shop("Bestprice"),
		new Shop("letSaveBig"),
		new Shop("MyFavoriteSho"),
		new Shop("BuyItAll"),
		new Shop("Bestprice"),
		new Shop("letSaveBig"),
		new Shop("MyFavoriteSho"),
		new Shop("BuyItAll"),
		new Shop("Bestprice"),
		new Shop("letSaveBig"),
		new Shop("MyFavoriteSho"),
		new Shop("BuyItAll")
	);

	public List<String> findPrices(String product){
		List<CompletableFuture<String>> futures = shops.stream()
			.map(shop -> CompletableFuture.supplyAsync(
				() -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))))
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
