package nemo.listener.transactional_event_listener;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MemberSignupEvent {
	private final String name;
	private final boolean emailVerified;

	@Override
	public String toString() {
		return "Member Event, name is " + name;
	}
}
