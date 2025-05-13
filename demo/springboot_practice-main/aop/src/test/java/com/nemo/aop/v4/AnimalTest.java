package com.nemo.aop.v4;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactoryBean;

class AnimalTest {

	@DisplayName("ProxyFactoryBean을 사용하여 Cat 클래스의 saySound 메서드를 호출하면 대문자로 변환된 결과를 반환한다")
	@Test
	void saySound_withProxyFactoryBean() {
		// given
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		proxyFactoryBean.setTarget(new Cat());
		proxyFactoryBean.addAdvice(new UpperCaseAdvice());
		Animal proxiedAnimal = (Animal)proxyFactoryBean.getObject();
		// when
		String actual = proxiedAnimal.saySound();
		// then
		Assertions.assertThat(actual).hasToString("MEOW");
	}
}
