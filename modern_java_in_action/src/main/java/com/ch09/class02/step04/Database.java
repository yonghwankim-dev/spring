package com.ch09.class02.step04;

public class Database {
	public static Customer getCustomerWithId(int id) {
		return new Customer("홍길동", id);
	}
}
