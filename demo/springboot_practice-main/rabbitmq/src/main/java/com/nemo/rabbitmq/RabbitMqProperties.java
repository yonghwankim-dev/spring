package com.nemo.rabbitmq;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import lombok.Getter;

@ConfigurationProperties(prefix = "spring.rabbitmq")
@Getter
public class RabbitMqProperties {
	private final String host;
	private final int port;
	private final String username;
	private final String password;

	@ConstructorBinding
	public RabbitMqProperties(String host, int port, String username, String password) {
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
	}
}
