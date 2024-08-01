package com.ch10.class03.step02;


public class TradeBuilder {
	private final MethodChainingOrderBuilder builder;
	private final Trade trade = new Trade();

	TradeBuilder(MethodChainingOrderBuilder builder, Trade.Type type, int quantity) {
		this.builder = builder;
		trade.setType(type);
		trade.setQuantity(quantity);
	}

	public StockBuilder stock(String symbol) {
		return new StockBuilder(builder, trade, symbol);
	}
}
