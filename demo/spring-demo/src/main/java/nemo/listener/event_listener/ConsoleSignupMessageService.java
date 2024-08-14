package nemo.listener.event_listener;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConsoleSignupMessageService implements SignupMessageService {
	@Override
	@Transactional
	public void sendSignupMessage(String name) {
		log.info("{}, congratulations on your membership.", name);
	}
}
