package io.security.corespringsecurity.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.security.corespringsecurity.security.common.AjaxAccessDeniedHandler;
import io.security.corespringsecurity.security.common.AjaxLoginAuthenticationEntryPoint;
import io.security.corespringsecurity.security.filter.AjaxLoginProcessingFilter;
import io.security.corespringsecurity.security.handler.AjaxAuthenticationFailureHandler;
import io.security.corespringsecurity.security.handler.AjaxAuthenticationSuccessHandler;
import io.security.corespringsecurity.security.provider.AjaxAuthenticationProvider;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@Order(0)
public class AjaxSecurityConfig {

	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	private final ObjectMapper objectMapper;

	// Warning: SecurityConfig 클래스의 securityFilterChain 메서드와 메서드 명이 동일한 경우 해당 메서드는 호출되지 않음
	@Bean
	public SecurityFilterChain ajaxSecurityFilterChain(HttpSecurity http) throws Exception {
		http
			.securityMatcher("/api/**")
			.authorizeHttpRequests(authorize -> authorize
				.anyRequest().authenticated()
			);
		http.authenticationProvider(authenticationProvider());
		http.authenticationManager(authenticationManager());
		http.addFilterBefore(ajaxLoginProcessingFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
		http.csrf(AbstractHttpConfigurer::disable);
		http.exceptionHandling(configurer->{
			configurer.authenticationEntryPoint(ajaxLoginAuthenticationEntryPoint());
			configurer.accessDeniedHandler(ajaxAccessDeniedHandler());
		});
		return http.build();
	}

	@Bean
	protected AjaxLoginAuthenticationEntryPoint ajaxLoginAuthenticationEntryPoint(){
		return new AjaxLoginAuthenticationEntryPoint();
	}

	@Bean
	protected AjaxAccessDeniedHandler ajaxAccessDeniedHandler(){
		return new AjaxAccessDeniedHandler();
	}

	@Bean
	protected AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler(){
		return new AjaxAuthenticationSuccessHandler(objectMapper);
	}

	@Bean
	protected AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler(){
		return new AjaxAuthenticationFailureHandler(objectMapper);
	}

	@Bean
	protected AjaxLoginProcessingFilter ajaxLoginProcessingFilter(AuthenticationManager authenticationManager){
		AjaxLoginProcessingFilter ajaxLoginProcessingFilter = new AjaxLoginProcessingFilter(authenticationManager);
		ajaxLoginProcessingFilter.setAuthenticationSuccessHandler(ajaxAuthenticationSuccessHandler());
		ajaxLoginProcessingFilter.setAuthenticationFailureHandler(ajaxAuthenticationFailureHandler());
		return ajaxLoginProcessingFilter;
	}

	@Bean
	protected AuthenticationManager authenticationManager(){
		return new ProviderManager(authenticationProvider());
	}

	@Bean
	protected AuthenticationProvider authenticationProvider(){
		return new AjaxAuthenticationProvider(userDetailsService, passwordEncoder);
	}
}
