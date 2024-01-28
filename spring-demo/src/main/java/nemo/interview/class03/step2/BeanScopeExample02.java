package nemo.interview.class03.step2;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Bean Scope : Prototype
 * - Bean Scope가 Prototype이면 Spring Container에 Spring Bean 요청시 마다 서로 다른 인스턴스를 반환한다
 */
public class BeanScopeExample02 {
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
		@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
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
