package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientCodecCustomizer;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class SpringbootRestclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRestclientApplication.class, args);
    }

    // WebClient 글로벌 커스터마이징
    @Bean
    public WebClientCustomizer webClientCustomizer(){
        return webClientBuilder -> {webClientBuilder.baseUrl("http://localhost:8080");};
    }

    @Bean
    public RestTemplateCustomizer restTemplateCustomizer(){
        return restTemplate -> {restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());};
    }

}
