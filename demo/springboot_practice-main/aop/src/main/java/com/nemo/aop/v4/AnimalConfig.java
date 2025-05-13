package com.nemo.aop.v4;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnimalConfig {
	@Bean
	public ProxyFactoryBean cat() {
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		proxyFactoryBean.setTarget(new Cat());
		proxyFactoryBean.addAdvice(new UpperCaseAdvice());
		return proxyFactoryBean;
	}

	@Bean
	public ProxyFactoryBean dog() {
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		proxyFactoryBean.setTarget(new Dog());
		proxyFactoryBean.addAdvice(new UpperCaseAdvice());
		return proxyFactoryBean;
	}

	@Bean
	public AnimalService animalService() {
		return new AnimalService();
	}
}
