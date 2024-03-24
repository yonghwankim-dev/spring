package com.ch16.class04.step01;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


/**
 * 비동기 작업 파이프 라인 만들기 : 할인 서비스 구현
 */
public class ShopService {
	List<Shop> shops = Arrays.asList(
		new Shop("Bestprice"),
		new Shop("letSaveBig"),
		new Shop("MyFavoriteSho"),
		new Shop("BuyItAll"),
		new Shop("letSale")
	);

	public List<String> findPrices(String product){
		return shops.stream()
			.map(shop -> shop.getPrice(product))
			.map(Quote::parse)
			.map(Discount::applyDiscount)
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
