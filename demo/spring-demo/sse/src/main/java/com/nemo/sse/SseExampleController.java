package com.nemo.sse;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/sse-server")
public class SseExampleController {

	private final SseExampleService service;

	public SseExampleController(SseExampleService service) {
		this.service = service;
	}

	@GetMapping(value = "/stream-flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> streamFlux(){
		return Flux.interval(Duration.ofSeconds(1))
			.map(sequence -> "Flux - " + LocalTime.now());
	}

	@GetMapping(value = "/stream-sse")
	public Flux<ServerSentEvent<String>> streamEvents(){
		return Flux.interval(Duration.ofSeconds(1))
			.map(sequence -> ServerSentEvent.<String>builder()
				.id(String.valueOf(sequence))
				.event("periodic-event")
				.data("SSE - " + LocalTime.now())
				.build());
	}

	@GetMapping("/consume")
	public ResponseEntity<Void> consumeServerSentEvent() {
		service.consumeServerSentEvent();
		return ResponseEntity.ok().build();
	}

	@GetMapping("/stream-sse-mvc")
	public SseEmitter streamSseMvc(){
		SseEmitter emitter = new SseEmitter();
		ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
		sseMvcExecutor.execute(()->{
			try{
				for(int i = 0; true; i++){
					SseEmitter.SseEventBuilder event = SseEmitter.event()
						.id(String.valueOf(i))
						.name("sse event - mvc")
						.data("SSE MVC - " + LocalTime.now());
					emitter.send(event);
					Thread.sleep(1000);
				}
			} catch (IOException e) {
				emitter.completeWithError(e);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				emitter.completeWithError(e);
			}
		});
		return emitter;
	}
}
