package nemo.listener.transactional_event_listener;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsoleSignupMessageService implements SignupMessageService {

	private final MemberSignupMessageRepository memberSignupMessageRepository;

	@Override
	@Transactional
	public void sendSignupMessage(String name) {
		String message = String.format("%s, congratulations on your membership.", name);
		log.info("message is {}", message);
		memberSignupMessageRepository.save(message);
	}
}
