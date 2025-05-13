package com.nemo.aop.v4;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

class AnimalIntegrationTest {

	@DisplayName("Spring Boot를 사용하여 Cat 클래스의 saySound 메서드를 호출하면 대문자로 변환된 결과를 반환한다")
	@Test
	void saySound_withSpringBoot() {
		// given
		SpringApplication application = new SpringApplication(AnimalConfig.class);
		ConfigurableApplicationContext context = application.run();
		Animal cat = context.getBean("cat", Animal.class);
		Animal dog = context.getBean("dog", Animal.class);
		// when
		String catSound = cat.saySound();
		String dogSound = dog.saySound();
		// then
		Assertions.assertThat(catSound).isEqualTo("MEOW");
		Assertions.assertThat(dogSound).isEqualTo("BOW");
	}

	@DisplayName("Spring Boot를 사용하여 AnimalService의 getAnimalSound 메서드를 호출하면 대문자로 변환된 결과를 반환한다")
	@Test
	void getAnimalSound_withSpringBoot() {
		// given
		SpringApplication application = new SpringApplication(AnimalConfig.class);
		ConfigurableApplicationContext context = application.run();
		AnimalService service = context.getBean(AnimalService.class);
		Animal cat = context.getBean("cat", Animal.class);
		// when
		String actual = service.getAnimalSound(cat);
		// then
		Assertions.assertThat(actual).isEqualTo("MEOW");
	}

}
