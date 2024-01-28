package nemo.filter_interceptor_exception.step01;

import java.util.Map;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

/**
 * Filter 에러 핸들링 방법
 * Filter는 Web Context에 존재하기 때문에 별도의 ErrorController 필요함
 */
@ServletComponentScan
@SpringBootApplication
public class FilterErrorHandleExample01 implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
		WebClient webClient = WebClient.builder()
			.baseUrl("http://localhost:8080")
			.build();

		String response = webClient.get()
			.uri("/filter/hello")
			.retrieve()
			.onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
				clientResponse.bodyToMono(String.class)
					.flatMap(errorBody -> Mono.error(new RuntimeException(errorBody)))
			)
			.bodyToMono(String.class)
			.onErrorResume(throwable -> Mono.just(throwable.getMessage()))
			.block();
		System.out.println(response);
		System.exit(0);
	}

	public static void main(String[] args) {
		SpringApplication.run(FilterErrorHandleExample01.class, args);
	}
}
