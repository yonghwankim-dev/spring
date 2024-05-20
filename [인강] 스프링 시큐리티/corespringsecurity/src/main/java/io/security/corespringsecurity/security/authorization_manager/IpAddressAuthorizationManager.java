package io.security.corespringsecurity.security.authorization_manager;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import io.security.corespringsecurity.security.service.SecurityResourceService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IpAddressAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

	private final SecurityResourceService securityResourceService;

	@Override
	public AuthorizationDecision check(Supplier<Authentication> supplier,
		RequestAuthorizationContext requestAuthorizationContext) {
		Authentication authentication = supplier.get();
		WebAuthenticationDetails details = (WebAuthenticationDetails)authentication.getDetails();
		String remoteAddress = details.getRemoteAddress();

		List<String> accessIpList = securityResourceService.getAccessIpList();
		for (String accessIp : accessIpList) {
			if (accessIp.equals(remoteAddress)) {
				return new AuthorizationDecision(true);
			}
		}
		return new AuthorizationDecision(false);
	}
}
