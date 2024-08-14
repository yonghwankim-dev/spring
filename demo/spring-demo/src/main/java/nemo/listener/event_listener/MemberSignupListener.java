package nemo.listener.event_listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class MemberSignupListener {

	private final SignupMessageService signupMessageService;

	@Async
	@TransactionalEventListener
	public void handleMemberSignupEvent(MemberSignupEvent event) {
		log.info("MemberSignupListener.handleMemberSignupEvent, event = {}", event);
		signupMessageService.sendSignupMessage(event.getName());
	}
}
