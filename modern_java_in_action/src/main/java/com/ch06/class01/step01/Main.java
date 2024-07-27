package com.ch06.class01.step01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

	static class Currency{
		private String name;
		private int amount;

		public Currency(String name, int amount) {
			this.name = name;
			this.amount = amount;
		}

		@Override
		public String toString() {
			return "Currency{" +
				"name='" + name + '\'' +
				", amount=" + amount +
				'}';
		}
	}

	static class Transaction{
		private Currency currency;

		public Transaction(Currency currency) {
			this.currency = currency;
		}

		public Currency getCurrency() {
			return currency;
		}

		@Override
		public String toString() {
			return "Transaction{" +
				"currency=" + currency +
				'}';
		}
	}

	public static void main(String[] args) {
		Transaction t1 = new Transaction(new Currency("USD", 1));
		Transaction t2 = new Transaction(new Currency("KRW", 1000));
		List<Transaction> transactions = List.of(t1, t2);
		Map<Currency, List<Transaction>> transactionsByCurrencies = getTransactionsByCurrencies(transactions);
		System.out.println(transactionsByCurrencies);

		transactionsByCurrencies = transactions.stream()
			.collect(Collectors.groupingBy(Transaction::getCurrency));
		System.out.println(transactionsByCurrencies);
	}

	private static Map<Currency, List<Transaction>> getTransactionsByCurrencies(List<Transaction> transactions) {
		Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();

		for (Transaction transaction : transactions){
			Currency currency = transaction.getCurrency();

			List<Transaction> transactionForCurrency = transactionsByCurrencies.get(currency);
			if (transactionForCurrency == null){
				transactionForCurrency = new ArrayList<>();
				transactionsByCurrencies.put(currency, transactionForCurrency);
			}
			transactionForCurrency.add(transaction);
		}
		return transactionsByCurrencies;
	}
}
