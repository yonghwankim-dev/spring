package com.nemo.rabbitmq;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RabbitMqRestController {
	private final RabbitMessageQueueService service;

	@PostMapping("/send/message")
	public ResponseEntity<String> sendMessage(@RequestBody MessageDto messageDto){
		service.sendMessage(messageDto);
		return ResponseEntity.ok("Message sent to RabbitMQ");
	}
}
