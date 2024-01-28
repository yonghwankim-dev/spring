package nemo.filter.cors.step03;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Spring CORS Filter 구현 방법 : Filter 인터페이스 구현 + FilterRegistrationBean 스프링 빈 생성

 */
@SpringBootApplication
public class CorsExample03 implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
		WebClient webClient = WebClient.builder()
			.baseUrl("http://localhost:8080")
			.build();
		String response = webClient.get()
			.uri("/hello")
			.retrieve()
			.bodyToMono(String.class)
			.block();
		System.out.println(response);
		System.exit(0);
	}

	public static void main(String[] args) {
		SpringApplication.run(CorsExample03.class, args);
	}
}
