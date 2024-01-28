package nemo.filter.registration.step01;

import java.util.Map;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Spring Filter 구현 방법 : @Configuration + FilterRegistrationBean
 * 1. @Configuration 설정 클래스에 FilterRegistrationBean을 추가 등록하여 필터를 등록합니다.
 * 2. jakarta.servlet.Filter 인터페이스를 구현하는 구현체를 생성합니다.
 */
@SpringBootApplication
public class FilterExample01 implements ApplicationRunner {

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
		SpringApplication.run(FilterExample01.class);
	}
}
