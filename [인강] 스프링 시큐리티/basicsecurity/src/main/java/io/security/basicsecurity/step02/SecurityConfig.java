package io.security.basicsecurity.step02;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
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

	enum Role {
		USER("ROLE_USER"), ADMIN("ROLE_ADMIN"), SYS("ROLE_SYS");

		private final String roleName;

		Role(String roleName) {
			this.roleName = roleName;
		}
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// http
		// 	.securityMatcher("/shop/**")
		// 	.authorizeHttpRequests(authorize -> {
		// 		authorize.requestMatchers("/shop/login.html", "/shop/users/**").permitAll();
		// 		authorize.requestMatchers("/shop/mypage").hasRole(Role.USER.name());
		// 		authorize.requestMatchers("/shop/admin/pay").hasAuthority(Role.ADMIN.roleName);
		// 		authorize.requestMatchers("/shop/admin/**").hasAnyAuthority(Role.ADMIN.roleName, Role.SYS.roleName);
		// 	});
		http.authorizeHttpRequests(
			authorizeHttpRequests ->
				authorizeHttpRequests
					.requestMatchers("/user").hasRole(Role.USER.name())
					.requestMatchers("/admin/pay").hasRole(Role.ADMIN.name())
					.requestMatchers("/admin/**").hasAnyRole(Role.ADMIN.name(), Role.SYS.name())
					.requestMatchers("/error").permitAll()
					.anyRequest().authenticated()
		);

		http
			.formLogin(configurer ->
				configurer
					// .loginPage("/login")
					.defaultSuccessUrl("/")
					.failureUrl("/login")
					.usernameParameter("username")
					.passwordParameter("password")
					.loginProcessingUrl("/login_proc")
					.successHandler(new AuthenticationSuccessHandler() {
						@Override
						public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
							Authentication authentication) throws IOException, ServletException {
							RequestCache requestCache = new HttpSessionRequestCache();
							SavedRequest savedRequest = requestCache.getRequest(request, response);
							String redirectUrl = savedRequest.getRedirectUrl();
							response.sendRedirect(redirectUrl);
						}
					}).permitAll()
			);
		// http.exceptionHandling(configurer -> {
		// 	configurer.authenticationEntryPoint(new AuthenticationEntryPoint() {
		// 		@Override
		// 		public void commence(HttpServletRequest request, HttpServletResponse response,
		// 			AuthenticationException authException) throws IOException, ServletException {
		// 			response.sendRedirect("/loginPage");
		// 		}
		// 	});
		// 	configurer.accessDeniedHandler(new AccessDeniedHandler() {
		// 		@Override
		// 		public void handle(HttpServletRequest request, HttpServletResponse response,
		// 			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// 			response.sendRedirect("/denied");
		// 		}
		// 	});
		// });
		return http.build();
	}

	@Bean
	public UserDetailsManager users() {
		// password 암호화 방식을 접두사로 추가합니다. => {noop} (별도 암호화없이 평문으로 저장)
		final String PASSWORD = "{noop}1111";
		UserDetails user = User.builder()
			.username("user")
			.password(PASSWORD)
			.roles(Role.USER.name())
			.build();

		UserDetails sys = User.builder()
			.username("sys")
			.password(PASSWORD)
			.roles(Role.SYS.name())
			.build();

		UserDetails admin = User.builder()
			.username("admin")
			.password(PASSWORD)
			.roles(Role.ADMIN.name(), Role.SYS.name(), Role.USER.name())
			.build();
		return new InMemoryUserDetailsManager(user, sys, admin);
	}
}
