package com.nemo.aop.hello;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UppercaseHandler implements InvocationHandler {
	private final Hello target;

	public UppercaseHandler(Hello target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = method.invoke(target, args);
		if (result instanceof String text) {
			return text.toUpperCase();
		}
		return result;
	}
}
