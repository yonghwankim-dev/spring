package com.spring.temperature.step2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class TemperatureController_v2 {
	private final TemperatureSensor_v2 temperatureSensor;

	@GetMapping("/temperature-stream-v2")
	public SseEmitter events(){
		RxSseEmitter emitter = new RxSseEmitter();
		temperatureSensor.temperatureStream().subscribe(emitter.getSubscriber());
		return emitter;
	}
}
