package com.nemo.aop.v5;

import java.util.Objects;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

class HelloTest {

	@Test
	void should_returnUppercaseHello_whenAddUpperCaseAdviceToProxyFactoryBean() {
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

	@Test
	void should_returnUppercaseHello_whenAddUpperCaseAdviceToProxyFactoryBeanWithPointcut() {
		// given
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		proxyFactoryBean.setTarget(new HelloTarget());
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.setMappedName("sayH*");
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new UpperCaseAdvice());

		proxyFactoryBean.addAdvisor(advisor);
		// when
		Hello proxyHello = (Hello)proxyFactoryBean.getObject();
		String actual1 = Objects.requireNonNull(proxyHello).sayHello("Dave");
		String actual2 = proxyHello.sayHi("Dave");
		String actual3 = proxyHello.sayThankYou("Dave");
		// then
		Assertions.assertThat(actual1).hasToString("HELLO DAVE");
		Assertions.assertThat(actual2).hasToString("HI DAVE");
		Assertions.assertThat(actual3).hasToString("Thank You Dave");
	}
}
