package nemo.event.step2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomSpringEventPublisher {
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	public void publishCustomEvent(final String message){
		log.info("Publishing custom event.");
		CustomSpringEvent customSpringEvent = new CustomSpringEvent(this, message);
		applicationEventPublisher.publishEvent(customSpringEvent);
	}
}
