package nemo.interview.class02.step1;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * InitializingBean, DisposableBean 인터페이스를 이용하여 Bean의 초기화 및 소멸 메소드 정의하기
 * - 코드를 수정할 수 없는 외부 라이브러리를 대상으로는 설정할 수 없습니다
 */
public class BeanLifeCycleExample01 {

	static class Car implements InitializingBean, DisposableBean {
		private final String name;

		public Car(String name) {
			System.out.println("Car 스프링 빈, 의존성 주입");
			this.name = name;
		}

		@Override
		public void destroy() throws Exception {
			System.out.println("Car 스프링 빈, 소멸 메소드 콜백 수행");
		}

		@Override
		public void afterPropertiesSet() throws Exception {
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
