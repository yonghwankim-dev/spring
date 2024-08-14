package nemo.listener.transactional_event_listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class EventListenerDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(EventListenerDemoApplication.class, args);
	}
}
