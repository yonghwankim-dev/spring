package io.security.corespringsecurity.security.authorization_manager;

import java.util.function.Supplier;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.authorization.AuthorizationManagers;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

public class UnanimousBasedAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
	private final AuthorizationManager<RequestAuthorizationContext>[] authorizationManagers;

	@SafeVarargs
	public UnanimousBasedAuthorizationManager(
		AuthorizationManager<RequestAuthorizationContext>... authorizationManagers) {
		this.authorizationManagers = authorizationManagers;
	}

	@Override
	public AuthorizationDecision check(Supplier<Authentication> authentication,
		RequestAuthorizationContext requestAuthorizationContext) {
		return AuthorizationManagers.allOf(authorizationManagers)
			.check(authentication, requestAuthorizationContext);
	}
}
