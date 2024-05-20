package io.security.corespringsecurity.security.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import io.security.corespringsecurity.security.authorization_manager.IpAddressAuthorizationManager;
import io.security.corespringsecurity.security.authorization_manager.UnanimousBasedAuthorizationManager;
import io.security.corespringsecurity.security.authorization_manager.UrlAuthorizationManager;
import io.security.corespringsecurity.security.factory.UrlResourcesMapFactoryBean;
import io.security.corespringsecurity.security.handler.CustomAccessDeniedHandler;
import io.security.corespringsecurity.security.handler.CustomAuthenticationFailureHandler;
import io.security.corespringsecurity.security.handler.CustomAuthenticationHandler;
import io.security.corespringsecurity.security.service.SecurityResourceService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@Order(value = 1)
public class SecurityConfig {

	private final AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;
	private final SecurityResourceService securityResourceService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/", "/users", "user/login/**", "/login*", "/error").permitAll()
				.anyRequest().access(unanimousBasedAuthorizationManager()));
		http
			.formLogin(configurer ->
				configurer.loginPage("/login")
					.loginProcessingUrl("/login_proc")
					.defaultSuccessUrl("/")
					.authenticationDetailsSource(authenticationDetailsSource)
					.successHandler(customAuthenticationHandler())
					.failureHandler(customAuthenticationFailureHandler())
					.permitAll()
			);
		http.exceptionHandling(configurer ->
			configurer.accessDeniedHandler(customAccessDeniedHandler()));

		return http.build();
	}

	@Bean
	public CustomAuthenticationHandler customAuthenticationHandler() {
		return new CustomAuthenticationHandler();
	}

	@Bean
	public CustomAuthenticationFailureHandler customAuthenticationFailureHandler() {
		return new CustomAuthenticationFailureHandler();
	}

	@Bean
	public CustomAccessDeniedHandler customAccessDeniedHandler() {
		return new CustomAccessDeniedHandler("/denied");
	}

	@Bean
	public WebSecurityCustomizer webSecurity() {
		return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	@Bean
	public UrlResourcesMapFactoryBean urlResourcesMapFactoryBean() {
		UrlResourcesMapFactoryBean urlResourcesMapFactoryBean = new UrlResourcesMapFactoryBean();
		urlResourcesMapFactoryBean.setSecurityResourceService(securityResourceService);
		return urlResourcesMapFactoryBean;
	}

	@Bean
	public UrlAuthorizationManager urlAuthorizationManager() {
		return new UrlAuthorizationManager(urlResourcesMapFactoryBean().getObject(), securityResourceService,
			roleHierarchyImpl());
	}

	@Bean
	public RoleHierarchyImpl roleHierarchyImpl() {
		return new RoleHierarchyImpl();
	}

	@Bean
	public IpAddressAuthorizationManager ipAddressAuthorizationManager() {
		return new IpAddressAuthorizationManager(securityResourceService);
	}

	@Bean
	public UnanimousBasedAuthorizationManager unanimousBasedAuthorizationManager() {
		return new UnanimousBasedAuthorizationManager(ipAddressAuthorizationManager(), urlAuthorizationManager());
	}
}
