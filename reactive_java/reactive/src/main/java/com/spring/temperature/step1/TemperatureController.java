package com.spring.temperature.step1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class TemperatureController {
	private final Set<SseEmitter> clients = new CopyOnWriteArraySet<>();

	@GetMapping("/temperature-stream")
	public SseEmitter events(){
		SseEmitter emitter = new SseEmitter();
		clients.add(emitter);

		emitter.onTimeout(()->clients.remove(emitter));
		emitter.onCompletion(()->clients.remove(emitter));
		return emitter;
	}

	@Async
	@EventListener
	public void handleMessage(Temperature temperature){
		List<SseEmitter> deadEmitters = new ArrayList<>();
		clients.forEach(emitter -> {
			try {
				emitter.send(temperature, MediaType.APPLICATION_JSON);
			} catch (IOException e) {
				deadEmitters.add(emitter);
			}
		});
		deadEmitters.forEach(clients::remove);
	}
}
