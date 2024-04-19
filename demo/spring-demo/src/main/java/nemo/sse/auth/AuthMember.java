package nemo.sse.auth;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class AuthMember {
	private String username;

	@Builder
	public AuthMember(String username) {
		this.username = username;
	}
}
