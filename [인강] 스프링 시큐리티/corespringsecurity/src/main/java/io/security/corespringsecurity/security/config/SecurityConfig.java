package io.security.corespringsecurity.security.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public UserDetailsManager users(){
		String password = passwordEncoder().encode("1111");
		UserDetails user = User.builder()
			.username("user")
			.password(password)
			.roles("USER")
			.build();

		UserDetails sys = User.builder()
			.username("manager")
			.password(password)
			.roles("MANAGER", "USER")
			.build();

		UserDetails admin = User.builder()
			.username("admin")
			.password(password)
			.roles("ADMIN", "USER", "MANAGER")
			.build();
		return new InMemoryUserDetailsManager(user, sys, admin);
	}

	@Bean
	protected PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public WebSecurityCustomizer webSecurity(){
		return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/", "user/login/**", "/users").permitAll()
				.requestMatchers("/mypage").hasRole("USER")
				.requestMatchers("/messages").hasRole("MANAGER")
				.requestMatchers("/config").hasRole("ADMIN")
				.anyRequest().authenticated());
		http
			.formLogin(httpSecurityFormLoginConfigurer -> {

			});
		return http.build();
	}
}
