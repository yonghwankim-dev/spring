package com.bean_lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NetworkClientFactory {

    @Bean
    public NetworkClient networkClient() {
        NetworkClient client = new NetworkClient();
        client.setUrl("http://www.naver.com");
        return client;
    }

    @Bean(initMethod = "initialize", destroyMethod = "close")
    public NetworkClient2 networkClient2() {
        NetworkClient2 client2 = new NetworkClient2();
        client2.setUrl("http://www.naver.com");
        return client2;
    }
}
