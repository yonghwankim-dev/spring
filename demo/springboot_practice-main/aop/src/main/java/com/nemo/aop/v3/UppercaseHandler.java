package com.nemo.aop.v3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UppercaseHandler implements InvocationHandler {
	private final Object target;

	public UppercaseHandler(Object target) {
		this.target = target;
	}

	@Override
	public java.lang.Object invoke(java.lang.Object proxy, Method method, java.lang.Object[] args) throws Throwable {
		java.lang.Object result = method.invoke(target, args);
		if (result instanceof String text) {
			return text.toUpperCase();
		}
		return result;
	}
}
