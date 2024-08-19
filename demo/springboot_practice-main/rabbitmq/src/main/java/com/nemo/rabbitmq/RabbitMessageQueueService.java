package com.nemo.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMessageQueueService {
	private final RabbitTemplate rabbitTemplate;

	@Value("${rabbitmq.queue.name}")
	private String queueName;

	@Value("${rabbitmq.exchange.name}")
	private String exchangeName;

	@Value("${rabbitmq.routing.key}")
	private String routingKey;

	/**
	 * 1. Queue로 메시지를 발행
	 * 2. Producer 역할 -> Direct Exchange 전략
	 * @param messageDto 메시지
	 */
	public void sendMessage(MessageDto messageDto) {
		log.info("Message send: {}", messageDto);
		this.rabbitTemplate.convertAndSend(exchangeName, routingKey, messageDto);
	}

	/**
	 * 1. Queue 에서 메시지를 구독
	 * @param messageDto 메시지를 담은 객체
	 */
	@RabbitListener(queues = "${rabbitmq.queue.name}")
	public void receiveMessage(MessageDto messageDto) {
		log.info("Received Message : {}",messageDto.toString());
	}
}
