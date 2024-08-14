package nemo.event_listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class MemberSignupListener {

	private final SignupMessageService signupMessageService;

	@EventListener
	public void handleMemberSignupEvent(MemberSignupEvent event) {
		log.info("MemberSignupListener.handleMemberSignupEvent, event = {}", event);
		signupMessageService.sendSignupMessage(event.getName());
	}
}
