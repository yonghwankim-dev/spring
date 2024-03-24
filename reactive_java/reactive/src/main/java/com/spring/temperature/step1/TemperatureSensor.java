package com.spring.temperature.step1;

import static java.util.concurrent.TimeUnit.*;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class TemperatureSensor {
	private final ApplicationEventPublisher publisher;
	private final Random rnd = new Random();
	private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

	public TemperatureSensor(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	@PostConstruct
	public void startProcessing(){
		this.executor.schedule(this::probe, 1, TimeUnit.SECONDS);
	}

	private void probe() {
		double temperature = 16 + rnd.nextGaussian() * 10;
		publisher.publishEvent(new Temperature(temperature));
		executor.schedule(this::probe, rnd.nextInt(5000), MILLISECONDS);
	}

}
