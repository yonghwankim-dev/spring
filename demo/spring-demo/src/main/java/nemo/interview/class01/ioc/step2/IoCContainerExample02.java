package nemo.interview.class01.ioc.step2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 빈 생성
 * - 애노테이션 설정 기반으로 스프링 컨테이너에 빈 생성하고 등록하기
 */
public class IoCContainerExample02 {
	@Configuration
	static class AppConfig {

		@Bean
		public Item item1(){
			return new ItemImpl("JPA");
		}

		@Bean
		public Store store(){
			return new Store(item1());
		}
	}

	interface Item {
		String sayName();
	}

	@RequiredArgsConstructor
	static class ItemImpl implements Item {

		private final String name;

		@Override
		public String sayName() {
			return name;
		}
	}

	@Getter
	@RequiredArgsConstructor
	static class Store {
		private final Item item;
	}

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		Store store = ctx.getBean(Store.class);
		System.out.println(store.getItem().sayName());
	}
}
