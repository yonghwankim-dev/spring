package com.dependency_injection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountingDaoFactory {

    @Bean
    public ConnectionMaker connectionMarker() {
        return new CountingConnectionMaker(realConnectionMarker());
    }

    @Bean
    public ConnectionMaker realConnectionMarker() {
        return new DConnectionMaker();
    }

}
