package nemo.listener.event_listener;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class TeamDeleteEvent extends ApplicationEvent {
	private final Long memberId;

	public TeamDeleteEvent(Long memberId) {
		super(memberId);
		this.memberId = memberId;
	}
}
