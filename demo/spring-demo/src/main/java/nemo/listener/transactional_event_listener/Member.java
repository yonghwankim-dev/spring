package nemo.listener.transactional_event_listener;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Member {
	private final String name;
	private final boolean emailVerified;
}
