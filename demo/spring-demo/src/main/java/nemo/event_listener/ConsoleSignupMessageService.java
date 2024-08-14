package nemo.event_listener;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConsoleSignupMessageService implements SignupMessageService {
	@Override
	public void sendSignupMessage(String name) {
		log.info("{}, congratulations on your membership.", name);
	}
}
