package nemo.interview.class02.step3;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

/**
 * Spring Bean 객체에 @PostConstruct, @PreDestroy 애노테이션을 사용하여 초기화 및 소멸 메소드 설정하기
 * - 코드를 수정할 수 없는 외부 라이브러리를 대상을 설정할 수 없습니다.
 */
public class BeanLifeCycleExample03 {

	static class Car{
		private final String name;

		public Car(String name) {
			System.out.println("Car 스프링 빈, 의존성 주입");
			this.name = name;
		}

		@PreDestroy
		public void close(){
			System.out.println("Car 스프링 빈, 소멸 메소드 콜백 수행");
		}

		@PostConstruct
		public void init(){
			System.out.println("Car 스프링 빈, 초기화 메소드 콜백 수행");
		}
	}

	@Configuration
	static class AppConfig{
		@Bean
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
