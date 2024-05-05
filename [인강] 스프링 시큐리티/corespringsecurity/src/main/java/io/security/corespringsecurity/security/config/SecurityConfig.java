package io.security.corespringsecurity.security.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import io.security.corespringsecurity.security.handler.CustomAccessDeniedHandler;
import io.security.corespringsecurity.security.handler.CustomAuthenticationFailureHandler;
import io.security.corespringsecurity.security.handler.CustomAuthenticationHandler;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Order(value = 1)
public class SecurityConfig{

	private final AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/", "/users", "user/login/**", "/login*").permitAll()
				.requestMatchers("/mypage").hasRole("USER")
				.requestMatchers("/messages").hasRole("MANAGER")
				.requestMatchers("/config").hasRole("ADMIN")
				.anyRequest().authenticated());
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
		http.exceptionHandling(configurer->
			configurer.accessDeniedHandler(accessDeniedHandler()));
		return http.build();
	}

	@Bean
	protected CustomAuthenticationHandler customAuthenticationHandler(){
		return new CustomAuthenticationHandler();
	}

	@Bean
	protected CustomAuthenticationFailureHandler customAuthenticationFailureHandler(){
		return new CustomAuthenticationFailureHandler();
	}

	@Bean
	protected AccessDeniedHandler accessDeniedHandler(){
		return new CustomAccessDeniedHandler("/denied");
	}

	@Bean
	protected PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public WebSecurityCustomizer webSecurity(){
		return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

}
