package nemo.sse.auth;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Getter;

@Getter
@Component
@RequestScope
public class AuthenticationContext {
	private AuthMember authMember;

	public void setAuthMember(AuthMember authMember) {
		this.authMember = authMember;
	}
}
