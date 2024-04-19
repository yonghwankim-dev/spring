package nemo.filter.registration.step02;

import java.util.Map;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Spring Filter 구현 방법 : @Component
 * 1. jakarta.servlet.Filter 인터페이스 구현체에 @Component 적용
 *
 * Filter 구현체에 @Component 적용시 장단점
 * - 장점 : 별도의 스프링 빈 설정이 필요없음
 * - 단점 : URL 패턴을 지정할 수 없어서 모든 URL 패턴에 매핑됨
 */
@SpringBootApplication
public class FilterExample02 implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
		WebClient webClient = WebClient.builder()
			.baseUrl("http://localhost:8080")
			.build();

		Map<String, Object> bodyMap = webClient.get()
			.uri(uriBuilder -> uriBuilder
				.path("/filterData")
				.queryParam("name", "김용환")
				.queryParam("age", 20)
				.build())
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
			})
			.block();
		System.out.println(bodyMap);
		System.exit(0);
	}

	public static void main(String[] args) {
		SpringApplication.run(FilterExample02.class, args);
	}
}
