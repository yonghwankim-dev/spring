package com.ch16.class03.step02;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 병렬 스트림으로 요청 병렬화하기
 * stream -> parallelStream 변경
 */
public class ShopService {
	List<Shop> shops = Arrays.asList(
		new Shop("Bestprice"),
		new Shop("letSaveBig"),
		new Shop("MyFavoriteSho"),
		new Shop("BuyItAll")
	);

	public List<String> findPrices(String product){
		// stream -> parallelStream으로 변경하여 요청을 병렬화하기
		return shops.parallelStream()
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
