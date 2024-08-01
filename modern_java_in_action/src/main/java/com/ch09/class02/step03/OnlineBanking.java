package com.ch09.class02.step03;

public abstract class OnlineBanking {
	public void processCustomer(int id){
		Customer c = Database.getCustomerWithId(id);
		makeCustomerHappy(c);
	}

	abstract void makeCustomerHappy(Customer c);
}
