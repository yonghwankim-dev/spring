package com.nemo.aop.v3;

public class HelloTarget2 implements Hello {
	@Override
	public String sayHello(String name) {
		return "Hello2 " + name;
	}

	@Override
	public String sayHi(String name) {
		return "Hi2 " + name;
	}

	@Override
	public String sayThankYou(String name) {
		return "Thank You2 " + name;
	}
}
