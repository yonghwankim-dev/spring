package nemo.event_listener;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MemberSignupEvent {
	private final String name;

	@Override
	public String toString() {
		return "Member Event, name is " + name;
	}
}
