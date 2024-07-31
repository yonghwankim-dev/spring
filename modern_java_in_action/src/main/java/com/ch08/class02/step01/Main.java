package com.ch08.class02.step01;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

public class Main {
	static class Transaction{
		private String referenceCode;

		public Transaction(String referenceCode) {
			this.referenceCode = referenceCode;
		}

		public String getReferenceCode() {
			return referenceCode;
		}
	}

	public static void main(String[] args) {
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(new Transaction("123"));
		transactions.add(new Transaction("234"));
		transactions.add(new Transaction("345"));

		// 반복자의 상태는 컬렉션의 상태와 서로 동기화하지 않기 때문에 예외 발생
		try{
			for (Transaction transaction : transactions){
				if (Character.isDigit(transaction.getReferenceCode().charAt(0))){
					transactions.remove(transaction);
				}
			}
		}catch (ConcurrentModificationException e){
			System.out.println("Don't modify list while iterating");
		}

		for (Iterator<Transaction> iterator = transactions.iterator(); iterator.hasNext();){
			Transaction transaction = iterator.next();
			if (Character.isDigit(transaction.getReferenceCode().charAt(0))){
				iterator.remove();
			}
		}
		System.out.println(transactions);

		transactions = new ArrayList<>();
		transactions.add(new Transaction("123"));
		transactions.add(new Transaction("234"));
		transactions.add(new Transaction("345"));
		transactions.removeIf(transaction -> Character.isDigit(transaction.getReferenceCode().charAt(0)));
		System.out.println(transactions);
	}

}
