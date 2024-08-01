package com.ch09.class02.step04;

import java.util.function.Consumer;

public class OnlineBanking {
	public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
		Customer c = Database.getCustomerWithId(id);
		makeCustomerHappy.accept(c);
	}

	public static void main(String[] args) {
		OnlineBanking banking = new OnlineBanking();
		banking.processCustomer(1, c -> System.out.println("Hello " + c.getName()));
	}
}
