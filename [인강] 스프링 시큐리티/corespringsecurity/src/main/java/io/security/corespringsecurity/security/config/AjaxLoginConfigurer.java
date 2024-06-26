package io.security.corespringsecurity.security.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import io.security.corespringsecurity.security.filter.AjaxLoginProcessingFilter;

public final class AjaxLoginConfigurer<H extends HttpSecurityBuilder<H>> extends
	AbstractAuthenticationFilterConfigurer<H, AjaxLoginConfigurer<H>, AjaxLoginProcessingFilter> {

	private AuthenticationSuccessHandler successHandler;
	private AuthenticationFailureHandler failureHandler;
	private AuthenticationManager authenticationManager;
	private SecurityContextRepository securityContextRepository;

	public AjaxLoginConfigurer() {
		super(new AjaxLoginProcessingFilter(null), null);
	}

	@Override
	public void init(H http) throws Exception {
		super.init(http);
	}

	@Override
	public void configure(H http) throws Exception {
		if (authenticationManager == null) {
			authenticationManager = http.getSharedObject(AuthenticationManager.class);
		}
		getAuthenticationFilter().setAuthenticationSuccessHandler(successHandler);
		getAuthenticationFilter().setAuthenticationFailureHandler(failureHandler);
		getAuthenticationFilter().setAuthenticationManager(authenticationManager);
		getAuthenticationFilter().setSecurityContextRepository(securityContextRepository);

		SessionAuthenticationStrategy sessionAuthenticationStrategy = http.getSharedObject(SessionAuthenticationStrategy.class);
		if (sessionAuthenticationStrategy != null) {
			getAuthenticationFilter().setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
		}
		RememberMeServices rememberMeServices = http.getSharedObject(RememberMeServices.class);
		if (rememberMeServices != null) {
			getAuthenticationFilter().setRememberMeServices(rememberMeServices);
		}
		http.setSharedObject(AjaxLoginProcessingFilter.class, getAuthenticationFilter());
		http.addFilterBefore(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	public AjaxLoginConfigurer<H> successHandlerAjax(AuthenticationSuccessHandler successHandler){
		this.successHandler = successHandler;
		return this;
	}

	public AjaxLoginConfigurer<H> failureHandlerAjax(AuthenticationFailureHandler failureHandler){
		this.failureHandler = failureHandler;
		return this;
	}

	public AjaxLoginConfigurer<H> setAuthenticationManager(AuthenticationManager authenticationManager){
		this.authenticationManager = authenticationManager;
		return this;
	}

	public AjaxLoginConfigurer<H> setSecurityContextRepository(SecurityContextRepository securityContextRepository){
		this.securityContextRepository = securityContextRepository;
		return this;
	}

	@Override
	protected RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl) {
		return new AntPathRequestMatcher(loginProcessingUrl, HttpMethod.POST.name());
	}
}
