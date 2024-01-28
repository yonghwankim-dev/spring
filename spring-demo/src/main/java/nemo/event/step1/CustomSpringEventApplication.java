package nemo.event.step1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomSpringEventApplication implements ApplicationRunner {

	@Autowired
	private CustomSpringEventPublisher publisher;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		publisher.publishCustomEvent("hello event");
		System.exit(0);
	}

	public static void main(String[] args) {
		SpringApplication.run(CustomSpringEventApplication.class, args);
	}
}
