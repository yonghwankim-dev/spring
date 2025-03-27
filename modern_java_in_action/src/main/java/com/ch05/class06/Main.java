package com.ch05.class06;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		String cambridge = "Cambridge";
		String milan = "Milan";
		Trader raoul = new Trader("Raoul", cambridge);
		Trader mario = new Trader("Mario", milan);
		Trader alan = new Trader("Alan", cambridge);
		Trader brian = new Trader("Brian", cambridge);

		List<Transaction> transactions = Arrays.asList(
			new Transaction(brian, 2011, 300),
			new Transaction(raoul, 2012, 1000),
			new Transaction(raoul, 2011, 400),
			new Transaction(mario, 2012, 710),
			new Transaction(mario, 2012, 700),
			new Transaction(alan, 2012, 950)
		);
		test1(transactions);
		test2(transactions);
		test3(transactions);
		test4(transactions);
		test5(transactions);
		test6(transactions);
		test7(transactions);
		test8(transactions);
	}

	// 2011년에 일어난 모든 틀랜잭션을 찾아서 값을 오름차순으로 정리

	private static void test1(List<Transaction> transactions) {
		List<Transaction> result = transactions.stream()
			.filter(t -> t.getYear() == 2011)
			.sorted(Comparator.comparingInt(Transaction::getValue))
			.collect(Collectors.toList());
		System.out.println("2011년에 일어난 모든 트랜잭션을 오름차순으로 정렬한 목록");
		result.forEach(System.out::println);
		System.out.println();
	}

	// 거래자가 근무하는 모든 도시를 중복 없이 나열하시오
	private static void test2(List<Transaction> transactions) {
		List<String> cities = transactions.stream()
			.map(Transaction::getTrader)
			.map(Trader::getCity)
			.distinct()
			.collect(Collectors.toList());
		System.out.println("거래자가 근무하는 모든 도시 목록 " + cities);
	}

	// 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬

	private static void test3(List<Transaction> transactions) {
		List<String> names = transactions.stream()
			.map(Transaction::getTrader)
			.filter(trader -> trader.getCity().equals("Cambridge"))
			.sorted(Comparator.comparing(Trader::getName))
			.map(Trader::getName)
			.distinct()
			.collect(Collectors.toList());
		System.out.println("케임브리지에서 근무하는 모든 거래자의 이름 목록 : " + names);
	}

	// 모든 거래자의 이름을 알파벳순으로 정렬해서 반환
	private static void test4(List<Transaction> transactions) {
		List<String> names = transactions.stream()
			.map(Transaction::getTrader)
			.sorted(Comparator.comparing(Trader::getName))
			.map(Trader::getName)
			.distinct()
			.collect(Collectors.toList());
		System.out.println("모든 거래자의 이름을 알파벳순으로 정렬한 목록 : " + names);
	}

	// 밀라노에 거래자가 있는지 여부 확인
	private static void test5(List<Transaction> transactions) {
		if (transactions.stream()
			.map(Transaction::getTrader)
			.anyMatch(trader -> trader.getCity().equals("Milan"))){
			System.out.println("밀라노에 거래자가 있습니다");
		}
	}

	// 케잌브리지에 거주하는 거래자의 모든 트랜잭션 값을 출력
	private static void test6(List<Transaction> transactions){
		System.out.println("케임브리지에 거주하는 거래자의 모든 트랜잭션 값 출력");
		transactions.stream()
			.filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
			.forEach(System.out::println);
		System.out.println();
	}

	// 전체 트랜잭션 중 최댓값은 얼마인지 계산
	private static void test7(List<Transaction> transactions) {
		Optional<Integer> max = transactions.stream()
			.map(Transaction::getValue)
			.reduce(Integer::max);
		System.out.println("전체 트랜잭션 중 최대값 : " + max.orElse(0));
	}

	// 전체 트랜잭션 중 최솟값은 얼마인지 계산
	private static void test8(List<Transaction> transactions){
		Optional<Integer> min = transactions.stream()
			.map(Transaction::getValue)
			.reduce(Integer::min);
		System.out.println("전체 트랜잭션 중 최솟값 : " + min.orElse(0));
	}
}

