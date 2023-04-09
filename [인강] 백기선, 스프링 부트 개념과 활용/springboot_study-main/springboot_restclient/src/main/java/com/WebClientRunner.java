package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WebClientRunner implements ApplicationRunner {
    @Autowired
    WebClient.Builder builder;

    @Override
    public void run(ApplicationArguments args) {
        // 로컬 커스터마이징
        WebClient webClient = builder
                .baseUrl("http://localhost:8080")
                .build();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // Non-Blocking I/O 기반의 ASynchronous API에 의해서 차례를 기다리지 않고 실행된다.
        // TODO /hello
        Mono<String> helloMono = webClient.get()
                                            .uri("/hello")
                                            .retrieve()
                                            .bodyToMono(String.class);
        helloMono.subscribe(s->{
            System.out.println(s);
            if(stopWatch.isRunning()){
                stopWatch.stop();
            }
            System.out.println(stopWatch.prettyPrint());
            stopWatch.start();
        });


        // TODO /world
        Mono<String> worldMono = webClient.get()
                                            .uri("/world")
                                            .retrieve()
                                            .bodyToMono(String.class);

        worldMono.subscribe(s->{
            System.out.println(s);
            if(stopWatch.isRunning()){
                stopWatch.stop();
            }
            System.out.println(stopWatch.prettyPrint());
            stopWatch.start();
        });

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}
