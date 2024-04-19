package io.security.basicsecurity.step04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Order(0)
public class SecurityConfig1 {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.securityMatcher("/admin/**")
			.authorizeHttpRequests(auth->auth.anyRequest().authenticated())
			.httpBasic(Customizer.withDefaults());
		return http.build();
	}
}

@Configuration
@Order(1)
class SecurityConfig2{
	@Bean
	public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(auth->auth.anyRequest().permitAll())
			.formLogin(Customizer.withDefaults());
		return http.build();
	}
}
