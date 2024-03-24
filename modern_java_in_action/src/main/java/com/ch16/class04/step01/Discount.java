package com.ch16.class04.step01;

public class Discount {
	public enum Code{
		NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

		private final int percentage;

		Code(int percentage) {
			this.percentage = percentage;
		}
	}

	public static String applyDiscount(Quote quote){
		return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
	}

	private static double apply(double price, Code discountCode) {
		delay();
		return format(price * (100 - discountCode.percentage) / 100);
	}

	private static double format(double v) {
		return Double.parseDouble(String.format("%.2f", v));
	}

	private static void delay() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
