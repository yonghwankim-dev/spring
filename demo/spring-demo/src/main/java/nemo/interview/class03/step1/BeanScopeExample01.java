package nemo.interview.class03.step1;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Bean Scope : Singleton
 * - Spring Bean의 Scope가 싱글톤이면 Spring Container에 하나의 인스턴스로 유지된다
 */
public class BeanScopeExample01 {
	static class Car{
		private String name;

		public Car(String name) {
			this.name = name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	@Configuration
	static class AppConfig{

		@Bean
		@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
		public Car car(){
			return new Car("K3");
		}
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		Car car1 = ctx.getBean(Car.class);
		Car car2 = ctx.getBean(Car.class);

		car1.setName("SM3");
		System.out.println(car1.name.equals(car2.name));
		ctx.close();
	}
}
