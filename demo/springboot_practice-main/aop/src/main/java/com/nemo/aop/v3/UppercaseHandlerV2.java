package com.nemo.aop.v3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UppercaseHandlerV2 implements InvocationHandler {
	private final Object target;

	public UppercaseHandlerV2(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result;
		try {
			result = method.invoke(target, args);
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		}

		if (result instanceof String text && method.getName().startsWith("say")) {
			return text.toUpperCase();
		}
		return result;
	}
}
