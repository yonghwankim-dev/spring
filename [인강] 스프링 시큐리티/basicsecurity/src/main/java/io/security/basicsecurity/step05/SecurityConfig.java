package io.security.basicsecurity.step05;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests()
				.anyRequest()
					.authenticated();

		http
			.formLogin();

		SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
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
