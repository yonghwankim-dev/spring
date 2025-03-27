package nemo.listener.event_listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class TeamDeleteListener {

	private final TeamMessageService teamMessageService;

	@Async
	@EventListener
	public void handleTeamDeleteEvent(TeamDeleteEvent event) {
		teamMessageService.sendDeleteMessage(event);
	}
}
