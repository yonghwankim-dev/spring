package nemo.interview.class02.step2;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Bean 애노테이션의 initMethod, destroyMethod 옵션을 사용하여 초기화 및 소멸 메소드 설정하기
 * - 외부 라이브러리에도 설정이 가능합니다
 */
public class BeanLifeCycleExample02 {

	static class Car{
		private final String name;

		public Car(String name) {
			System.out.println("Car 스프링 빈, 의존성 주입");
			this.name = name;
		}

		public void close(){
			System.out.println("Car 스프링 빈, 소멸 메소드 콜백 수행");
		}

		public void init(){
			System.out.println("Car 스프링 빈, 초기화 메소드 콜백 수행");
		}
	}

	@Configuration
	static class AppConfig{
		@Bean(initMethod = "init", destroyMethod = "close")
		public Car car(){
			System.out.println("Car 객체의 스프링 빈 정보 정의 및 인스턴스화");
			return new Car("k3");
		}
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		Car car = ctx.getBean(Car.class);
		System.out.printf("Car 스프링 빈, 사용 name=%s%n", car.name);
		ctx.close();
	}
}
