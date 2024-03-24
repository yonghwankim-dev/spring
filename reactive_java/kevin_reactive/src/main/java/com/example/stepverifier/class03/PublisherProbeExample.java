package com.example.stepverifier.class03;

import reactor.core.publisher.Mono;

public class PublisherProbeExample {
    public static Mono<String> processWith(Mono<String> main, Mono<String> standby) {
        return main
                .flatMap(Mono::just)
                .switchIfEmpty(standby);
    }

    public static Mono<String> useMainPower() {
        return Mono.empty();
    }

    public static Mono<String> useStandbyPower() {
        return Mono.just("# use Standby Power");
    }
}
