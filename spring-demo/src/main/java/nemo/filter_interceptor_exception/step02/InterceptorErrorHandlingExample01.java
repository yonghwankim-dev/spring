package nemo.filter_interceptor_exception.step02;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@SpringBootApplication
public class InterceptorErrorHandlingExample01 implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
		WebClient webClient = WebClient.builder()
			.baseUrl("http://localhost:8080")
			.build();

		String response = webClient.get()
			.uri("/hello")
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
		SpringApplication.run(InterceptorErrorHandlingExample01.class, args);
	}
}
