package nemo.filter.registration.step04;

import java.util.Map;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Spring Filter 주의점 : @WebFilter + @Component + @ServletComponentScan
 * - @WebFilter + @Component 조합에 @ServletComponentScan을 적용하면 안된다
 *
 * @WebFilter + @Component + @ServletComponentScan 조합 문제점
 * - @ComponentScan과 @ServletComponentScan으로 인해서 필터가 두번 초기화됩니다.
 * - @COmponentScan과 @Component로 인해서 등록된 필터 클래스는 특정 URL 패턴이 적용되지 않음 (전체 URL에 적용됨)
 *
 * 정리하면 @WebFilter + @Component + @ServletComponentScan을 적용하면 필터가 두번 적용되고,
 * @Component로 적용된 필터는 URL 패턴이 적용되지 않습니다.
 *
 * 위 문제를 해결하기 위한 방법은?
 * - @WebFilter + @ServletComponentScan 조합 사용
 * - @Configuration + FilterRegistrationBean 방법 사용
 * - 조건부적으로 모든 URL 패턴에 사용해야 한다면 @Component + @ComponentScan 사용 (비권장)
 */
@ServletComponentScan
@SpringBootApplication
public class FilterExample04 implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
		WebClient webClient = WebClient.builder()
			.baseUrl("http://localhost:8080")
			.build();
		// filter가 적용되지 않아야 함에도 필터가 적용되는 문제점 발생
		Map<String, Object> bodyMap = webClient.get()
			.uri(uriBuilder -> uriBuilder
				.path("/noFilterData")
				.queryParam("name", "김용환")
				.queryParam("age", 20)
				.build())
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
			})
			.block();
		System.out.println(bodyMap);

		Map<String, Object> bodyMap2 = webClient.get()
			.uri(uriBuilder -> uriBuilder
				.path("/filterData")
				.queryParam("name", "김용환")
				.queryParam("age", 20)
				.build())
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
			})
			.block();
		System.out.println(bodyMap2);
		System.exit(0);
	}

	public static void main(String[] args) {
		SpringApplication.run(FilterExample04.class, args);
	}
}
