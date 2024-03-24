package com.example.sequence;

import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Stream;

import com.example.utils.TimeUtils;

import ch.qos.logback.core.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class HotSequenceExample {
	public static void main(String[] args) {
		Flux<String> concertFlux = Flux.fromStream(Stream.of("Singer A", "Singer B", "Singer C", "Singer D", "Singer E"))
			.delayElements(Duration.ofSeconds(1)).share(); // share() 원본 Flux를 여러 Subscriber가 공유한다

		concertFlux.subscribe(signer -> log.info(" # Subscriber1 is watching {}'s song.", signer));

		TimeUtils.sleep(2500);

		concertFlux.subscribe(signer -> log.info(" # Subscriber2 is watching {}'s song.", signer));

		TimeUtils.sleep(3000);
	}
}
