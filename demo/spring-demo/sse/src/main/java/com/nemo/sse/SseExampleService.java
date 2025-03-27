package com.nemo.sse;

import java.lang.reflect.ParameterizedType;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

@Service
public class SseExampleService {
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(SseExampleService.class);
	public void consumeServerSentEvent() {
		WebClient client = WebClient.create("http://localhost:8080/sse-server");
		ParameterizedTypeReference<ServerSentEvent<String>> type = new ParameterizedTypeReference<>() {};
		Flux<ServerSentEvent<String>> eventStream = client.get()
			.uri("/stream-sse")
			.retrieve()
			.bodyToFlux(type);
		eventStream.subscribe(content-> logger.info("Time: {} - event: name[{}], id [{}], content[{}] ",
			LocalTime.now(), content.event(), content.id(), content.data()),
			error-> logger.error("Error receiving SSE: {}", error.toString()),
			()-> logger.info("Completed"));
	}
}
