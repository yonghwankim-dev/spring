package com.ch16.class03.step01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ShopService {
	List<Shop> shops = Arrays.asList(
		new Shop("Bestprice"),
		new Shop("letSaveBig"),
		new Shop("MyFavoriteSho"),
		new Shop("BuyItAll")
	);

	public List<String> findPrices(String product){
		return shops.stream()
			.map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
			.collect(Collectors.toList());
	}

	public static void main(String[] args) {
		ShopService shopService = new ShopService();
		long start = System.nanoTime();
		System.out.println(shopService.findPrices("myPhone27S"));
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in " + duration + " msecs");
	}
}
