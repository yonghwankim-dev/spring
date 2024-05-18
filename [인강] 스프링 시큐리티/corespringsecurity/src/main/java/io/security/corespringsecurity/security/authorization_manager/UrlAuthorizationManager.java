package io.security.corespringsecurity.security.authorization_manager;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.hierarchicalroles.NullRoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.authorization.AuthoritiesAuthorizationManager;
import org.springframework.security.authorization.AuthorityAuthorizationDecision;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import io.security.corespringsecurity.security.service.SecurityResourceService;
import jakarta.servlet.http.HttpServletRequest;

public class UrlAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

	private LinkedHashMap<RequestMatcher, List<String>> requestMap;
	private final SecurityResourceService securityResourceService;
	private final RoleHierarchy roleHierarchy;

	public UrlAuthorizationManager(LinkedHashMap<RequestMatcher, List<String>> requestMap,
		SecurityResourceService securityResourceService, RoleHierarchy roleHierarchy) {
		this.requestMap = requestMap;
		this.securityResourceService = securityResourceService;
		this.roleHierarchy = roleHierarchy;
	}

	@Override
	public AuthorizationDecision check(Supplier<Authentication> supplier, RequestAuthorizationContext requestAuthorizationContext) {
		// AuthoritiesAuthorizationManager authoritiesAuthorizationManager = new AuthoritiesAuthorizationManager();
		// authoritiesAuthorizationManager.setRoleHierarchy(roleHierarchy);

		Authentication authentication = supplier.get();
		HttpServletRequest request = requestAuthorizationContext.getRequest();
		if (requestMap != null){
			for (Map.Entry<RequestMatcher, List<String>> entry : requestMap.entrySet()) {
				if (entry.getKey().matches(request)) {
					List<String> authorities = entry.getValue();
					boolean isGranted = isGranted(authentication, authorities);
					return new AuthorityAuthorizationDecision(isGranted, AuthorityUtils.createAuthorityList(authorities));
				}
			}
		}
		return new AuthorizationDecision(true);
	}

	private boolean isGranted(Authentication authentication, List<String> authorities) {
		return authentication != null && isAuthorized(authentication, authorities);
	}

	private boolean isAuthorized(Authentication authentication, List<String> authorities) {
		for (GrantedAuthority grantedAuthority : roleHierarchy.getReachableGrantedAuthorities(
			authentication.getAuthorities())){
			if (authorities.contains(grantedAuthority.getAuthority())){
				return true;
			}
		}
		return false;
	}

	public void reload() {
		// gc 성능 문제로 인하여 iterator 방식 사용
		LinkedHashMap<RequestMatcher, List<String>> reloadedMap = securityResourceService.getResourceList();
		Iterator<Map.Entry<RequestMatcher, List<String>>> iterator = reloadedMap.entrySet().iterator();

		requestMap.clear();

		while (iterator.hasNext()){
			Map.Entry<RequestMatcher, List<String>> entry = iterator.next();
			requestMap.put(entry.getKey(), entry.getValue());
		}
	}
}
