package com.nemo.aop.hello;

public class HelloUppercase implements Hello {
	private final Hello delegate;

	public HelloUppercase(Hello delegate) {
		this.delegate = delegate;
	}

	@Override
	public String sayHello(String name) {
		return delegate.sayHello(name).toUpperCase();
	}

	@Override
	public String sayHi(String name) {
		return delegate.sayHi(name).toUpperCase();
	}

	@Override
	public String sayThankYou(String name) {
		return delegate.sayThankYou(name).toUpperCase();
	}
}
