package io.security.basicsecurity.step02;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(
				authorizeHttpRequests ->
					authorizeHttpRequests
						.requestMatchers("/login").permitAll()
						.requestMatchers("/user").hasRole("USER")
						.requestMatchers("/admin/pay").hasRole("ADMIN")
						.requestMatchers("/admin/**").hasAnyRole("ADMIN", "SYS")
						.anyRequest().authenticated()
			);

		http
			.formLogin()
				.successHandler(new AuthenticationSuccessHandler() {
					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
						Authentication authentication) throws IOException, ServletException {
						RequestCache requestCache = new HttpSessionRequestCache();
						SavedRequest savedRequest = requestCache.getRequest(request, response);
						String redirectUrl = savedRequest.getRedirectUrl();
						response.sendRedirect(redirectUrl);
					}
				});

		http.exceptionHandling()
			// .authenticationEntryPoint(new AuthenticationEntryPoint() {
			// 	@Override
			// 	public void commence(HttpServletRequest request, HttpServletResponse response,
			// 		AuthenticationException authException) throws IOException, ServletException {
			// 		response.sendRedirect("/login");
			// 	}
			// })
			.accessDeniedHandler(new AccessDeniedHandler() {
				@Override
				public void handle(HttpServletRequest request, HttpServletResponse response,
					AccessDeniedException accessDeniedException) throws IOException, ServletException {
					response.sendRedirect("/denied");
				}
			});
		return http.build();
	}

	@Bean
	public UserDetailsManager users(){

		UserDetails user = User.builder()
			.username("user")
			.password("{noop}1111") // password 암호화 방식을 접두사로 추가합니다. => {noop} (별도 암호화없이 평문으로 저장)
			.roles("USER")
			.build();

		UserDetails sys = User.builder()
			.username("sys")
			.password("{noop}1111")
			.roles("SYS")
			.build();

		UserDetails admin = User.builder()
			.username("admin")
			.password("{noop}1111")
			.roles("ADMIN", "SYS", "USER")
			.build();
		return new InMemoryUserDetailsManager(user, sys, admin);
	}
}
