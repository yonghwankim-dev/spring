package nemo.listener.event_listener;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MemberSignupEvent {
	private final Long memberId;

	@Override
	public String toString() {
		return "Member Event, memberId is " + memberId;
	}
}
