package com.nemo.aop.v5;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

class UpperCaseAdvice implements MethodInterceptor {
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object result = invocation.proceed();
		if (result instanceof String text) {
			return text.toUpperCase();
		}
		return result;
	}
}
