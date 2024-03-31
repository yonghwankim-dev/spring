package io.security.basicsecurity.step01;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.anyRequest().authenticated();
		http
			.formLogin()
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
			.permitAll(); // loginPage로 접근하는 모든 사용자는 인증없이도 접근가능

		http
			.logout()
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
			})
			.deleteCookies("remember-me");
		http
			.rememberMe()
			.rememberMeParameter("remember")
			.tokenValiditySeconds(3600)
			.userDetailsService(userDetailService);

		http.sessionManagement()
			.maximumSessions(1)
			.maxSessionsPreventsLogin(false); // true: 현재 사용자 인증 실패, false : 이전 사용자 세션 만료
		http.sessionManagement()
			// .sessionFixation().none();
			// .sessionFixation().migrateSession()
			// .sessionFixation().newSession()
			.sessionFixation().changeSessionId(); // 기본값
		http.sessionManagement()
			// .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
			.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED); // 기본값
			// .sessionCreationPolicy(SessionCreationPolicy.NEVER)
			// .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		return http.build();
	}
}
