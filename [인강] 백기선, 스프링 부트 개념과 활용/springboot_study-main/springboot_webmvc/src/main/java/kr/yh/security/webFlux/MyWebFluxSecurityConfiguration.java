package kr.yh.security.webFlux;

import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.*;

@Configuration(proxyBeanMethods = false) // 빈 메서드 호출마다 새로운 객체 생성
@EnableWebFluxSecurity
public class MyWebFluxSecurityConfiguration {
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http){
        http.authorizeExchange((exchange) -> {
            exchange.matchers(PathRequest.toStaticResources().atCommonLocations()).permitAll();
            exchange.pathMatchers("/foo", "/bar").authenticated();
        });
        http.formLogin(withDefaults());
        return http.build();
    }
}
