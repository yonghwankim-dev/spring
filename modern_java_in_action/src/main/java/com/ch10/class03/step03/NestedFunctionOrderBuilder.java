package com.ch10.class03.step03;

import java.sql.BatchUpdateException;
import java.util.stream.Stream;

public class NestedFunctionOrderBuilder {
	public static Order order(String customer, Trade... trades){
		Order order = new Order();
		order.setCustomer(customer);
		Stream.of(trades).forEach(order::addTrade);
		return order;
	}

	public static Trade buy(int quantity, Stock stock, double price){
		return buildTrade(quantity, stock, price, Trade.Type.BUY);
	}

	private static Trade buildTrade(int quantity, Stock stock, double price, Trade.Type type) {
		Trade trade = new Trade();
		trade.setQuantity(quantity);
		trade.setStock(stock);
		trade.setPrice(price);
		trade.setType(type);
		return trade;
	}

	// 거래된 주식의 단가를 정의하는 더미 메서드
	public static double at(double price){
		return price;
	}

	public static Stock stock(String symbol, String market){
		Stock stock = new Stock();
		stock.setSymbol(symbol);
		stock.setMarket(market);
		return stock;
	}

	public static String on(String market){
		return market;
	}

	public static void main(String[] args) {
		Order order =
				order("BigCustomer",
						buy(100, stock("SYMBOL", on("NASDAQ")), at(40.0)),
						buy(50, stock("OTHER", on("NYSE")), at(100.0))
				);
		System.out.println(order);
	}
}
