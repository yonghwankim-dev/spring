package com.nemo.aop.v3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Objects;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

class HelloTest {

	@DisplayName("Hello 클래스의 sayHello 메서드를 호출하면 대문자로 변환된 결과를 반환한다")
	@Test
	void sayHello() {
		// given
		var classes = new Class[] {Hello.class};
		InvocationHandler uppercaseHandler = new UppercaseHandler(new HelloTarget());
		Hello proxiedHello = (Hello)Proxy.newProxyInstance(getClass().getClassLoader(),
			classes, uppercaseHandler);
		// when
		String actual = proxiedHello.sayHello("Dave");
		// then
		Assertions.assertThat(actual).hasToString("HELLO DAVE");
	}

	@Test
	void sayHello2() {
		// given
		var classes = new Class[] {Hello.class};
		HelloTarget helloTarget1 = new HelloTarget();
		HelloTarget2 helloTarget2 = new HelloTarget2();
		InvocationHandler uppercaseHandler = new UppercaseHandler(helloTarget1);
		Hello proxy1 = (Hello)Proxy.newProxyInstance(getClass().getClassLoader(),
			classes, uppercaseHandler);
		Hello proxy2 = (Hello)Proxy.newProxyInstance(getClass().getClassLoader(),
			classes, uppercaseHandler);
		// when
		String actual = proxy1.sayHello("Dave");
		String actual2 = proxy2.sayHello("Dave");
		// then
		Assertions.assertThat(actual).hasToString("HELLO DAVE");
		Assertions.assertThat(actual2).hasToString("HELLO DAVE");
	}

	@DisplayName("Hello 클래스의 sayHi 메서드를 호출하면 대문자로 변환된 결과를 반환한다")
	@Test
	void sayHello_whenStartsWithSayMethod_thenReturnUppercaseText() {
		// given
		var classes = new Class[] {Hello.class};
		InvocationHandler uppercaseHandler = new UppercaseHandlerV2(new HelloTarget());
		Hello proxiedHello = (Hello)Proxy.newProxyInstance(getClass().getClassLoader(),
			classes, uppercaseHandler);
		// when
		String actual = proxiedHello.sayHello("Dave");
		// then
		Assertions.assertThat(actual).hasToString("HELLO DAVE");
	}

	@DisplayName("ProxyFactoryBean을 사용하여 Hello 클래스의 sayHello 메서드를 호출하면 대문자로 변환된 결과를 반환한다")
	@Test
	void sayHello_withProxyFactoryBean() {
		// given
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		proxyFactoryBean.setTarget(new HelloTarget());
		proxyFactoryBean.addAdvice(new UpperCaseAdvice());
		// when
		Hello proxiedHello = (Hello)proxyFactoryBean.getObject();
		String actual = Objects.requireNonNull(proxiedHello).sayHello("Dave");
		// then
		Assertions.assertThat(actual).hasToString("HELLO DAVE");
	}

	@DisplayName("ProxyFactoryBean을 사용하여 Hello 클래스의 sayHi 메서드를 호출하면 대문자로 변환된 결과를 반환한다")
	@Test
	void sayHello_withProxyFactoryBean_whenStartsWithSayMethod_thenReturnUppercaseText() {
		// given
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		proxyFactoryBean.setTarget(new HelloTarget());

		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.setMappedName("sayH*");
		proxyFactoryBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UpperCaseAdvice()));
		Hello proxiedHello = (Hello)proxyFactoryBean.getObject();
		// when
		String actual1 = Objects.requireNonNull(proxiedHello).sayHello("Dave");
		// then
		Assertions.assertThat(actual1).hasToString("HELLO DAVE");
	}

}
