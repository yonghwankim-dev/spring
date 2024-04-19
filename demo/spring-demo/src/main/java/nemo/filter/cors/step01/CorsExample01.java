package nemo.filter.cors.step01;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.reactive.function.client.WebClient;

@ServletComponentScan
@SpringBootApplication
public class CorsExample01 implements ApplicationRunner {

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
		SpringApplication.run(CorsExample01.class, args);
	}
}
