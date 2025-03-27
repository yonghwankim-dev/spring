package com.ch10.class03.step04;

public class Main {
	public static void main(String[] args) {
		Order order = LambdaOrderBuilder.order(o -> {
			o.forCustomer("BigBank");
			o.buy(t -> {
				t.quantity(80);
				t.price(125.00);
				t.stock(s -> {
					s.symbol("IBM");
					s.market("NYSE");
				});
			});
			o.sell(t -> {
				t.quantity(50);
				t.price(150.00);
				t.stock(s -> {
					s.symbol("GOOG");
					s.market("NASDAQ");
				});
			});
		});
		System.out.println(order);
	}
}
