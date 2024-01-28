package nemo.event.step2;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomSpringEventListener implements ApplicationListener<CustomSpringEvent> {
	@Override
	public void onApplicationEvent(CustomSpringEvent event) {
		log.info("Received spring custom event - {}", event.getMessage());
	}
}
