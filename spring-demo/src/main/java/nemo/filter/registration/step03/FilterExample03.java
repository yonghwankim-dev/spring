package nemo.filter.registration.step03;

import java.util.Map;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Spring Filter 구현 방법 : @WebFilter + @ServletComponentScan
 * - @WebFilter를 필터 클래스에 적용하고 설정 클래스(@Configration, @SpringBootApplication)에 @ServletComponentScan
 * 을 적용하여 웹 필터 애노테이션을 수행합니다.
 *
 */
@ServletComponentScan
@SpringBootApplication
public class FilterExample03 implements ApplicationRunner {

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
		SpringApplication.run(FilterExample03.class, args);
	}
}
