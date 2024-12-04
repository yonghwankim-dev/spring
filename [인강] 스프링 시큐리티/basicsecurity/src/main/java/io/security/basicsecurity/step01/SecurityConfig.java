package io.security.basicsecurity.step01;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final UserDetailsService userDetailService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authorize ->
				authorize.anyRequest().authenticated()
			);
		http.formLogin(configurer ->
			configurer
				// .loginPage("/loginPage")
				.defaultSuccessUrl("/")
				.failureUrl("/login")
				.usernameParameter("userId")
				.passwordParameter("passwd")
				.loginProcessingUrl("/login_proc")
				.successHandler(new AuthenticationSuccessHandler() {
					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
						Authentication authentication) throws IOException, ServletException {
						System.out.println("authentication : " + authentication.getName());
						response.sendRedirect("/");
					}
				})
				.failureHandler(new AuthenticationFailureHandler() {
					@Override
					public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
						AuthenticationException exception) throws IOException, ServletException {
						System.out.println("exception : " + exception.getMessage());
						response.sendRedirect("/login");
					}
				})
				.permitAll() // loginPage로 접근하는 모든 사용자는 인증없이도 접근가능
		);
		http.logout(configurer ->
			configurer
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login")
				.addLogoutHandler(new LogoutHandler() {
					@Override
					public void logout(HttpServletRequest request, HttpServletResponse response,
						Authentication authentication) {
						HttpSession session = request.getSession();
						session.invalidate(); // 세션 무묘화
					}
				})
				.logoutSuccessHandler(new LogoutSuccessHandler() {
					@Override
					public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
						Authentication authentication) throws IOException, ServletException {
						response.sendRedirect("/login");
					}
				}).deleteCookies("remember-me"));
		http.rememberMe(configurer ->
			configurer
				.rememberMeParameter("remember")
				.tokenValiditySeconds(3600)
				.userDetailsService(userDetailService));
		http.sessionManagement(configurer ->
			configurer
				.invalidSessionUrl("/invalid")
				.maximumSessions(1)
				.maxSessionsPreventsLogin(false)// true: 현재 사용자 인증 실패, false : 이전 사용자 세션 만료
				.expiredUrl("/expired")
		);
		http.sessionManagement(configurer ->
				// 기본값
				configurer.sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::changeSessionId)
			// configurer.sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::none)
			// configurer.sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::migrateSession)
			// configurer.sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::newSession)
		);
		http.sessionManagement(configurer ->
				configurer.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // 기본값
			// configurer.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
			// configurer.sessionCreationPolicy(SessionCreationPolicy.NEVER)
			// configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		);
		return http.build();
	}
}
